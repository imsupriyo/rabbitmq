package com.example.rabbitmq.controller;

import com.example.rabbitmq.DTO.User;
import com.example.rabbitmq.producer.RabbitMQJsonProducer;
import com.example.rabbitmq.producer.RabbitMQProducer;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rabbitmq")
@AllArgsConstructor
public class RabbitMQController {
    private RabbitMQProducer rabbitMQProducer;
    private RabbitMQJsonProducer rabbitMQJsonProducer;

    @GetMapping
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
        rabbitMQProducer.sendMessage(message);
        return ResponseEntity.ok("message is sent to RabbitMQ...");
    }

    @PostMapping
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user) {
        rabbitMQJsonProducer.sendJsonMessage(user);
        return ResponseEntity.ok("message sent to RabbitMQ ->" + user.toString());
    }
}
