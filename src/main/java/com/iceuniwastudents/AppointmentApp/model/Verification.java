package com.iceuniwastudents.AppointmentApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Timestamp;

@Entity
@Table(name = "verification")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
/*`id` VARCHAR(36) NOT NULL,
    `token` VARCHAR(70) NOT NULL,
    `created` TIMESTAMP NOT NULL,
        `employee_id` VARCHAR(36) NOT NULL,*/
public class Verification {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private String id;
    @Column(name = "token")
    private String token;
    @Column(name = "created")
    private Timestamp created;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
