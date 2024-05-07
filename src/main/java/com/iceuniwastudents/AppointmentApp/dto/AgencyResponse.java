package com.iceuniwastudents.AppointmentApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AgencyResponse {
    private String agencyName;
    private int duration;
    private double price;
}
