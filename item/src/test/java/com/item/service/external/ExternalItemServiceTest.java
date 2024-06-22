package com.item.service.external;

import com.common.domain.Genre;
import com.common.domain.Item.Book;
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
import java.util.Optional;

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
        signUpForm.setUserPassword("testup");
        signUpForm.setUserNickname("testun");
        User user = User.of(Optional.empty(), signUpForm.getUserNickname(), signUpForm.getUserName(), passwordEncoder.encode(signUpForm.getUserPassword()), Role.USER);
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
        assertThat(uploadBook.getBookPublisher()).isEqualTo(uploadBookForm.getPublisher());
        assertThat(uploadBook.getItemName()).isEqualTo(uploadBookForm.getItemName());
        assertThat(uploadBook.getId()).isNotNull();
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
        assertThat(uploadMovie.getItemDescription()).isEqualTo(uploadMovieForm.getItemDescription());
        assertThat(uploadMovie.getMovieRating()).isEqualTo(uploadMovieForm.getRating());
        assertThat(uploadMovie.getId()).isNotNull();
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
        externalItemService.updateBook(user, updateBookForm);
        List<Book> allBooks = bookRepository.findAll();
        assertThat(allBooks.size()).isEqualTo(1);
        assertThat(allBooks.getFirst().getBookAuthor()).isEqualTo(updateBookForm.getAuthor());
        assertThat(allBooks.getFirst().getItemName()).isEqualTo(updateBookForm.getItemName());
    }

    @Test
    void updateMovie() {
        uploadMovie();
        Movie movie = movieRepository.findAll().getFirst();
        User user = userRepository.findAll().getFirst();
        UpdateMovieForm updateMovieForm = new UpdateMovieForm();
        updateMovieForm.setItemDescription("udesc");
        updateMovieForm.setItemStock(124);
        updateMovieForm.setItemPrice(123124L);
        updateMovieForm.setItemName("uname");
        updateMovieForm.setReleaseYear(LocalDateTime.now());
        updateMovieForm.setGenre(Genre.COMEDY);
        updateMovieForm.setDirector("hdir");
        updateMovieForm.setRating(13);
        updateMovieForm.setTitle("utitle");
        updateMovieForm.setItemId(movie.getId());
        externalItemService.updateMovie(user, updateMovieForm);
        List<Movie> allMovies = movieRepository.findAll();
        assertThat(allMovies.size()).isEqualTo(1);
        assertThat(allMovies.getFirst().getMovieGenre()).isEqualTo(updateMovieForm.getGenre());
        assertThat(allMovies.getFirst().getItemName()).isEqualTo(updateMovieForm.getItemName());
    }
}