package com.iceuniwastudents.AppointmentApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AvailabilityBody {
    private String employeeId;
    private LocalDate date;
    //private String agencyId;

}
