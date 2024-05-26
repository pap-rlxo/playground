package com.item.aop;

import com.common.service.KafkaProducer;
import com.common.service.KafkaTopic;
import com.common.service.KafkaUploadItemEventForm;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
@Aspect
@RequiredArgsConstructor
public class SendUploadItemEventAspect {

    private final KafkaProducer kafkaProducer;

    @AfterReturning(value = "@within(com.item.aop.SendUploadItemEvent)", returning = "target")
    public void sendUploadItemEvent(Object target) {
        try {
            Field field = target.getClass().getSuperclass().getSuperclass().getDeclaredField("id");
            field.setAccessible(true);
            Long id = (Long) field.get(target);
            KafkaUploadItemEventForm kafkaUploadItemEventForm = new KafkaUploadItemEventForm(id);
            kafkaProducer.create(KafkaTopic.UPLOAD, kafkaUploadItemEventForm);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
