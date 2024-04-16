package com.iceuniwastudents.AppointmentApp.service;

import com.iceuniwastudents.AppointmentApp.Repository.EmployeeRepo;
import com.iceuniwastudents.AppointmentApp.dto.LoginBody;
import com.iceuniwastudents.AppointmentApp.dto.RegisterBody;
import com.iceuniwastudents.AppointmentApp.exception.EmailAlreadyExists;
import com.iceuniwastudents.AppointmentApp.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final EncryptionService encryptionService;
    public String register(RegisterBody registerBody) throws EmailAlreadyExists {
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
        employeeRepo.save(employee);
        return "The employee added successfully!";

    }




}
