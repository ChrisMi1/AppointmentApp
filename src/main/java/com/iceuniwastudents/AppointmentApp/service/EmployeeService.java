package com.iceuniwastudents.AppointmentApp.service;

import com.iceuniwastudents.AppointmentApp.Repository.EmployeeRepo;
import com.iceuniwastudents.AppointmentApp.Repository.VerificationRepo;
import com.iceuniwastudents.AppointmentApp.dto.*;
import com.iceuniwastudents.AppointmentApp.exception.EmailAlreadyExists;
import com.iceuniwastudents.AppointmentApp.exception.EmailNotFound;
import com.iceuniwastudents.AppointmentApp.exception.MailFailureException;
import com.iceuniwastudents.AppointmentApp.exception.UserNotVerified;
import com.iceuniwastudents.AppointmentApp.model.Employee;
import com.iceuniwastudents.AppointmentApp.model.Verification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final EncryptionService encryptionService;
    private final JWTService jwtService;
    private final EmailService emailService;
    private final VerificationRepo verificationRepo;


    public List<EmployeeResponse> getAllEmployees(){
        List<Employee> employees = employeeRepo.findAll();
        return employees.stream().map(this::mapToEmployeeResponse).collect(Collectors.toList());
    }

    private EmployeeResponse mapToEmployeeResponse(Employee employee){
        return EmployeeResponse.builder()
                .name(employee.getFirstName()+ " " + employee.getLastName())
                .id(employee.getId())
                .photo(employee.getPhoto())
                .build();
    }
    public RegisterResponse register(RegisterBody registerBody) throws EmailAlreadyExists, MailFailureException {
        if(employeeRepo.findByEmail(registerBody.getEmail()).isPresent()){
            throw new EmailAlreadyExists("The email already exists");
        }
        Employee employee = Employee.builder()
                .email(registerBody.getEmail())
                .firstName(registerBody.getFirstName())
                .lastName(registerBody.getLastName())
                .password(encryptionService.encryptPassword(registerBody.getPassword()))
                .phoneNumber(registerBody.getPhoneNumber())
                .role("ROLE_USER")
                .build();
        Verification verificationToken = createVerificationToken(employee);
        emailService.sendVerificationEmail(verificationToken);
        employee.addVerificationToken(verificationToken);
        employeeRepo.save(employee);
        return RegisterResponse.builder()
                .message("Please verify your email")
                .success(true)
                .build();
    }

    private Verification createVerificationToken(Employee employee){
        return Verification.builder()
                .token(jwtService.generateTokenEmail(employee))
                .employee(employee)
                .created(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    @Transactional
    public boolean verifyUser(String token) {
        Optional<Verification> verification1 = verificationRepo.findByToken(token);
        if(verification1.isPresent()){
            Employee employee = verification1.get().getEmployee();
            if(!employee.isEmailVerified()){
                employee.setEmailVerified(true);
                employeeRepo.save(employee);
                verificationRepo.deleteByEmployee(employee);
                return true;
            }
        }
        return false;
    }

    public LoginResponse login(LoginBody loginBody) throws EmailNotFound, UserNotVerified,MailFailureException {
        Optional<Employee> employee = employeeRepo.findByEmail(loginBody.getEmail());
        if(employee.isPresent()){
            Employee employee1 = employee.get();
            if(encryptionService.verifyPassword(loginBody.getPassword(),employee1.getPassword())){
                if(employee1.isEmailVerified()){
                    return LoginResponse.builder()
                            .jwt(jwtService.generateToken(employee1))
                            .success(true)
                            .build();
                }else{
                    List<Verification> verificationTokens = employee1.getVerificationTokens();
                    boolean resend= verificationTokens.isEmpty() ||
                            verificationTokens.getFirst().getCreated().before(new Timestamp(System.currentTimeMillis()-60*60*1000));
                    if(resend){
                        Verification verificationToken1 = createVerificationToken(employee1);
                        verificationRepo.save(verificationToken1);
                        emailService.sendVerificationEmail(verificationToken1);
                    }
                    throw new UserNotVerified("Please verify your email!");
                }
            }else{
                throw new UserNotVerified("Wrong password");
            }
        }else{
            throw new EmailNotFound("The email doesn't exist");
        }

    }
}
