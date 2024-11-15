package com.example.rabbitmq.consumer;

import com.example.rabbitmq.DTO.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonConsumer.class);

    @RabbitListener(queues = {"${spring.rabbit.mq.queue.json}"})
    public void receiveJsonMessage(User user) {
        LOGGER.info("received message -> {}", user.toString());
    }
}
