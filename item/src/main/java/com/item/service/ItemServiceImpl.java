package com.item.service;

import com.common.domain.Item.Book;
import com.common.domain.Item.Movie;
import com.common.domain.user.User;
import com.common.dto.UploadBookForm;
import com.common.dto.UploadMovieForm;
import com.common.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public Book uploadBook(User user, UploadBookForm uploadBookForm) {
        Book book = new Book(
                uploadBookForm.getAuthor(),
                uploadBookForm.getPublisher(),
                uploadBookForm.getPublicationDate(),
                uploadBookForm.getItemDescription(),
                uploadBookForm.getItemPrice(),
                uploadBookForm.getItemStock(),
                uploadBookForm.getItemName(),
                user.getId()
        );
        return itemRepository.save(book);
    }

    @Override
    public Movie uploadMovie(User user, UploadMovieForm uploadMovieForm) {
        Movie movie = new Movie(
                uploadMovieForm.getTitle(),
                uploadMovieForm.getDirector(),
                uploadMovieForm.getReleaseYear(),
                uploadMovieForm.getGenre(),
                uploadMovieForm.getRating(),
                uploadMovieForm.getItemDescription(),
                uploadMovieForm.getItemPrice(),
                uploadMovieForm.getItemStock(),
                uploadMovieForm.getItemName(),
                user.getId()
                );
        return itemRepository.save(movie);
    }
}
