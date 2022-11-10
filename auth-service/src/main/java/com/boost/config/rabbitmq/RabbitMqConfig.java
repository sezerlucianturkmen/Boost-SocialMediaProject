package com.boost.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Value("${rabbitmq.exchange-auth}")
    private String exchange;
    @Value("${rabbitmq.bindingKey}")
    private String bindingKeyActivatedCode;
    @Value("${rabbitmq.queueActivated}")
    private String queueNameActivatedCode;

    @Bean
    DirectExchange exchangeAuth(){

        return new DirectExchange(exchange);
    }
    @Bean
    Queue activatedCodeQueue(){
        return new Queue(bindingKeyActivatedCode);
    }
    @Bean
    public Binding bindingActivatedCode(final Queue activatedCodeQueue,final DirectExchange exchangeAuth){
        return BindingBuilder.bind(activatedCodeQueue).to(exchangeAuth).with(bindingKeyActivatedCode);
    }


}
