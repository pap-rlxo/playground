package com.item.controller.internal;

import com.common.aop.InternalAuthCheck;

import com.common.domain.Item.Book;
import com.common.domain.Item.Movie;
import com.item.service.internal.InternalItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@InternalAuthCheck
@RequestMapping("/internal/items")
public class InternalItemController {

    private final InternalItemService itemService;

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) throws Exception {
        Book book = itemService.getBook(id);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable Long id) throws Exception {
        Movie movie = itemService.getMovie(id);
        return ResponseEntity.status(HttpStatus.OK).body(movie);
    }
}
