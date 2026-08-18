package com.msjava.email.services;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msjava.email.enums.EmailStatus;
import com.msjava.email.models.EmailModel;
import com.msjava.email.repositories.EmailRepository;

@Service
public class EmailService {
    final EmailRepository emailRepository;
    final JavaMailSender emailSender;

    public EmailService(EmailRepository emailRepository, JavaMailSender emailSender) {
        this.emailRepository = emailRepository;
        this.emailSender = emailSender;
    }

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Transactional
    public EmailModel sendEmail(EmailModel emailModel) {
        try {
            emailModel.setSendDateTime(OffsetDateTime.now());
            emailModel.setEmailFrom(this.emailFrom);

            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom(this.emailFrom);
            msg.setTo(emailModel.getEmailTo());
            msg.setSubject(emailModel.getSubject());
            msg.setText(emailModel.getText());

            this.emailSender.send(msg);
            emailModel.setStatus(EmailStatus.SENT);
        } catch (Exception e) {
            emailModel.setStatus(EmailStatus.ERROR);
        }

        return this.emailRepository.save(emailModel);
    }
}
