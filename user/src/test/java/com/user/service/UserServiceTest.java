package com.user.service;

import com.common.domain.user.User;
import com.common.dto.SignUpForm;
import com.user.config.UserTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({UserTestConfig.class})
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void signUp() {
        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setUserName("test");
        signUpForm.setUserPassword("testUserPassword");
        signUpForm.setUserNickname("testUserNickname");
        User signupUser = userService.signUp(signUpForm);
        assertThat(signupUser.getUserPassword()).isNotEqualTo(signUpForm.getUserPassword());
    }

    @Test
    void get() {
        signUp();
        assertThat(1L).isNotNull();
    }
}