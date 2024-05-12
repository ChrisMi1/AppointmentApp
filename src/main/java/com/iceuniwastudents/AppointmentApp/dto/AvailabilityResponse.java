package com.iceuniwastudents.AppointmentApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AvailabilityResponse {
    private LocalTime availableTime;
    private boolean isAvailable;

}
