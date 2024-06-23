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
        Optional<DiscoveryBook> bookOptional = discoveryBookRepository.findById(updateBookForm.itemId());
        if (bookOptional.isPresent()) {
            updateBook(user, updateBookForm);
        } else {
            uploadBook(user, getUploadBookForm(updateBookForm));
        }
    }

    @Transactional(readOnly = false)
    @Override
    public void upsertMovie(User user, UpdateMovieForm updateMovieForm) {
        Optional<DiscoveryMovie> movieOptional = discoveryMovieRepository.findById(updateMovieForm.itemId());
        if (movieOptional.isPresent()) {
            updateMovie(user, updateMovieForm);
        } else {
            uploadMovie(user, getUploadMovieForm(updateMovieForm));
        }
    }

    private static UploadMovieForm getUploadMovieForm(UpdateMovieForm updateMovieForm) {
        UploadMovieForm uploadMovieForm = new UploadMovieForm(
                updateMovieForm.director(),
                updateMovieForm.genre(),
                updateMovieForm.rating(),
                updateMovieForm.releaseYear(),
                updateMovieForm.title(),
                updateMovieForm.itemDescription(),
                updateMovieForm.itemName(),
                updateMovieForm.itemPrice(),
                updateMovieForm.itemStock()
        );
        return uploadMovieForm;
    }

    private void uploadBook(User user, UploadBookForm uploadBookForm) {
        DiscoveryBook discoveryBook = DiscoveryBook.of(Optional.empty(), uploadBookForm.author(), uploadBookForm.publisher(), uploadBookForm.publicationDate(), uploadBookForm.itemDescription(), uploadBookForm.itemPrice(), uploadBookForm.itemStock(), uploadBookForm.itemName(), user.getId());
        discoveryBookRepository.save(discoveryBook);
    }

    private void uploadMovie(User user, UploadMovieForm uploadMovieForm) {
        DiscoveryMovie discoveryMovie = DiscoveryMovie.of(Optional.empty(), uploadMovieForm.title(), uploadMovieForm.director(), uploadMovieForm.releaseYear(), uploadMovieForm.genre(), uploadMovieForm.rating(), uploadMovieForm.itemDescription(), uploadMovieForm.itemPrice(), uploadMovieForm.itemStock(), uploadMovieForm.itemName(), user.getId());
        discoveryItemRepository.save(discoveryMovie);
    }

    private void updateBook(User user, UpdateBookForm updateBookForm) {
        DiscoveryBook discoveryBook = discoveryBookRepository.findById(updateBookForm.itemId()).orElseThrow(ElementNotFoundException::new);
        DiscoveryBook.of(Optional.of(discoveryBook.getId()), updateBookForm.author(), updateBookForm.publisher(), updateBookForm.publicationDate(), updateBookForm.itemDescription(), updateBookForm.itemPrice(), updateBookForm.itemStock(), updateBookForm.itemName(), user.getId());
    }

    private void updateMovie(User user, UpdateMovieForm updateMovieForm) {
        DiscoveryMovie discoveryMovie = discoveryMovieRepository.findById(updateMovieForm.itemId()).orElseThrow(ElementNotFoundException::new);
        DiscoveryMovie.of(Optional.of(discoveryMovie.getId()), updateMovieForm.title(), updateMovieForm.director(), updateMovieForm.releaseYear(), updateMovieForm.genre(), updateMovieForm.rating(), updateMovieForm.itemDescription(), updateMovieForm.itemPrice(), updateMovieForm.itemStock(), updateMovieForm.itemName(), user.getId());
    }

    private static UploadBookForm getUploadBookForm(UpdateBookForm updateBookForm) {
        UploadBookForm uploadBookForm = new UploadBookForm(
                updateBookForm.author(),
                updateBookForm.publicationDate(),
                updateBookForm.publisher(),
                updateBookForm.itemDescription(),
                updateBookForm.itemName(),
                updateBookForm.itemPrice(),
                updateBookForm.itemStock()
        );
        return uploadBookForm;
    }
}
