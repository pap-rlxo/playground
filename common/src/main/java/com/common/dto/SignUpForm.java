package com.common.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignUpForm {
    @NotNull(message = "Username is required")
    private String userName;
    @NotNull(message = "Password is required")
    private String userPassword;
    private String userNickname;
}
