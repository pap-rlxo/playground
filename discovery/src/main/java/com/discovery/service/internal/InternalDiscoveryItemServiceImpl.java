package com.discovery.service.internal;

import com.common.domain.discovery.DiscoveryBook;
import com.common.domain.discovery.DiscoveryMovie;
import com.common.domain.user.User;
import com.common.dto.UpdateBookForm;
import com.common.dto.UpdateMovieForm;
import com.common.dto.UploadBookForm;
import com.common.dto.UploadMovieForm;
import com.common.exception.ElementNotFoundException;
import com.common.repository.discovery.DiscoveryBookRepository;
import com.common.repository.discovery.DiscoveryItemRepository;
import com.common.repository.discovery.DiscoveryMovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class InternalDiscoveryItemServiceImpl implements InternalDiscoveryItemService {

    private final DiscoveryItemRepository discoveryItemRepository;
    private final DiscoveryBookRepository discoveryBookRepository;
    private final DiscoveryMovieRepository discoveryMovieRepository;

    @Transactional(readOnly = false)
    @Override
    public void upsertBook(User user, UpdateBookForm updateBookForm) {
        Optional<DiscoveryBook> bookOptional = discoveryBookRepository.findById(updateBookForm.getItemId());
        if (bookOptional.isPresent()) {
            updateBook(user, updateBookForm);
        } else {
            uploadBook(user, updateBookForm);
        }
    }

    @Transactional(readOnly = false)
    @Override
    public void upsertMovie(User user, UpdateMovieForm updateMovieForm) {
        Optional<DiscoveryMovie> movieOptional = discoveryMovieRepository.findById(updateMovieForm.getItemId());
        if (movieOptional.isPresent()) {
            updateMovie(user, updateMovieForm);
        } else {
            uploadMovie(user, updateMovieForm);
        }
    }

    private void uploadBook(User user, UploadBookForm uploadBookForm) {
        DiscoveryBook discoveryBook = DiscoveryBook.of(Optional.empty(), uploadBookForm.getAuthor(), uploadBookForm.getPublisher(), uploadBookForm.getPublicationDate(), uploadBookForm.getItemDescription(), uploadBookForm.getItemPrice(), uploadBookForm.getItemStock(), uploadBookForm.getItemName(), user.getId());
        discoveryBookRepository.save(discoveryBook);
    }

    private void uploadMovie(User user, UploadMovieForm uploadMovieForm) {
        DiscoveryMovie discoveryMovie = DiscoveryMovie.of(Optional.empty(), uploadMovieForm.getTitle(), uploadMovieForm.getDirector(), uploadMovieForm.getReleaseYear(), uploadMovieForm.getGenre(), uploadMovieForm.getRating(), uploadMovieForm.getItemDescription(), uploadMovieForm.getItemPrice(), uploadMovieForm.getItemStock(), uploadMovieForm.getItemName(), user.getId());
        discoveryItemRepository.save(discoveryMovie);
    }

    private void updateBook(User user, UpdateBookForm updateBookForm) {
        DiscoveryBook discoveryBook = discoveryBookRepository.findById(updateBookForm.getItemId()).orElseThrow(ElementNotFoundException::new);
        DiscoveryBook.of(Optional.of(discoveryBook.getId()), updateBookForm.getAuthor(), updateBookForm.getPublisher(), updateBookForm.getPublicationDate(), updateBookForm.getItemDescription(), updateBookForm.getItemPrice(), updateBookForm.getItemStock(), updateBookForm.getItemName(), user.getId());
    }

    private void updateMovie(User user, UpdateMovieForm updateMovieForm) {
        DiscoveryMovie discoveryMovie = discoveryMovieRepository.findById(updateMovieForm.getItemId()).orElseThrow(ElementNotFoundException::new);
        DiscoveryMovie.of(Optional.of(discoveryMovie.getId()), updateMovieForm.getTitle(), updateMovieForm.getDirector(), updateMovieForm.getReleaseYear(), updateMovieForm.getGenre(), updateMovieForm.getRating(), updateMovieForm.getItemDescription(), updateMovieForm.getItemPrice(), updateMovieForm.getItemStock(), updateMovieForm.getItemName(), user.getId());
    }
}
