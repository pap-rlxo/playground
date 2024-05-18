package com.user.service;


import com.common.domain.user.User;
import com.common.dto.SignUpForm;

public interface UserService {
    User signUp(SignUpForm signUpForm);
    User get(Long id);
}
