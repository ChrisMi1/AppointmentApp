package com.iceuniwastudents.AppointmentApp.service;

import com.iceuniwastudents.AppointmentApp.Repository.AgencyRepo;
import com.iceuniwastudents.AppointmentApp.model.Agency;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgencyService {
    private final AgencyRepo agencyRepo;
    public List<Agency> allAgencies(){
        return agencyRepo.findAll();
    }
}
