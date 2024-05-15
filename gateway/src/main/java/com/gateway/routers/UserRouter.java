package com.gateway.routers;

import com.common.domain.user.User;
import com.common.dto.SignUpForm;
import com.common.utils.Crypto;
import com.gateway.PrincipalDetail;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.function.Consumer;

@RestController()
@RequestMapping("/users")
public class UserRouter {

    @Value("${internal.auth.key}")
    private String authKey;

    static final String USER_BASE_URL = "http://localhost:8081/users";

    @GetMapping("/me")
    public User test(@AuthenticationPrincipal PrincipalDetail user) throws Exception {
        String seceretKey = Crypto.aesEcbEncrypt(authKey, user.getUser().getId().toString());
        Consumer<HttpHeaders> headersConsumer = headers -> {
            headers.set("Content-Type", "application/json");
            headers.set("Authorization", seceretKey);
        };
        RestClient client = RestClient.create();
        return client.get().uri(USER_BASE_URL + "/me")
                .headers(
                headersConsumer
        )
                .retrieve().toEntity(User.class).getBody();
    }

    @PostMapping("/signUp")
    public User signup(@Valid @RequestBody SignUpForm signUpForm) {
        RestClient client = RestClient.create();
        return client.post().uri(USER_BASE_URL + "/signUp").body(signUpForm).retrieve().toEntity(User.class).getBody();
    }
}
