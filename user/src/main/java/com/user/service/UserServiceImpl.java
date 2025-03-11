package com.user.service;

import com.common.domain.user.Role;
import com.common.domain.user.User;

import com.common.dto.SignUpForm;
import com.common.exception.ElementNotFoundException;
import com.common.exception.RegistrationException;
import com.common.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User signUp(SignUpForm signUpForm) {
        Optional<User> userOptional = userRepository.findByUserName(signUpForm.userName());
        if (userOptional.isPresent()) {
            throw new RegistrationException();
        }
        User user = User.of(Optional.empty(), signUpForm.userNickname(), signUpForm.userName(), passwordEncoder.encode(signUpForm.userPassword()), Role.USER);
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User get(Long id) {
        return userRepository.findById(id).orElseThrow(ElementNotFoundException::new);
    }
}
