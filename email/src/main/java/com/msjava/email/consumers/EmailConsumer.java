package com.msjava.email.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {
    @RabbitListener(queues = "${broker.queue.email.name}")
    public void receiveMessage(@Payload String msg) {
        System.out.println(msg);
    }
}
