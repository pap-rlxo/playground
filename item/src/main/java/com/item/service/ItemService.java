package com.item.service;

import com.common.domain.Item.Book;
import com.common.domain.Item.Movie;
import com.common.domain.user.User;
import com.common.dto.UploadBookForm;
import com.common.dto.UploadMovieForm;

public interface ItemService {
    Book uploadBook(User user, UploadBookForm uploadBookForm);
    Movie uploadMovie(User user, UploadMovieForm uploadMovieForm);
}
