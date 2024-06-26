package com.item.service.external;

import com.common.domain.Item.Book;
import com.common.domain.Item.Movie;
import com.common.domain.user.User;
import com.common.dto.UpdateBookForm;
import com.common.dto.UpdateMovieForm;
import com.common.dto.UploadBookForm;
import com.common.dto.UploadMovieForm;

public interface ExternalItemService {
    Book uploadBook(User user, UploadBookForm uploadBookForm);
    Movie uploadMovie(User user, UploadMovieForm uploadMovieForm);
    Book updateBook(User user, UpdateBookForm uploadBookForm);
    Movie updateMovie(User user, UpdateMovieForm uploadMovieForm);
}
