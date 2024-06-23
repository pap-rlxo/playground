package com.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record SignUpForm(
        @JsonProperty("username")
        @NotNull(message = "Username is required")
        String userName,

        @JsonProperty("userpassword")
        @NotNull(message = "Password is required")
        String userPassword,

        String userNickname
) {
}
