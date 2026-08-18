package com.msjava.email.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.msjava.email.dtos.EmailRecordDto;
import com.msjava.email.models.EmailModel;
import com.msjava.email.services.EmailService;

@Component
public class EmailConsumer {
    final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void receiveMessage(@Payload EmailRecordDto msg) {
        var emailModel = new EmailModel();
        BeanUtils.copyProperties(msg, emailModel);

        this.emailService.sendEmail(emailModel);
    }
}
