package com.common.domain.Item;

import lombok.RequiredArgsConstructor;

public enum ItemType {

    BOOK("Book"), MOVIE("Movie");

    private final String value;

    ItemType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
