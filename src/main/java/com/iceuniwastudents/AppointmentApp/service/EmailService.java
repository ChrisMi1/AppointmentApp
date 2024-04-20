package com.iceuniwastudents.AppointmentApp.service;

import com.iceuniwastudents.AppointmentApp.exception.MailFailureException;
import com.iceuniwastudents.AppointmentApp.model.Verification;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmailService {
    @Value("${email.from}")
    private String fromAddress;
    @Value("${app.frontEnd.url}")
    private String url;
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender){
        this.javaMailSender=javaMailSender;
    }
        public void sendVerificationEmail(Verification verification) throws MailFailureException {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromAddress);
        simpleMailMessage.setTo(verification.getEmployee().getEmail());
        simpleMailMessage.setSubject("Verify your email to active your account");
        simpleMailMessage.setText("Follow the link bellow to verify your email .\n" +
                url+"api/admin/verify?token="+verification.getToken());
        try{
            javaMailSender.send(simpleMailMessage);
        }catch (MailException e ){
            throw new MailFailureException("Something went wrong! "+ e.getMessage());
        }

    }

}
