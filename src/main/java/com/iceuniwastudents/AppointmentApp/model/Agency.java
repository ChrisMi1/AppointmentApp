package com.iceuniwastudents.AppointmentApp.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "agency")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Agency {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private String id;
    @Column(name = "agency_name")
    private String agencyName;
    @Column(name = "duration")
    private int durationInMinutes;
    @Column(name = "price")
    private long price;

}
