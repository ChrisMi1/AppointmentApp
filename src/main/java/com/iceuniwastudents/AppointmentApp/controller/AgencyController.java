package com.iceuniwastudents.AppointmentApp.controller;

import com.iceuniwastudents.AppointmentApp.model.Agency;
import com.iceuniwastudents.AppointmentApp.service.AgencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/agencies")
public class AgencyController {
    private final AgencyService agencyService;
    @GetMapping
    public ResponseEntity<List<Agency>> getAgencies(){
        return new ResponseEntity<>(agencyService.allAgencies(), HttpStatus.OK);
    }

}
