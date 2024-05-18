package com.user.utils;

import com.common.domain.user.User;
import com.common.dto.UserDto;

public class DtoUtils {

    static public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setUserNickname(user.getUserNickname());
        userDto.setUserRole(user.getUserRole());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setUpdatedAt(user.getUpdatedAt());
        return userDto;
    }
}
