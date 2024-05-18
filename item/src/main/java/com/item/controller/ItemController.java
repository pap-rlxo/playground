package com.item.controller;

import com.common.domain.user.User;
import com.common.dto.UploadBookForm;
import com.common.dto.UploadMovieForm;
import com.common.interceptor.auth.LoginUser;
import com.item.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/upload/book")
    public ResponseEntity<Void> uploadBook(@LoginUser User loginUser, @Valid @RequestBody UploadBookForm uploadBookForm) {
        itemService.uploadBook(loginUser, uploadBookForm);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/upload/movie")
    public ResponseEntity<Void> uploadMovie(@LoginUser User loginUser, @Valid @RequestBody UploadMovieForm uploadMovieForm) {
        itemService.uploadMovie(loginUser, uploadMovieForm);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
