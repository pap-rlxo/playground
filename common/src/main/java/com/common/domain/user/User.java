package com.common.domain.user;

import com.common.domain.AbstractBaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

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

    private User(Builder builder) {
        super(builder);
        userName = builder.userName;
        userPassword = builder.userPassword;
        userRole = builder.role;
        userNickname = builder.userNickname;
    }

    public static class Builder extends AbstractBaseEntity.Builder<Builder> {

        private final String userName; // 필수 매개변수는 반드시 final 을 선언한다.
        private final String userPassword;    // 필수 매개변수는 반드시 final 을 선언한다.

        private Role role;    // 선택 매개변수
        private String userNickname;


        public Builder(String userName, String userPassword) {
            this.userName = userName;
            this.userPassword = userPassword;
        }

        public Builder userRole(Role role) {
            this.role = role;
            return this;
        }

        public Builder userNickname(String userNickname) {
            this.userNickname = userNickname;
            return this;
        }

        @Override
        public User build() {
            return new User(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    public static User of(Optional<Long> id, String userNickname, String userName, String userPassword, Role role) {
        Builder builder = new Builder(userName, userPassword);
        builder.userNickname(userNickname);
        builder.userRole(role);
        id.ifPresent(builder::setId);
        return builder.build();
    }
}
