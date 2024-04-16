package com.iceuniwastudents.AppointmentApp;

import com.iceuniwastudents.AppointmentApp.Repository.InventoryRepo;
import com.iceuniwastudents.AppointmentApp.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AppointmentAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppointmentAppApplication.class, args);
	}


}
