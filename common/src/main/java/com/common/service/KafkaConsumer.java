package com.common.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    static int count = 0;

    @KafkaListener(topics = "upload", groupId = "group_1")
    public void listener1(Object data) {

    }
}
