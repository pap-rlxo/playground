package com.item.service.internal;

import com.common.domain.Item.Book;
import com.common.domain.Item.Movie;
import com.common.exception.ElementNotFoundException;
import com.common.repository.item.BookRepository;
import com.common.repository.item.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class InternalItemServiceImpl implements InternalItemService {

    private final BookRepository bookRepository;
    private final MovieRepository movieRepository;

    @Transactional(readOnly = true)
    @Override
    public Book getBook(Long id) {
        return bookRepository.findById(id).orElseThrow(ElementNotFoundException::new);
    }

    @Transactional(readOnly = true)
    @Override
    public Movie getMovie(Long id) {
        return movieRepository.findById(id).orElseThrow(ElementNotFoundException::new);
    }
}
