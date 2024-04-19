package com.iceuniwastudents.AppointmentApp.controller;

import com.iceuniwastudents.AppointmentApp.exception.EmailAlreadyExists;
import com.iceuniwastudents.AppointmentApp.exception.MailFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    public ResponseEntity<String> handleException(EmailAlreadyExists emailAlreadyExists){
        return new ResponseEntity<>(emailAlreadyExists.getMessage(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler
    public ResponseEntity<String> handleException(MailFailureException mailFailureException){
        return new ResponseEntity<>(mailFailureException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
