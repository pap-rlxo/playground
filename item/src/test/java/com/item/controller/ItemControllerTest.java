package com.item.controller;

import com.common.domain.Item.Book;
import com.common.domain.Item.Genre;
import com.common.domain.Item.Movie;
import com.common.domain.user.Role;
import com.common.domain.user.User;
import com.common.dto.UpdateBookForm;
import com.common.dto.UpdateMovieForm;
import com.common.dto.UploadBookForm;
import com.common.dto.UploadMovieForm;
import com.common.repository.item.BookRepository;
import com.common.repository.item.ItemRepository;
import com.common.repository.item.MovieRepository;
import com.common.repository.user.UserRepository;
import com.item.controller.external.ExternalItemController;
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
@Transactional
class ItemControllerTest {

    @Autowired
    ExternalItemController itemController;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private MovieRepository movieRepository;

    @BeforeEach
    void setUp() {
        userRepository.save(new User("test", "test", Role.USER));
    }

    @Test
    void uploadBook() {
        User user = userRepository.findAll().getFirst();
        UploadBookForm uploadBookForm = generateUploadBookForm();
        ResponseEntity<Void> responseEntity = itemController.uploadBook(user, uploadBookForm);
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void updateBook() {
        User user = userRepository.findAll().getFirst();
        UploadBookForm uploadBookForm = generateUploadBookForm();
        itemController.uploadBook(user, uploadBookForm);
        Book book = bookRepository.findAll().getFirst();
        UpdateBookForm updateBookForm = generateUpdateBookForm(book);
        ResponseEntity<Void> responseEntity = itemController.updateBook(user, updateBookForm);
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void uploadMovie() {
        User user = userRepository.findAll().getFirst();
        UploadMovieForm uploadMovieForm = generateUploadMovieForm();
        ResponseEntity<Void> responseEntity = itemController.uploadMovie(user, uploadMovieForm);
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void updateMovie() {
        User user = userRepository.findAll().getFirst();
        UploadMovieForm uploadMovieForm = generateUploadMovieForm();
        itemController.uploadMovie(user, uploadMovieForm);
        Movie movie = movieRepository.findAll().getFirst();
        UpdateMovieForm updateMovieForm = generateUpdateMovieForm(movie);
        ResponseEntity<Void> responseEntity = itemController.updateMovie(user, updateMovieForm);
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    private UploadMovieForm generateUploadMovieForm() {
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
        return uploadMovieForm;
    }

    private UploadBookForm generateUploadBookForm() {
        UploadBookForm uploadBookForm = new UploadBookForm();
        uploadBookForm.setItemDescription("This is a book");
        uploadBookForm.setItemName("Book");
        uploadBookForm.setItemPrice(100L);
        uploadBookForm.setItemStock(10);
        uploadBookForm.setAuthor("Author");
        uploadBookForm.setPublisher("tae");
        uploadBookForm.setPublicationDate(LocalDateTime.now());
        return uploadBookForm;
    }

    private UpdateBookForm generateUpdateBookForm(Book book) {
        UpdateBookForm uploadBookForm = new UpdateBookForm();
        uploadBookForm.setItemDescription("This is a book");
        uploadBookForm.setItemName("Book");
        uploadBookForm.setItemPrice(10L);
        uploadBookForm.setItemStock(5);
        uploadBookForm.setAuthor("Author");
        uploadBookForm.setPublisher("tae");
        uploadBookForm.setPublicationDate(LocalDateTime.now());
        uploadBookForm.setItemId(book.getId());
        return uploadBookForm;
    }

    private UpdateMovieForm generateUpdateMovieForm(Movie movie) {
        UpdateMovieForm updateMovieForm = new UpdateMovieForm();
        updateMovieForm.setItemDescription("This is a movie");
        updateMovieForm.setItemName("Movie");
        updateMovieForm.setItemPrice(5L);
        updateMovieForm.setItemStock(10);
        updateMovieForm.setDirector("director");
        updateMovieForm.setGenre(Genre.ACTION);
        updateMovieForm.setGenre(Genre.ACTION);
        updateMovieForm.setRating(23);
        updateMovieForm.setReleaseYear(LocalDateTime.now());
        updateMovieForm.setTitle("title");
        updateMovieForm.setItemId(movie.getId());
        return updateMovieForm;
    }
}