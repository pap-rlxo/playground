package com.item.controller;

import com.common.dto.UploadBookForm;
import com.common.dto.UploadMovieForm ;
import com.item.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/upload/books")
    @ResponseStatus(HttpStatus.OK)
    public void uploadBook(@Valid @RequestBody UploadBookForm uploadBookForm) {
        itemService.uploadBook(uploadBookForm);
    }

//    @PostMapping("/upload/movies")
//    @ResponseStatus(HttpStatus.OK)
//    public void me(@Valid @RequestBody UploadMovieForm uploadMovieForm) {
//        return itemService.uploadItem(uploadBookForm);
//    }
}
