package com.user.controller;

import com.common.dto.UserDto;
import com.common.interceptor.auth.LoginUser;
import com.common.domain.user.User;
import com.common.dto.SignUpForm;
import com.user.service.UserService;
import com.user.utils.DtoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserDto> me(@LoginUser User loginUser) {
        UserDto userDto = DtoUtils.toUserDto(userService.get(loginUser.getId()));
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @PostMapping("/signUp")
    public ResponseEntity<UserDto> signup(@RequestBody SignUpForm signUpForm) {
        UserDto userDto = DtoUtils.toUserDto(userService.signUp(signUpForm));
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }
}
