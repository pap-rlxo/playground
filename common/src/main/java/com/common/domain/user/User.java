package com.common.domain.user;

import com.common.domain.AbstractBaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users", indexes = @Index(columnList = "userName", unique = true))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends AbstractBaseEntity {

    @Column(nullable = false, length = 8)
    private String userName;

    @Column(nullable = false)
    private String userPassword;

    @Enumerated(EnumType.STRING)
    private Role userRole;

    @Column(nullable = true, length = 8)
    private String userNickname;

    public static User of(String userName, String userPassword, Role role) {
        User user = new User();
        user.userName = userName;
        user.userPassword = userPassword;
        user.userRole = role;
        return user;
    }
}
