package com.iceuniwastudents.AppointmentApp.Repository;

import com.iceuniwastudents.AppointmentApp.model.Agency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgencyRepo extends JpaRepository<Agency,String> {
    Agency findByAgencyName(String agencyName);
}
