package com.gateway.routers;

import com.common.dto.SignUpForm;
import com.common.dto.UploadBookForm;
import com.common.dto.UploadMovieForm;
import com.common.dto.UserDto;
import com.gateway.PrincipalDetail;
import com.gateway.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController()
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemRouter {

    final private ClientService clientService;

    static final String ITEM_BASE_URL = "http://localhost:8082/items";

    @PostMapping("/upload/book")
    public ResponseEntity<UserDto> uploadBook(@AuthenticationPrincipal PrincipalDetail loginUser, @Valid @RequestBody UploadBookForm uploadBookForm) throws Exception {
        return clientService.post(loginUser, ITEM_BASE_URL + "/upload/book", uploadBookForm, null);
    }

    @PostMapping("/upload/movie")
    public ResponseEntity<UserDto> uploadMovie(@AuthenticationPrincipal PrincipalDetail loginUser, @Valid @RequestBody UploadMovieForm uploadMovieForm) throws Exception {
        return clientService.post(loginUser, ITEM_BASE_URL + "/upload/movie", uploadMovieForm, null);
    }
}
