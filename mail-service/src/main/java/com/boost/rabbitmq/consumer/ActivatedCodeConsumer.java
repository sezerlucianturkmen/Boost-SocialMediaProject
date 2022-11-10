package com.boost.rabbitmq.consumer;

import com.boost.rabbitmq.model.ActivateRequestDto;
import com.boost.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActivatedCodeConsumer {

    private final EmailSenderService emailSenderService;

    @RabbitListener(queues ="${rabbitmq.queueActivated}")
    public void activatedMessage(ActivateRequestDto dto){
        log.info("Activate: {}", dto.toString());
        emailSenderService.sendActivateCode(dto);

    }


}
