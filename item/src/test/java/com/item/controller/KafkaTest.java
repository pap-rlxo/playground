package com.item.controller;

import com.common.service.KafkaProducer;
import config.ItemTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import({ItemTestConfig.class})
public class KafkaTest {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Test
    public void test() {
        while (true) {
            kafkaProducer.create();
        }
    }
}
