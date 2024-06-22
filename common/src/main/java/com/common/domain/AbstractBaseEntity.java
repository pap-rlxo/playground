package com.common.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.Objects;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractBaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    protected AbstractBaseEntity(Builder<?> builder) {
        this.id = builder.id;
    }

    protected abstract static class Builder<T extends Builder<T>> {

        private Long id;

        // 제너릭 추상 메서드
        public T setId(Long id) {
            this.id = id;
            return self();
        }

        // 추상 메서드
        public abstract AbstractBaseEntity build();

        // 추상 메서드
        // 하위 클래스는 이 메서드를 재정의(overriding)하여
        // "this"를 반환하도록 해야 한다.
        protected abstract T self();
    }
}
