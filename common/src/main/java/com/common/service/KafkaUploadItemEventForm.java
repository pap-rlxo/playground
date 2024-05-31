package com.common.service;

import com.common.domain.ItemType;
import lombok.Getter;

@Getter
public class KafkaUploadItemEventForm {

    private Long id;
    private ItemType type;

    public KafkaUploadItemEventForm() {
    }

    public KafkaUploadItemEventForm(Long id, ItemType type) {
        this.id = id;
        this.type = type;
    }
}
