package com.msjava.email.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.msjava.email.dtos.EmailRecordDto;

@Component
public class EmailConsumer {
    @RabbitListener(queues = "${broker.queue.email.name}")
    public void receiveMessage(@Payload EmailRecordDto msg) {
        System.out.println(msg.emailTo());
    }
}
