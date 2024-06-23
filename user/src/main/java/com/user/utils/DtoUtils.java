package com.user.utils;

import com.common.domain.user.User;
import com.common.dto.UserDto;

public class DtoUtils {

    static public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto(
                user.getId(),
                user.getUserName(),
                user.getUserRole(),
                user.getUserNickname(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
        return userDto;
    }
}
