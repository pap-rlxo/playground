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
        SignUpForm signUpForm = new SignUpForm("test", "testup", "testun");
        User user = User.of(Optional.empty(), signUpForm.userNickname(), signUpForm.userName(), passwordEncoder.encode(signUpForm.userPassword()), Role.USER);
        userRepository.save(user);
    }

    @Test
    void uploadBook() {
        User user = userRepository.findAll().getFirst();
        UploadBookForm uploadBookForm = new UploadBookForm("author", LocalDateTime.now(), "sdf", "descrp", "name", 123L, 3);
        Book uploadBook = externalItemService.uploadBook(user, uploadBookForm);
        assertThat(uploadBook).isNotNull();
        assertThat(uploadBook.getBookPublisher()).isEqualTo(uploadBookForm.publisher());
        assertThat(uploadBook.getItemName()).isEqualTo(uploadBookForm.itemName());
        assertThat(uploadBook.getId()).isNotNull();
    }

    @Test
    void uploadMovie() {
        User user = userRepository.findAll().getFirst();
        UploadMovieForm uploadMovieForm = new UploadMovieForm("director", Genre.ACTION, 12, LocalDateTime.now(), "title", "description", "name", 123L, 123);
        Movie uploadMovie = externalItemService.uploadMovie(user, uploadMovieForm);
        assertThat(uploadMovie).isNotNull();
        assertThat(uploadMovie.getItemDescription()).isEqualTo(uploadMovieForm.itemDescription());
        assertThat(uploadMovie.getMovieRating()).isEqualTo(uploadMovieForm.rating());
        assertThat(uploadMovie.getId()).isNotNull();
    }

    @Test
    void updateBook() {
        uploadBook();
        Book book = bookRepository.findAll().getFirst();
        User user = userRepository.findAll().getFirst();
        UpdateBookForm updateBookForm = new UpdateBookForm(book.getId(), "uauthor", LocalDateTime.now(), "usdf", "udescrp", "uname", 123L, 3);
        externalItemService.updateBook(user, updateBookForm);
        List<Book> allBooks = bookRepository.findAll();
        assertThat(allBooks.size()).isEqualTo(1);
        assertThat(allBooks.getFirst().getBookAuthor()).isEqualTo(updateBookForm.author());
        assertThat(allBooks.getFirst().getItemName()).isEqualTo(updateBookForm.itemName());
    }

    @Test
    void updateMovie() {
        uploadMovie();
        Movie movie = movieRepository.findAll().getFirst();
        User user = userRepository.findAll().getFirst();
        UpdateMovieForm updateMovieForm = new UpdateMovieForm(movie.getId(), "udir", Genre.COMEDY, 13, LocalDateTime.now(), "utitle", "udesc", "uname", 1232L, 123);
        externalItemService.updateMovie(user, updateMovieForm);
        List<Movie> allMovies = movieRepository.findAll();
        assertThat(allMovies.size()).isEqualTo(1);
        assertThat(allMovies.getFirst().getMovieGenre()).isEqualTo(updateMovieForm.genre());
        assertThat(allMovies.getFirst().getItemName()).isEqualTo(updateMovieForm.itemName());
    }
}