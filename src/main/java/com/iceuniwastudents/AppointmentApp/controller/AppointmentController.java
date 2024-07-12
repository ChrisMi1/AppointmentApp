package com.iceuniwastudents.AppointmentApp.controller;

import com.iceuniwastudents.AppointmentApp.dto.AppointmentBody;
import com.iceuniwastudents.AppointmentApp.dto.AppointmentDetails;
import com.iceuniwastudents.AppointmentApp.dto.AppointmentResponse;
import com.iceuniwastudents.AppointmentApp.dto.UpdateAppointmentBody;
import com.iceuniwastudents.AppointmentApp.exception.AppointmentNumberNotFound;
import com.iceuniwastudents.AppointmentApp.exception.MailFailureException;
import com.iceuniwastudents.AppointmentApp.model.Appointment;
import com.iceuniwastudents.AppointmentApp.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService){
        this.appointmentService=appointmentService;
    }
    @PostMapping
    public ResponseEntity<AppointmentResponse> bookAppointment(@RequestBody AppointmentBody appointmentBody) throws MailFailureException {
        return new ResponseEntity<>(appointmentService.bookAppointment(appointmentBody), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<AppointmentDetails> getAppointment(@RequestParam String appointmentNumber) throws AppointmentNumberNotFound {
        return new ResponseEntity<>(appointmentService.getAppointmentDetails(appointmentNumber),HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<String> cancelAppointment(@RequestParam String appointmentNumber) throws AppointmentNumberNotFound{
        return new  ResponseEntity<>(appointmentService.deleteAppointment(appointmentNumber),HttpStatus.OK);
    }
}
