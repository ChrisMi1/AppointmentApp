package com.iceuniwastudents.AppointmentApp.controller;

import com.iceuniwastudents.AppointmentApp.dto.LoginResponse;
import com.iceuniwastudents.AppointmentApp.dto.RegisterResponse;
import com.iceuniwastudents.AppointmentApp.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    public ResponseEntity<RegisterResponse> handleExceptionRegister(EmailAlreadyExists emailAlreadyExists){
        RegisterResponse registerResponse = RegisterResponse.builder()
                .success(false)
                .failureReason(emailAlreadyExists.getMessage())
                .build();
        return new ResponseEntity<>(registerResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler
    public ResponseEntity<RegisterResponse> handleExceptionRegister(MailFailureException mailFailureException){
        RegisterResponse registerResponse = RegisterResponse.builder()
                .success(false)
                .failureReason(mailFailureException.getMessage())
                .build();
        return new ResponseEntity<>(registerResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler
    public ResponseEntity<LoginResponse> handleExceptionLogin(EmailNotFound emailNotFound){
        LoginResponse loginResponse = LoginResponse.builder()
                .success(false)
                .failureReason(emailNotFound.getMessage())
                .build();
        return new ResponseEntity<>(loginResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<LoginResponse> handleExceptionLogin(UserNotVerified userNotVerified){
        LoginResponse loginResponse = LoginResponse.builder()
                .success(false)
                .failureReason(userNotVerified.getMessage())
                .build();
        return new ResponseEntity<>(loginResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<String> handleExceptionAppointmentNumber(AppointmentNumberNotFound appointmentNumberNotFound){
        return new ResponseEntity<>(appointmentNumberNotFound.getMessage(), HttpStatus.BAD_REQUEST);
    }



}
