package com.iceuniwastudents.AppointmentApp.controller;

import com.iceuniwastudents.AppointmentApp.dto.AgencyResponse;
import com.iceuniwastudents.AppointmentApp.dto.AvailabilityBody;
import com.iceuniwastudents.AppointmentApp.dto.AvailabilityResponse;
import com.iceuniwastudents.AppointmentApp.model.Agency;
import com.iceuniwastudents.AppointmentApp.service.AgencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/agencies")
public class AgencyController {
    private final AgencyService agencyService;
    @GetMapping
    public ResponseEntity<List<AgencyResponse>> getAgencies(){
        return new ResponseEntity<>(agencyService.allAgencies(), HttpStatus.OK);
    }

    @GetMapping("/availability")
    public ResponseEntity<List<AvailabilityResponse>> getAvailability(@RequestBody AvailabilityBody availabilityBody){
        return new ResponseEntity<>(agencyService.availability(availabilityBody),HttpStatus.OK);
    }

}
