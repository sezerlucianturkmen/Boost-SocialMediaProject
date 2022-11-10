package com.boost.rabbitmq.procedure;

import com.boost.rabbitmq.model.UpdateUsernameEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class UpdateUserProcedure {
    @Value("${rabbitmq.exchange-user}")
    private String directExchange;


    @Value("${rabbitmq.userUpdateBindingKey}")
    private String userUpdateBindingKey;

    private final RabbitTemplate rabbitTemplate;


    public void sendUpdateUser(UpdateUsernameEmail model) {

        rabbitTemplate.convertAndSend(directExchange, userUpdateBindingKey, model);

    }

}
