package com.discovery.service.internal;

import com.common.domain.user.User;
import com.common.dto.UpdateBookForm;
import com.common.dto.UpdateMovieForm;

public interface InternalDiscoveryItemService {
    void upsertBook(User user, UpdateBookForm uploadBookForm);
    void upsertMovie(User user, UpdateMovieForm uploadMovieForm);
}
