package com.msjava.user.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.msjava.user.dtos.EmailDto;
import com.msjava.user.models.UserModel;

@Component
public class UserProducer {
    final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${broker.queue.email.name}")
    private String routingKey;

    public void sendEmail(UserModel userModel) {
        var emailDto = new EmailDto(
                userModel.getId(),
                userModel.getEmail(),
                "Novo usuário cadastrado!",

                userModel.getName() + " acabou de se cadastrar!");

        rabbitTemplate.convertAndSend(this.routingKey, emailDto);
    }
}
