package com.iceuniwastudents.AppointmentApp.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

/*CREATE TABLE `schedule`(
        `id` VARCHAR(36) NOT NULL ,
    `from` DATETIME NOT NULL,
        `to` DATETIME NOT NULL,
        `employee_id` VARCHAR(36) NOT NULL,
primary key(`id`),
FOREIGN KEY(`employee_id`) REFERENCES `employee`(`id`)
        )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;*/
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
    @Column(name = "from")
    private LocalDateTime from;
    @Column(name = "to")
    private LocalDateTime to;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;



}
