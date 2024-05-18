package com.user.controller;

import com.common.dto.UserDto;
import com.common.interceptor.auth.LoginUser;
import com.common.domain.user.User;
import com.common.dto.SignUpForm;
import com.user.service.UserService;
import com.user.utils.DtoUtils;
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
    public UserDto me(@LoginUser User loginUser) {
        return DtoUtils.toUserDto(userService.get(loginUser.getId()));
    }

    @PostMapping("/signUp")
    @ResponseStatus(HttpStatus.OK)
    public UserDto signup(@RequestBody SignUpForm signUpForm) {
        return DtoUtils.toUserDto(userService.signUp(signUpForm));
    }
}
