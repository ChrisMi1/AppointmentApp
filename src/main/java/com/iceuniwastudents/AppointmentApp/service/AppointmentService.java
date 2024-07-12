package com.iceuniwastudents.AppointmentApp.service;

import com.iceuniwastudents.AppointmentApp.Repository.*;
import com.iceuniwastudents.AppointmentApp.dto.AppointmentBody;
import com.iceuniwastudents.AppointmentApp.dto.AppointmentDetails;
import com.iceuniwastudents.AppointmentApp.dto.AppointmentResponse;
import com.iceuniwastudents.AppointmentApp.exception.AppointmentNumberNotFound;
import com.iceuniwastudents.AppointmentApp.exception.MailFailureException;
import com.iceuniwastudents.AppointmentApp.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

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
        Schedule schedule = Schedule.builder()
                .date(appointmentBody.getStart().toLocalDate())
                .start(appointmentBody.getStart().toLocalTime())
                .end(estimation.toLocalTime())
                .employee(employee)
                .build();

        Appointment appointment = Appointment.builder()
                .start(appointmentBody.getStart())
                .end(estimation)
                .price(agency.getPrice())
                .cancelled(false)
                .user(user)
                .employee(employee)
                .agency(agency)
                .schedule(schedule)
                .build();
        appointment = appointmentRepo.save(appointment);
        appointment.setAppointmentNumber(appointment.getId().substring(0,8));
        emailService.sendAppointmentCode(appointment);
        return AppointmentResponse.builder()
                .appointmentNumber(appointment.getId().substring(0,8))
                .message("There is your appointment number in case you want to modify your appointment.Also we send the code in your email check it!Thanks!")
                .success(true)
                .build();
    }

    public AppointmentDetails getAppointmentDetails(String appointmentNumber) throws AppointmentNumberNotFound{
        Optional<Appointment> appointment= appointmentRepo.getAppointmentByAppointmentNumber(appointmentNumber);
        if(appointment.isEmpty()){
            throw new AppointmentNumberNotFound("The number you provide doesn't exist");
        }
        return AppointmentDetails.builder()
                .start(appointment.get().getStart())
                .userName(appointment.get().getUser().getFirstName() + " " + appointment.get().getUser().getLastName())
                .price(appointment.get().getPrice())
                .employeeName(appointment.get().getEmployee().getFirstName() + " " + appointment.get().getEmployee().getLastName())
                .agencyName(appointment.get().getAgency().getAgencyName())
                .build();
    }
    public String deleteAppointment(String appointmentNumber) throws AppointmentNumberNotFound{
        Optional<Appointment> appointment= appointmentRepo.getAppointmentByAppointmentNumber(appointmentNumber);
        if(appointment.isEmpty()){
            throw new AppointmentNumberNotFound("The number you provide doesn't exist");
        }else{
            appointmentRepo.delete(appointment.get());
            return "Your appointment was cancelled successfully";
        }

    }

}
