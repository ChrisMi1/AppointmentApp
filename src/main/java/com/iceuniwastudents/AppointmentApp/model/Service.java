package com.iceuniwastudents.AppointmentApp.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "service")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Service {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private String id;
    @Column(name = "service_name")
    private String serviceName;
    @Column(name = "duration")
    private int durationInMinutes;
    @Column(name = "price")
    private long price;

}
