package com.gateway.routers;

import com.common.domain.user.User;
import com.common.dto.SignUpForm;
import com.common.dto.UserDto;
import com.gateway.PrincipalDetail;
import com.gateway.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserRouter {

    final private ClientService clientService;

    static final String USER_BASE_URL = "http://localhost:8081/users";

    @GetMapping("/me")
    public UserDto me(@AuthenticationPrincipal PrincipalDetail user) throws Exception {
        return clientService.get(user, USER_BASE_URL + "/me", UserDto.class);
    }

    @PostMapping("/signUp")
    public UserDto signup(@Valid @RequestBody SignUpForm signUpForm) throws Exception {
        return clientService.post(null, USER_BASE_URL + "/signUp", signUpForm, UserDto.class);
    }
}
