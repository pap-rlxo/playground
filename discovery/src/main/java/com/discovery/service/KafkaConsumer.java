package com.discovery.service;

import com.common.domain.Item.Book;
import com.common.domain.Item.Movie;
import com.common.domain.ItemType;
import com.common.domain.user.User;
import com.common.dto.UpdateBookForm;
import com.common.dto.UpdateMovieForm;
import com.common.repository.user.UserRepository;
import com.common.service.ClientService;
import com.common.service.KafkaUploadItemEventForm;
import com.discovery.service.internal.InternalDiscoveryItemService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.common.service.KafkaTopic.UPLOAD;

@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final InternalDiscoveryItemService internalDiscoveryItemService;
    private final UserRepository userRepository;

    private static final Map<String, Class<?>> ITEM_TYPE_MAP = new HashMap<>();
    private static final Map<Class<?>, String> ITEM_URL_MAP = new HashMap<>();

    static {
        ITEM_TYPE_MAP.put(ItemType.MOVIE.toString(), Movie.class);
        ITEM_TYPE_MAP.put(ItemType.BOOK.toString(), Book.class);
        ITEM_URL_MAP.put(Movie.class, "http://localhost:8082/internal/items/movies");
        ITEM_URL_MAP.put(Book.class, "http://localhost:8082/internal/items/books");
    }

    private final ClientService clientService;

    @KafkaListener(topics = UPLOAD, groupId = "discovery_consumer", containerFactory = "kafkaUploadItemListenerContainerFactory")
    private void listener(ConsumerRecord<String, KafkaUploadItemEventForm> consumerRecord, Acknowledgment acknowledgment) throws Exception {
        upload(consumerRecord.value().getId(), consumerRecord.value().getType().toString());
        acknowledgment.acknowledge();
    }

    private void upload(Long id, String itemType) throws Exception {
        Class<?> itemClass = ITEM_TYPE_MAP.get(itemType);
        if (itemClass == null) {
            throw new IllegalArgumentException("Unsupported item type: " + itemType);
        }
        Object abstractItem = clientService.get(ITEM_URL_MAP.get(itemClass) + "/" + id, itemClass).getBody();
        switch (abstractItem) {
            case Movie movie -> uploadMovie(movie);
            case Book book -> uploadBook(book);
            default -> throw new IllegalStateException("Unexpected value: " + abstractItem);
        }
    }

    private void uploadMovie(Movie movie) {
        User user = userRepository.findById(movie.getItemSellerId()).get();
        UpdateMovieForm form = new UpdateMovieForm();
        form.setTitle(movie.getMovieTitle());
        form.setRating(movie.getMovieRating());
        form.setGenre(movie.getMovieGenre());
        form.setDirector(movie.getMovieDirector());
        form.setItemPrice(movie.getItemPrice());
        form.setItemStock(movie.getItemStock());
        form.setItemName(movie.getItemName());
        form.setItemDescription(movie.getItemDescription());
        form.setItemId(movie.getId());
        internalDiscoveryItemService.upsertMovie(user, form);
    }

    private void uploadBook(Book book) {
        User user = userRepository.findById(book.getItemSellerId()).get();
        UpdateBookForm form = new UpdateBookForm();
        form.setAuthor(book.getBookAuthor());
        form.setPublicationDate(book.getBookPublicationDate());
        form.setPublisher(book.getBookPublisher());
        form.setItemPrice(book.getItemPrice());
        form.setItemStock(book.getItemStock());
        form.setItemName(book.getItemName());
        form.setItemDescription(book.getItemDescription());
        form.setItemId(book.getId());
        internalDiscoveryItemService.upsertBook(user, form);
    }
}
