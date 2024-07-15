package com.iceuniwastudents.AppointmentApp.controller;

import com.iceuniwastudents.AppointmentApp.dto.LoginBody;
import com.iceuniwastudents.AppointmentApp.dto.LoginResponse;
import com.iceuniwastudents.AppointmentApp.dto.RegisterBody;
import com.iceuniwastudents.AppointmentApp.dto.RegisterResponse;
import com.iceuniwastudents.AppointmentApp.exception.EmailAlreadyExists;
import com.iceuniwastudents.AppointmentApp.exception.EmailNotFound;
import com.iceuniwastudents.AppointmentApp.exception.MailFailureException;
import com.iceuniwastudents.AppointmentApp.exception.UserNotVerified;
import com.iceuniwastudents.AppointmentApp.model.Employee;
import com.iceuniwastudents.AppointmentApp.model.Schedule;
import com.iceuniwastudents.AppointmentApp.model.Verification;
import com.iceuniwastudents.AppointmentApp.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerEmployee(@RequestBody RegisterBody registerBody) throws EmailAlreadyExists, MailFailureException {
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

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginEmployee(@RequestBody LoginBody loginBody) throws EmailNotFound, UserNotVerified, MailFailureException {
        return new ResponseEntity<>(employeeService.login(loginBody),HttpStatus.OK);
    }

    @GetMapping("/schedule")
    public ResponseEntity<List<Schedule>> getSchedules(@AuthenticationPrincipal Employee employee){
        return new ResponseEntity<>(employeeService.getScheduleByEmployeeId(employee),HttpStatus.OK);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String employeeId){
        return new ResponseEntity<>(employeeService.deleteEmployeeById(employeeId),HttpStatus.OK);
    }
}
