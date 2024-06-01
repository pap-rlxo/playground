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
        Book book = new Book();
        book.update(uploadBookForm.getAuthor(), uploadBookForm.getPublisher(), uploadBookForm.getPublicationDate(), uploadBookForm.getItemDescription(), uploadBookForm.getItemPrice(), uploadBookForm.getItemStock(), uploadBookForm.getItemName(), user.getId());
        return itemRepository.save(book);
    }

    @Transactional(readOnly = false)
    @Override
    public Movie uploadMovie(User user, UploadMovieForm uploadMovieForm) {
        Movie movie = new Movie();
        movie.update(uploadMovieForm.getTitle(), uploadMovieForm.getDirector(), uploadMovieForm.getReleaseYear(), uploadMovieForm.getGenre(), uploadMovieForm.getRating(), uploadMovieForm.getItemDescription(), uploadMovieForm.getItemPrice(), uploadMovieForm.getItemStock(), uploadMovieForm.getItemName(), user.getId());
        return itemRepository.save(movie);
    }

    @Transactional(readOnly = false)
    @Override
    public Book updateBook(User user, UpdateBookForm updateBookForm) {
        Book book = bookRepository.findById(updateBookForm.getItemId()).orElseThrow(ElementNotFoundException::new);
        book.update(updateBookForm.getAuthor(), updateBookForm.getPublisher(), updateBookForm.getPublicationDate(), updateBookForm.getItemDescription(), updateBookForm.getItemPrice(), updateBookForm.getItemStock(), updateBookForm.getItemName(), user.getId());
        return book;
    }

    @Transactional(readOnly = false)
    @Override
    public Movie updateMovie(User user, UpdateMovieForm updateMovieForm) {
        Movie movie = movieRepository.findById(updateMovieForm.getItemId()).orElseThrow(ElementNotFoundException::new);
        movie.update(updateMovieForm.getTitle(), updateMovieForm.getDirector(), updateMovieForm.getReleaseYear(), updateMovieForm.getGenre(), updateMovieForm.getRating(), updateMovieForm.getItemDescription(), updateMovieForm.getItemPrice(), updateMovieForm.getItemStock(), updateMovieForm.getItemName(), user.getId());
        return movie;
    }
}
