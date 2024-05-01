package com.iceuniwastudents.AppointmentApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AppointmentResponse {
    private String appointmentNumber;
    private String message;
    private boolean success;
}
