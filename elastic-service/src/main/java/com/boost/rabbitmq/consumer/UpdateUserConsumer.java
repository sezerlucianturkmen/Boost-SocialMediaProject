package com.boost.rabbitmq.consumer;
import com.boost.rabbitmq.model.UpdateUsernameEmail;
import com.boost.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateUserConsumer {

    private final UserProfileService userProfileService;

   @RabbitListener(queues = "${rabbitmq.userUpdateQueueElastic}")
    public void updateUser(UpdateUsernameEmail model) {
        log.info("User : {}", model.toString());
        userProfileService.updateUser(model);
    }


}