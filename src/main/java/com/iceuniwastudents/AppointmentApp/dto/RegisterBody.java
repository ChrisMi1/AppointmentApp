package com.iceuniwastudents.AppointmentApp.dto;

import lombok.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterBody {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
}
