package com.gateway.routers;

import com.common.dto.*;
import com.gateway.PrincipalDetail;

import com.gateway.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemRouter {

    final private ClientService clientService;

    static final String ITEM_BASE_URL = "http://localhost:8082/external/items";

    @PostMapping("/upload/book")
    public ResponseEntity<Void> uploadBook(@AuthenticationPrincipal PrincipalDetail loginUser, @Valid @RequestBody UploadBookForm uploadBookForm) throws Exception {
        return clientService.post(loginUser, ITEM_BASE_URL + "/upload/book", uploadBookForm, null);
    }

    @PostMapping("/update/book")
    public ResponseEntity<Void> uploadBook(@AuthenticationPrincipal PrincipalDetail loginUser, @Valid @RequestBody UpdateBookForm updateBookForm) throws Exception {
        return clientService.post(loginUser, ITEM_BASE_URL + "/update/book", updateBookForm, null);
    }

    @PostMapping("/upload/movie")
    public ResponseEntity<Void> uploadMovie(@AuthenticationPrincipal PrincipalDetail loginUser, @Valid @RequestBody UploadMovieForm uploadMovieForm) throws Exception {
        return clientService.post(loginUser, ITEM_BASE_URL + "/upload/movie", uploadMovieForm, null);
    }

    @PostMapping("/update/movie")
    public ResponseEntity<Void> uploadMovie(@AuthenticationPrincipal PrincipalDetail loginUser, @Valid @RequestBody UpdateMovieForm updateMovieForm) throws Exception {
        return clientService.post(loginUser, ITEM_BASE_URL + "/update/movie", updateMovieForm, null);
    }
}
