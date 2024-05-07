package com.iceuniwastudents.AppointmentApp.dto;

import com.iceuniwastudents.AppointmentApp.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AppointmentBody {
    private LocalDateTime start;
    private User user;
    private String employeeEmail;
    private String agencyName;
}
