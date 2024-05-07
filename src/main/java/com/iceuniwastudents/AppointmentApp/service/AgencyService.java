package com.iceuniwastudents.AppointmentApp.service;

import com.iceuniwastudents.AppointmentApp.Repository.AgencyRepo;
import com.iceuniwastudents.AppointmentApp.dto.AgencyResponse;
import com.iceuniwastudents.AppointmentApp.model.Agency;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgencyService {
    private final AgencyRepo agencyRepo;
    public List<AgencyResponse> allAgencies(){
        List<Agency> agencies =  agencyRepo.findAll();
        return agencies.stream().map(this::mapToAgencyResponse).collect(Collectors.toList());
    }

    private AgencyResponse mapToAgencyResponse(Agency agency){
        return AgencyResponse.builder()
                .agencyName(agency.getAgencyName())
                .duration(agency.getDurationInMinutes())
                .price(agency.getPrice())
                .build();
    }
}
