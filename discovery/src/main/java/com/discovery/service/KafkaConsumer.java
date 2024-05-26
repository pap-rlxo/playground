package com.discovery.service;

import com.common.domain.Item.Movie;
import com.common.service.ClientService;
import com.common.service.KafkaUploadItemEventForm;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import static com.common.service.KafkaTopic.UPLOAD;

@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    static final String ITEM_BASE_URL = "http://localhost:8082/internal/items/movies";

    private final ClientService clientService;

    @KafkaListener(topics = UPLOAD, groupId = "discovery_consumer", containerFactory = "kafkaUploadItemListenerContainerFactory")
    private void listener1(ConsumerRecord<String, KafkaUploadItemEventForm> consumerRecord, Acknowledgment acknowledgment) throws Exception {
        switch (consumerRecord.value().getType()) {
            case MOVIE -> System.out.println("MOVIE");
            case BOOK -> System.out.println("BOOK");
        } ;
//        Movie movie = clientService.get(ITEM_BASE_URL + "/" + consumerRecord.value().getId(), Movie.class).getBody();
        acknowledgment.acknowledge();
    }
}
