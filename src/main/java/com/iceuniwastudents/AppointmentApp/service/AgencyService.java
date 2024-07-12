package com.iceuniwastudents.AppointmentApp.service;

import com.iceuniwastudents.AppointmentApp.Repository.AgencyRepo;
import com.iceuniwastudents.AppointmentApp.Repository.ScheduleRepo;
import com.iceuniwastudents.AppointmentApp.dto.AgencyResponse;
import com.iceuniwastudents.AppointmentApp.dto.AvailabilityBody;
import com.iceuniwastudents.AppointmentApp.dto.AvailabilityResponse;
import com.iceuniwastudents.AppointmentApp.model.Agency;
import com.iceuniwastudents.AppointmentApp.model.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgencyService {
    private final AgencyRepo agencyRepo;
    private final ScheduleRepo scheduleRepo;
    public List<AgencyResponse> allAgencies(){
        List<Agency> agencies =  agencyRepo.findAll();
        return agencies.stream().map(this::mapToAgencyResponse).collect(Collectors.toList());
    }

    private AgencyResponse mapToAgencyResponse(Agency agency){
        return AgencyResponse.builder()
                .id(agency.getId())
                .agencyName(agency.getAgencyName())
                .duration(agency.getDurationInMinutes())
                .price(agency.getPrice())
                .build();
    }

    public List<AvailabilityResponse> availability(AvailabilityBody availabilityBody){
        Optional<List<Schedule>> scheduleList = scheduleRepo.findByEmployeeIdAndDate(availabilityBody.getEmployeeId(), availabilityBody.getDate());
        return findTheAvailableHours(scheduleList.get()).stream().filter(AvailabilityResponse::isAvailable).collect(Collectors.toList());
    }

    private List<AvailabilityResponse> findTheAvailableHours(List<Schedule> scheduleList) {
        List<AvailabilityResponse> availabilityResponseList =  new ArrayList<>(List.of(
                new AvailabilityResponse(LocalTime.of(9, 0),true),
                new AvailabilityResponse(LocalTime.of(9, 30),true),
                new AvailabilityResponse(LocalTime.of(10, 0),true),
                new AvailabilityResponse(LocalTime.of(10, 30),true),
                new AvailabilityResponse(LocalTime.of(11, 0),true),
                new AvailabilityResponse(LocalTime.of(11, 30),true),
                new AvailabilityResponse(LocalTime.of(12, 0),true),
                new AvailabilityResponse(LocalTime.of(12, 30),true),
                new AvailabilityResponse(LocalTime.of(13, 0),true),
                new AvailabilityResponse(LocalTime.of(13, 30),true),
                new AvailabilityResponse(LocalTime.of(14, 0),true),
                new AvailabilityResponse(LocalTime.of(14, 30),true),
                new AvailabilityResponse(LocalTime.of(15, 0),true),
                new AvailabilityResponse(LocalTime.of(15, 30),true),
                new AvailabilityResponse(LocalTime.of(16, 0),true),
                new AvailabilityResponse(LocalTime.of(16, 30),true),
                new AvailabilityResponse(LocalTime.of(17, 0),true),
                new AvailabilityResponse(LocalTime.of(17, 30),true)
        ));
        if(!scheduleList.isEmpty()){
            for (Schedule schedule : scheduleList) {
                for (AvailabilityResponse availabilityResponse : availabilityResponseList) {
                    if (schedule.getStart().getHour() == availabilityResponse.getAvailableTime().getHour() && schedule.getStart().getMinute() == availabilityResponse.getAvailableTime().getMinute()) {
                        availabilityResponse.setAvailable(false);
                    }
                }
            }

        }

        return availabilityResponseList;
    }
}
