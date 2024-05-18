package com.common.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AbstractBaseDto {

    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
