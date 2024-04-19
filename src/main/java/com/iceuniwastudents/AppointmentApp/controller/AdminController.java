package com.iceuniwastudents.AppointmentApp.controller;

import com.iceuniwastudents.AppointmentApp.dto.RegisterBody;
import com.iceuniwastudents.AppointmentApp.exception.EmailAlreadyExists;
import com.iceuniwastudents.AppointmentApp.exception.MailFailureException;
import com.iceuniwastudents.AppointmentApp.model.Verification;
import com.iceuniwastudents.AppointmentApp.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<String> registerEmployee(@RequestBody RegisterBody registerBody) throws EmailAlreadyExists, MailFailureException {
        return new ResponseEntity<>(employeeService.register(registerBody), HttpStatus.CREATED);
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyEmail(@RequestParam String token){
        if(employeeService.verifyUser(token)) {
            return new ResponseEntity<>("Email verified", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Something went wrong!",HttpStatus.CONFLICT);
        }

    }
}
