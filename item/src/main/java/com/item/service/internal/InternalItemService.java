package com.item.service.internal;

import com.common.domain.Item.Book;
import com.common.domain.Item.Movie;

public interface InternalItemService {
    Book getBook(Long id);
    Movie getMovie(Long id);
}
