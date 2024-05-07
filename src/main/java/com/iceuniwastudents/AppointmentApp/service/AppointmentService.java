package com.iceuniwastudents.AppointmentApp.service;

import com.iceuniwastudents.AppointmentApp.Repository.*;
import com.iceuniwastudents.AppointmentApp.dto.AppointmentBody;
import com.iceuniwastudents.AppointmentApp.dto.AppointmentResponse;
import com.iceuniwastudents.AppointmentApp.exception.MailFailureException;
import com.iceuniwastudents.AppointmentApp.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AppointmentService{
    private final AppointmentRepo appointmentRepo;
    private final EmployeeRepo employeeRepo;
    private final AgencyRepo agencyRepo;
    private final UserRepo userRepo;
    private final EmailService emailService;
    private final ScheduleRepo scheduleRepo;
    public AppointmentResponse bookAppointment(AppointmentBody appointmentBody) throws MailFailureException {
        Employee employee = employeeRepo.findByEmail(appointmentBody.getEmployeeEmail()).get();
        Agency agency = agencyRepo.findByAgencyName(appointmentBody.getAgencyName());
        LocalDateTime estimation = appointmentBody.getStart().plusMinutes(agency.getDurationInMinutes());
        User user = userRepo.save(appointmentBody.getUser());
        Appointment appointment = Appointment.builder()
                .start(appointmentBody.getStart())
                .end(estimation)
                .price(agency.getPrice())
                .cancelled(false)
                .user(user)
                .employee(employee)
                .agency(agency)
                .build();
        appointment = appointmentRepo.save(appointment);
        appointment.setAppointmentNumber(appointment.getId().substring(0,8));
        emailService.sendAppointmentCode(appointment);
        Schedule schedule = Schedule.builder()
                .start(appointment.getStart())
                .end(appointment.getEnd())
                .employee(appointment.getEmployee())
                .build();
        scheduleRepo.save(schedule);
        return AppointmentResponse.builder()
                .appointmentNumber(appointment.getId().substring(0,8))
                .message("There is your appointment number in case you want to modify your appointment.Also we send the code in your email check it!Thanks!")
                .success(true)
                .build();
    }

}
