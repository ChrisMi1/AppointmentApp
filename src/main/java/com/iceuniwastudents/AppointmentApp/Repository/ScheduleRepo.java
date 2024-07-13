package com.iceuniwastudents.AppointmentApp.Repository;

import com.iceuniwastudents.AppointmentApp.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepo extends JpaRepository<Schedule,String> {
    Optional<List<Schedule>> findByEmployeeIdAndDate(String employeeId, LocalDate date);
    Optional<List<Schedule>> findByEmployeeId(String employeeId);
}
