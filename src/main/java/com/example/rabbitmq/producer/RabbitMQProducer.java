package com.example.rabbitmq.producer;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);
    @Value("${spring.rabbit.mq.exchange}")
    private String exchange;
    @Value("${spring.rabbit.mq.routing_key}")
    private String routingKey;
    private RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message) {
        LOGGER.info("sending message -> {}", message);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
