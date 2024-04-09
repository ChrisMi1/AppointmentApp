package com.iceuniwastudents.AppointmentApp.Repository;

import com.iceuniwastudents.AppointmentApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InventoryRepo extends JpaRepository<User, UUID> {
}
