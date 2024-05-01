package com.iceuniwastudents.AppointmentApp.Repository;

import com.iceuniwastudents.AppointmentApp.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepo extends JpaRepository<Appointment,String> {

}
