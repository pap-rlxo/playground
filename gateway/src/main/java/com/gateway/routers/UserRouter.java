package com.gateway.routers;

import com.common.domain.user.User;
import com.common.dto.SignUpForm;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

@RestController()
@RequestMapping("/users")
public class UserRouter {
    static final String USER_BASE_URL = "http://localhost:8081/users";

    @GetMapping("/me")
    public User test() {
        RestClient client = RestClient.create();
        return client.get().uri(USER_BASE_URL + "/me").retrieve().toEntity(User.class).getBody();
    }

    @PostMapping("/signUp")
    public User signup(@Valid @RequestBody SignUpForm signUpForm) {
        RestClient client = RestClient.create();
        return client.post().uri(USER_BASE_URL + "/signUp").body(signUpForm).retrieve().toEntity(User.class).getBody();
    }
}
