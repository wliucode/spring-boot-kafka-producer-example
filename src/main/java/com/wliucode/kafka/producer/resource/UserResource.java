package com.wliucode.kafka.producer.resource;

import com.wliucode.kafka.producer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class UserResource {

    private static final String TOPIC = "Kafka_Example";

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplateUser;

    @GetMapping("/publish/user/{name}")
    public String postUser(@PathVariable("name") final String name) {

        kafkaTemplateUser.send(TOPIC, new User(name, "Technology", 12000L));

        return name + " is Published " +  "successfully.";
    }

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/publish/{message}")
    public String post(@PathVariable("message") final String message) {

        kafkaTemplate.send(TOPIC, message);

        return message + " is Published " + "successfully.";
    }
}