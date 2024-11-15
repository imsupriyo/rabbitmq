package com.example.rabbitmq.producer;

import com.example.rabbitmq.DTO.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {
    private final static Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);
    @Value("${spring.rabbit.mq.exchange}")
    private String exchange;
    @Value("${spring.rabbit.mq.json.routing_key}")
    private String jsonRoutingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void sendJsonMessage(User user) {
        LOGGER.info("sending message -> {}", user.toString());
        rabbitTemplate.convertAndSend(exchange, jsonRoutingKey, user);
    }
}
