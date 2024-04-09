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
	@Bean
	public CommandLineRunner loadData(InventoryRepo inventoryRepository){
		return args -> {
			User user = new User();
			user.setFirstName("Christos Μαλτεζοσ");
			user.setPassword("pasdapsd");
			user.setEmail("pasdapsd");
			user.setPhoneNumber("pasdapsd");
			user.setLastName("pasdapsd");
			user.setRole("ROLE_USER");
			inventoryRepository.save(user);

			List<User> userSet= inventoryRepository.findAll();
			for(var user1:userSet){
				System.out.println(user1);
			}
		};
	}
}
