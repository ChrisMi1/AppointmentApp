package com.iceuniwastudents.AppointmentApp.service;

import com.iceuniwastudents.AppointmentApp.Repository.EmployeeRepo;
import com.iceuniwastudents.AppointmentApp.Repository.VerificationRepo;
import com.iceuniwastudents.AppointmentApp.dto.LoginBody;
import com.iceuniwastudents.AppointmentApp.dto.RegisterBody;
import com.iceuniwastudents.AppointmentApp.exception.EmailAlreadyExists;
import com.iceuniwastudents.AppointmentApp.exception.MailFailureException;
import com.iceuniwastudents.AppointmentApp.model.Employee;
import com.iceuniwastudents.AppointmentApp.model.User;
import com.iceuniwastudents.AppointmentApp.model.Verification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final EncryptionService encryptionService;
    private final JWTService jwtService;
    private final EmailService emailService;
    private final VerificationRepo verificationRepo;
    public String register(RegisterBody registerBody) throws EmailAlreadyExists, MailFailureException {
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
        return "The employee added successfully!";

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
                return true;
            }
        }
        return false;
    }
}
