package com.iceuniwastudents.AppointmentApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AppointmentDetails {
    private String userName;
    private LocalDateTime start;
    private double price;
    private String employeeName;
    private String agencyName;
}
