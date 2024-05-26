package com.item.controller.external;

import com.common.domain.user.User;
import com.common.dto.UpdateBookForm;
import com.common.dto.UpdateMovieForm;
import com.common.dto.UploadBookForm;
import com.common.dto.UploadMovieForm;
import com.common.interceptor.auth.LoginUser;
import com.item.service.external.ExternalItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/external/items")
public class ExternalItemController {

    private final ExternalItemService itemService;

    @PostMapping("/upload/book")
    public ResponseEntity<Void> uploadBook(@LoginUser User loginUser, @Valid @RequestBody UploadBookForm uploadBookForm) {
        itemService.uploadBook(loginUser, uploadBookForm);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/update/book")
    public ResponseEntity<Void> updateBook(@LoginUser User loginUser, @Valid @RequestBody UpdateBookForm updateBookForm) {
        itemService.updateBook(loginUser, updateBookForm);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/upload/movie")
    public ResponseEntity<Void> uploadMovie(@LoginUser User loginUser, @Valid @RequestBody UploadMovieForm uploadMovieForm) {
        itemService.uploadMovie(loginUser, uploadMovieForm);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/update/movie")
    public ResponseEntity<Void> updateMovie(@LoginUser User loginUser, @Valid @RequestBody UpdateMovieForm updateMovieForm) {
        itemService.updateMovie(loginUser, updateMovieForm);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
