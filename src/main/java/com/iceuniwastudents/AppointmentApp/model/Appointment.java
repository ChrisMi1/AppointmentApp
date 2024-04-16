package com.iceuniwastudents.AppointmentApp.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/*CREATE TABLE `appointment`(
        `id` VARCHAR(36) NOT NULL,
    `appointment_number` VARCHAR(8) NOT NULL ,
    `from` DATETIME NOT NULL,
        `to` DATETIME NOT NULL,
        `total_price` DECIMAL(10,2) NOT NULL ,
    `canceled` BOOL NOT NULL,
        `user_id` CHAR(36) NOT NULL,
    `employee_id`  VARCHAR(36) NOT NULL,
primary key(`id`),
FOREIGN KEY(`employee_id`) REFERENCES `employee`(`id`),
FOREIGN KEY(`user_id`) REFERENCES `user`(`id`)
        )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;*/
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
    @Column(name = "from")
    private LocalDateTime from;
    @Column(name = "to")
    private LocalDateTime to;
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
