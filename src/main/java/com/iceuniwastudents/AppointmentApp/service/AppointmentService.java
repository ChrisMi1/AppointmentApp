package com.iceuniwastudents.AppointmentApp.service;

import com.iceuniwastudents.AppointmentApp.Repository.AppointmentRepo;
import com.iceuniwastudents.AppointmentApp.Repository.EmployeeRepo;
import com.iceuniwastudents.AppointmentApp.Repository.ServiceRepo;
import com.iceuniwastudents.AppointmentApp.Repository.UserRepo;
import com.iceuniwastudents.AppointmentApp.dto.AppointmentBody;
import com.iceuniwastudents.AppointmentApp.dto.AppointmentResponse;
import com.iceuniwastudents.AppointmentApp.model.Appointment;
import com.iceuniwastudents.AppointmentApp.model.Employee;
import com.iceuniwastudents.AppointmentApp.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AppointmentService{
    private final AppointmentRepo appointmentRepo;
    private final EmployeeRepo employeeRepo;
    private final ServiceRepo serviceRepo;
    private final UserRepo userRepo;
    public AppointmentResponse bookAppointment(AppointmentBody appointmentBody){
        Employee employee = employeeRepo.findByEmail(appointmentBody.getEmployeeEmail()).get();
        com.iceuniwastudents.AppointmentApp.model.Service service = serviceRepo.findByServiceName(appointmentBody.getServiceName());
        LocalDateTime estimation = appointmentBody.getStart().plusMinutes(service.getDurationInMinutes());
        User user = userRepo.save(appointmentBody.getUser());
        Appointment appointment = Appointment.builder()
                .start(appointmentBody.getStart())
                .end(estimation)
                .price(service.getPrice())
                .cancelled(false)
                .user(user)
                .employee(employee)
                .service(service)
                .build();
        appointment = appointmentRepo.save(appointment);

        return AppointmentResponse.builder()
                .appointmentNumber(appointment.getId().substring(0,8))
                .message("There is your appointment number in case you want to modify your appointment.Thanks!")
                .success(true)
                .build();
    }

}