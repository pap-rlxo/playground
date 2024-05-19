package com.common.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "upload", groupId = "group_1")
    public void listener(Object data) {
        System.out.println(data);
    }
}
