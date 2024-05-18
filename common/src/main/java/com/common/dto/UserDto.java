package com.common.dto;

import com.common.domain.user.Role;
import lombok.Data;

@Data
public class UserDto extends AbstractBaseDto {

    private String userName;
    private Role userRole;
    private String userNickname;
}
