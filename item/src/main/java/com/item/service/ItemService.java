package com.item.service;

import com.common.dto.UploadBookForm;
import com.common.dto.UploadMovieForm;

public interface ItemService {
    void uploadBook(UploadBookForm uploadBookForm);
    void uploadMovie(UploadMovieForm uploadMovieForm);
}
