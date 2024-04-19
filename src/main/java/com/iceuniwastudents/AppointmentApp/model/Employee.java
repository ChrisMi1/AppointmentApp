package com.iceuniwastudents.AppointmentApp.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="employee")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Employee {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private String id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "password")
    private String password;
    @Column(name = "photo")
    private String photo;
    @Column(name = "role")
    private String role;
    @OneToMany(mappedBy = "employee",cascade = CascadeType.REMOVE)
    List<Schedule> scheduleList;
    @OneToMany(mappedBy = "employee",cascade = CascadeType.REMOVE)
    List<Appointment> appointments;
    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    List<Verification> verificationTokens;
    @Column(name = "email_verified")
    private boolean emailVerified;

    public void addVerificationToken(Verification verification){
        if(verificationTokens==null){
            verificationTokens=new ArrayList<>();
        }
        verificationTokens.add(verification);
    }
}
