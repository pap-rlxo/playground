package com.discovery.config;

import com.common.service.KafkaUploadItemEventForm;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaUploadItemConsumerConfig {

    public ConsumerFactory<String, KafkaUploadItemEventForm> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "discovery_consumer");
        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
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
        return factory;
    }
}
