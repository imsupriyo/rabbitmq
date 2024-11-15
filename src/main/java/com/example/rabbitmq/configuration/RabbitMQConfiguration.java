package com.example.rabbitmq.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
    @Value("${spring.rabbit.mq.queue}")
    private String queue;
    @Value("${spring.rabbit.mq.exchange}")
    private String exchange;
    @Value("${spring.rabbit.mq.routing_key}")
    private String routingKey;

    @Value("${spring.rabbit.mq.queue.json}")
    private String jsonQueue;
    @Value("${spring.rabbit.mq.json.routing_key}")
    private String jsonRoutingKey;

    // spring bean for rabbitmq queue
    @Bean
    public Queue queue() {
        return new Queue(queue);
    }

    // spring bean for rabbitmq jsonQueue
    @Bean
    public Queue jsonQueue() {
        return new Queue(jsonQueue);
    }

    // spring bean for rabbitmq exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    // Binding between queue and exchange using routingKey
    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routingKey);
    }

    // Binding between jsonQueue and exchange using jsonRoutingKey
    @Bean
    public Binding jsonBinding() {
        return BindingBuilder
                .bind(jsonQueue())
                .to(exchange())
                .with(jsonRoutingKey);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
