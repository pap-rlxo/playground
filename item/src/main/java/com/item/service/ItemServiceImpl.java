package com.item.service;

import com.common.domain.Item.Book;
import com.common.dto.UploadBookForm;
import com.common.dto.UploadMovieForm;
import com.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Override
    public void uploadBook(UploadBookForm uploadBookForm) {
        Book book = new Book(
                uploadBookForm.getAuthor(),
                uploadBookForm.getPublisher(),
                uploadBookForm.getPublicationDate(),
                uploadBookForm.getItemDescription(),
                uploadBookForm.getItemPrice(),
                uploadBookForm.getItemStock(),
                uploadBookForm.getItemName(),
                uploadBookForm.getSellerId()
        );
        itemRepository.save(book);
    }

    @Override
    public void uploadMovie(UploadMovieForm uploadMovieForm) {

    }
}
