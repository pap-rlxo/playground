package com.item.service.external;

import com.common.domain.Item.Book;
import com.common.domain.Item.Movie;
import com.common.domain.user.User;
import com.common.dto.UpdateBookForm;
import com.common.dto.UpdateMovieForm;
import com.common.dto.UploadBookForm;
import com.common.dto.UploadMovieForm;
import com.common.exception.ElementNotFoundException;
import com.common.repository.item.BookRepository;
import com.common.repository.item.ItemRepository;
import com.common.repository.item.MovieRepository;
import com.item.aop.SendUploadItemEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@SendUploadItemEvent
@Transactional
public class ExternalItemServiceImpl implements ExternalItemService {

    private final ItemRepository itemRepository;
    private final BookRepository bookRepository;
    private final MovieRepository movieRepository;

    @Transactional(readOnly = false)
    @Override
    public Book uploadBook(User user, UploadBookForm uploadBookForm) {
        Book book = Book.of(Optional.empty(), uploadBookForm.author(), uploadBookForm.publisher(), uploadBookForm.publicationDate(), uploadBookForm.itemDescription(), uploadBookForm.itemPrice(), uploadBookForm.itemStock(), uploadBookForm.itemName(), user.getId());
        return itemRepository.save(book);
    }

    @Transactional(readOnly = false)
    @Override
    public Movie uploadMovie(User user, UploadMovieForm uploadMovieForm) {
        Movie movie = Movie.of(Optional.empty(), uploadMovieForm.title(), uploadMovieForm.director(), uploadMovieForm.releaseYear(), uploadMovieForm.genre(), uploadMovieForm.rating(), uploadMovieForm.itemDescription(), uploadMovieForm.itemPrice(), uploadMovieForm.itemStock(), uploadMovieForm.itemName(), user.getId());
        return itemRepository.save(movie);
    }

    @Transactional(readOnly = false)
    @Override
    public Book updateBook(User user, UpdateBookForm updateBookForm) {
        Book book = bookRepository.findById(updateBookForm.itemId()).orElseThrow(ElementNotFoundException::new);
        Book updatedBook = Book.of(Optional.of(book.getId()), updateBookForm.author(), updateBookForm.publisher(), updateBookForm.publicationDate(), updateBookForm.itemDescription(), updateBookForm.itemPrice(), updateBookForm.itemStock(), updateBookForm.itemName(), user.getId());
        return itemRepository.save(updatedBook);
    }

    @Transactional(readOnly = false)
    @Override
    public Movie updateMovie(User user, UpdateMovieForm updateMovieForm) {
        Movie movie = movieRepository.findById(updateMovieForm.itemId()).orElseThrow(ElementNotFoundException::new);
        Movie updatedMovie = Movie.of(Optional.of(movie.getId()), updateMovieForm.title(), updateMovieForm.director(), updateMovieForm.releaseYear(), updateMovieForm.genre(), updateMovieForm.rating(), updateMovieForm.itemDescription(), updateMovieForm.itemPrice(), updateMovieForm.itemStock(), updateMovieForm.itemName(), user.getId());
        return movieRepository.save(updatedMovie);
    }
}
