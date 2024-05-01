package com.iceuniwastudents.AppointmentApp.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Entity
@Table(name = "appointment")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Appointment {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private String id;
    @Column(name = "appointment_number")
    private String appointmentNumber;
    @Column(name = "start")
    private LocalDateTime start;
    @Column(name = "end")
    private LocalDateTime end;
    @Column(name = "total_price")
    private long price;
    @Column(name = "canceled")
    private boolean cancelled;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @OneToOne
    @JoinColumn(name = "service_id")
    private Service service;
}
