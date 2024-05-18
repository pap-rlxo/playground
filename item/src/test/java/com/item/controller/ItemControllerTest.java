package com.item.controller;

import com.common.domain.Item.Genre;
import com.common.domain.user.Role;
import com.common.domain.user.User;
import com.common.dto.UploadBookForm;
import com.common.dto.UploadMovieForm;
import com.common.repository.user.UserRepository;
import config.ItemTestConfig;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@SpringBootTest
@Import({ItemTestConfig.class})
@Transactional()
class ItemControllerTest {

    @Autowired
    ItemController itemController;
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.save(new User("test", "test", Role.USER));
    }

    @Test
    void uploadBook() {
        User user = userRepository.findAll().getFirst();
        UploadBookForm uploadBookForm = new UploadBookForm();
        uploadBookForm.setItemDescription("This is a book");
        uploadBookForm.setItemName("Book");
        uploadBookForm.setItemPrice(100L);
        uploadBookForm.setItemStock(10);
        uploadBookForm.setAuthor("Author");
        uploadBookForm.setPublisher("tae");
        uploadBookForm.setPublicationDate(LocalDateTime.now());
        ResponseEntity<Void> responseEntity = itemController.uploadBook(user, uploadBookForm);
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void uploadMovie() {
        User user = userRepository.findAll().getFirst();
        UploadMovieForm uploadMovieForm = new UploadMovieForm();
        uploadMovieForm.setItemDescription("This is a movie");
        uploadMovieForm.setItemName("Movie");
        uploadMovieForm.setItemPrice(1000L);
        uploadMovieForm.setItemStock(5);
        uploadMovieForm.setDirector("director");
        uploadMovieForm.setGenre(Genre.ACTION);
        uploadMovieForm.setGenre(Genre.ACTION);
        uploadMovieForm.setRating(23);
        uploadMovieForm.setReleaseYear(LocalDateTime.now());
        uploadMovieForm.setTitle("title");
        ResponseEntity<Void> responseEntity = itemController.uploadMovie(user, uploadMovieForm);
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}