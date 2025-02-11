package com.anemona.consumer2.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

@Configuration
public class Consumer2Config {
    
    public static final String QUEUE_HISTORICO = "historico.queue";

    @Bean
    public Queue HistoricoQueue() {
        return new Queue(QUEUE_HISTORICO, true);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
