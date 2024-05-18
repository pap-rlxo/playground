package com.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignUpForm {
    @JsonProperty("username")
    @NotNull(message = "Username is required")
    private String userName;

    @JsonProperty("userpassword")
    @NotNull(message = "Password is required")
    private String userPassword;

    private String userNickname;
}
