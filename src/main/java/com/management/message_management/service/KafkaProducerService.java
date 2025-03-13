package com.management.message_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    private static final String topic = "user_topic";

    public void sendMessage(String message){
        kafkaTemplate.send(topic,message);
    }
}
