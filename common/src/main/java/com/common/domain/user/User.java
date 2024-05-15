package com.common.domain.user;

import com.common.domain.AbstractBaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users", indexes = @Index(columnList = "userName", unique = true))
@NoArgsConstructor
@Getter
public class User extends AbstractBaseEntity {

    public User(String userName, String userPassword, Role role) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userRole = role;
    }

    @Column(nullable = false, length = 8)
    private String userName;

    @Column(nullable = false)
    private String userPassword;

    @Enumerated(EnumType.STRING)
    private Role userRole;

    @Column(nullable = true, length = 8)
    private String userNickname;

    public void updatePasswordToEncryptPassword(String encodedPassword) {
        userPassword = encodedPassword;
    }

    public void update(User user) {
        this.userRole = user.getUserRole();
        this.userNickname = user.getUserNickname();
    }
}
