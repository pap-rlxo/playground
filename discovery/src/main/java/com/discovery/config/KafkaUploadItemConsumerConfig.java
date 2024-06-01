package com.discovery.config;

import com.common.service.KafkaUploadItemEventForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.util.backoff.FixedBackOff;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class KafkaUploadItemConsumerConfig {

    public static final BiFunction<ConsumerRecord<?, ?>, Exception, TopicPartition>
            DEAD_TOPIC_DESTINATION_RESOLVER = (cr, e) -> {
        log.error("[Send to dead letter topic]: {} - [Exception message] : {}" , cr.topic(), e);
        return new TopicPartition("dlt-" + cr.topic(), cr.partition());
    };

    final KafkaTemplate<String, Object> kafkaTemplate;

    public ConsumerFactory<String, KafkaUploadItemEventForm> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094");
        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        JsonDeserializer<KafkaUploadItemEventForm> deserializer = new JsonDeserializer<>(KafkaUploadItemEventForm.class, false);
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaUploadItemEventForm> kafkaUploadItemListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, KafkaUploadItemEventForm> factory = new ConcurrentKafkaListenerContainerFactory<>();
        // 기본 팩토리 설정
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3); // 하나의 리스너에서 스레드 3개로 처리.
        // 리스너에서 acknowledgment가 호출될 때 마다, 수동 커밋
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        factory.setCommonErrorHandler(errorHandler());
        return factory;
    }

    @Bean
    public DefaultErrorHandler errorHandler() {
        DefaultErrorHandler errorHandler = new DefaultErrorHandler(
                new DeadLetterPublishingRecoverer(kafkaTemplate, DEAD_TOPIC_DESTINATION_RESOLVER),
                new FixedBackOff(1000, 3));
        errorHandler.setCommitRecovered(true);
        errorHandler.setAckAfterHandle(true);
        errorHandler.setResetStateOnRecoveryFailure(true);
        return errorHandler;
    }
}
