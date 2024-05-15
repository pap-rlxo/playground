package com.user.controller;

import com.common.domain.user.User;
import com.common.dto.SignUpForm;
import com.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public User me() {
        return userService.get(1L);
    }

    @PostMapping("/signUp")
    @ResponseStatus(HttpStatus.OK)
    public User signup(@RequestBody SignUpForm signUpForm) {
        return userService.signUp(signUpForm);
    }
}
