package com.item.service.external;

import com.common.domain.Genre;
import com.common.domain.Item.Book;
import com.common.domain.Item.Item;
import com.common.domain.Item.Movie;
import com.common.domain.user.Role;
import com.common.domain.user.User;
import com.common.dto.*;
import com.common.repository.item.BookRepository;
import com.common.repository.item.ItemRepository;
import com.common.repository.item.MovieRepository;
import com.common.repository.user.UserRepository;
import config.ItemTestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({ItemTestConfig.class})
class ExternalItemServiceTest {

    @Autowired
    private ExternalItemService externalItemService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private MovieRepository movieRepository;

    @BeforeEach
    void setUp() {
        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setUserName("test");
        signUpForm.setUserPassword("testUserPassword");
        signUpForm.setUserNickname("testUserNickname");
        User user = User.of(signUpForm.getUserName(), passwordEncoder.encode(signUpForm.getUserPassword()), Role.USER);
        userRepository.save(user);
    }

    @Test
    void uploadBook() {
        User user = userRepository.findAll().getFirst();
        UploadBookForm uploadBookForm = new UploadBookForm();
        uploadBookForm.setPublisher("sdf");
        uploadBookForm.setItemDescription("description");
        uploadBookForm.setItemStock(123);
        uploadBookForm.setAuthor("author");
        uploadBookForm.setPublicationDate(LocalDateTime.now());
        uploadBookForm.setItemPrice(123123L);
        uploadBookForm.setItemName("name");
        Book uploadBook = externalItemService.uploadBook(user, uploadBookForm);
        assertThat(uploadBook).isNotNull();
    }

    @Test
    void uploadMovie() {
        User user = userRepository.findAll().getFirst();
        UploadMovieForm uploadMovieForm = new UploadMovieForm();
        uploadMovieForm.setItemDescription("description");
        uploadMovieForm.setItemStock(123);
        uploadMovieForm.setItemPrice(123123L);
        uploadMovieForm.setItemName("name");
        uploadMovieForm.setReleaseYear(LocalDateTime.now());
        uploadMovieForm.setGenre(Genre.ACTION);
        uploadMovieForm.setDirector("director");
        uploadMovieForm.setRating(12);
        uploadMovieForm.setTitle("title");
        Movie uploadMovie = externalItemService.uploadMovie(user, uploadMovieForm);
        assertThat(uploadMovie).isNotNull();
    }

    @Test
    void updateBook() {
        uploadBook();
        Book book = bookRepository.findAll().getFirst();
        User user = userRepository.findAll().getFirst();
        UpdateBookForm updateBookForm = new UpdateBookForm();
        updateBookForm.setPublisher("usdf");
        updateBookForm.setItemDescription("udescription");
        updateBookForm.setItemStock(124);
        updateBookForm.setAuthor("uauthor");
        updateBookForm.setPublicationDate(LocalDateTime.now());
        updateBookForm.setItemPrice(123124L);
        updateBookForm.setItemName("uname");
        updateBookForm.setItemId(book.getId());
        Book updateBook = externalItemService.updateBook(user, updateBookForm);
        List<Item> allItems = itemRepository.findAll();
        assertThat(allItems.size()).isEqualTo(1);
        assertThat(updateBook).isNotNull();
        assertThat(updateBook.getBookPublisher()).isEqualTo(updateBookForm.getPublisher());
    }

    @Test
    void updateMovie() {
        uploadMovie();
        Movie movie = movieRepository.findAll().getFirst();
        User user = userRepository.findAll().getFirst();
        UpdateMovieForm updateMovieForm = new UpdateMovieForm();
        updateMovieForm.setItemDescription("udescription");
        updateMovieForm.setItemStock(124);
        updateMovieForm.setItemPrice(123124L);
        updateMovieForm.setItemName("uname");
        updateMovieForm.setReleaseYear(LocalDateTime.now());
        updateMovieForm.setGenre(Genre.COMEDY);
        updateMovieForm.setDirector("udirector");
        updateMovieForm.setRating(13);
        updateMovieForm.setTitle("utitle");
        updateMovieForm.setItemId(movie.getId());
        Movie updateMovie = externalItemService.updateMovie(user, updateMovieForm);
        List<Item> allItems = itemRepository.findAll();
        assertThat(allItems.size()).isEqualTo(1);
        assertThat(updateMovie).isNotNull();
        assertThat(updateMovie.getMovieGenre()).isEqualTo(updateMovieForm.getGenre());
    }
}