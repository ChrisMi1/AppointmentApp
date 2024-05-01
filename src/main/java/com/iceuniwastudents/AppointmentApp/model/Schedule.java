package com.iceuniwastudents.AppointmentApp.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
@Entity
@Table(name = "schedule")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Schedule {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private String id;
    @Column(name = "start")
    private LocalDateTime start;
    @Column(name = "end")
    private LocalDateTime end;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
