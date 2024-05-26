package com.common.service;

import lombok.Getter;

@Getter
public class KafkaUploadItemEventForm {

    private Long id;

    public KafkaUploadItemEventForm() {
    }

    public KafkaUploadItemEventForm(Long id) {
        this.id = id;
    }
}
