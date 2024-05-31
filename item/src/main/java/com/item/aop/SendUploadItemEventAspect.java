package com.item.aop;

import com.common.domain.Item.Book;
import com.common.domain.Item.Movie;
import com.common.domain.ItemType;
import com.common.service.KafkaProducer;
import com.common.service.KafkaTopic;
import com.common.service.KafkaUploadItemEventForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@RequiredArgsConstructor
@Slf4j
public class SendUploadItemEventAspect {

    private final KafkaProducer kafkaProducer;

    @AfterReturning(value = "@within(com.item.aop.SendUploadItemEvent)", returning = "target")
    public void sendUploadItemEvent(Object target) {
        try {
            switch (target) {
                case Movie movie -> kafkaProducer.create(KafkaTopic.UPLOAD, new KafkaUploadItemEventForm(movie.getId(), ItemType.MOVIE));
                case Book book -> kafkaProducer.create(KafkaTopic.UPLOAD, new KafkaUploadItemEventForm(book.getId(), ItemType.BOOK));
                default -> throw new IllegalArgumentException("Unknown type: " + target.getClass());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

//    private Long getTargetId(Object target) throws NoSuchFieldException, IllegalAccessException {
//        Field field = target.getClass().getSuperclass().getSuperclass().getDeclaredField("id");
//        field.setAccessible(true);
//        return (Long) field.get(target);
//    }
}
