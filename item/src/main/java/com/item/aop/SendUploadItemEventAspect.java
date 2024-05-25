package com.item.aop;

import com.common.service.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@RequiredArgsConstructor
public class SendUploadItemEventAspect {

    private final KafkaProducer kafkaProducer;

    @AfterReturning("@within(com.item.aop.SendUploadItemEvent)")
    public void sendUploadItemEvent() {
        while (true) {
            kafkaProducer.create();
        }
    }
}
