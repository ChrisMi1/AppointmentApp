package com.iceuniwastudents.AppointmentApp.Repository;

import com.iceuniwastudents.AppointmentApp.model.Employee;
import com.iceuniwastudents.AppointmentApp.model.Verification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationRepo extends JpaRepository<Verification,String> {
    Optional<Verification> findByToken(String token);

    void deleteByEmployee(Employee employee);
}
