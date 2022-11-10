package com.boost.rabbitmq.procedure;

import com.boost.rabbitmq.model.ActivateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivatedCodeProducer {
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange-auth}")
    private String exchange;
    @Value("${rabbitmq.bindingKey}")
    private String bindingKeyActivatedCode;

    public void sendActivatedCode(ActivateRequestDto dto){

        rabbitTemplate.convertAndSend(exchange,bindingKeyActivatedCode,dto);
    }
}
