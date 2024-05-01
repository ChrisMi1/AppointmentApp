package com.iceuniwastudents.AppointmentApp.Repository;

import com.iceuniwastudents.AppointmentApp.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepo extends JpaRepository<Service,String> {
    Service findByServiceName(String serviceName);
}
