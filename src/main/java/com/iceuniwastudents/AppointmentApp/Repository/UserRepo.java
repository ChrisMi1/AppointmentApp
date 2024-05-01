package com.iceuniwastudents.AppointmentApp.Repository;

import com.iceuniwastudents.AppointmentApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,String> {
}
