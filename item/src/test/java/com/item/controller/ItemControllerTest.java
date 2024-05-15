package com.item.controller;

import com.common.dto.UploadBookForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
@EntityScan(basePackages = "com.common.domain")
class ItemControllerTest {

    @Autowired ItemController itemController;

    @Test
    void uploadBook() {
        UploadBookForm uploadBookForm = new UploadBookForm();
        uploadBookForm.setItemDescription("This is a book");
        uploadBookForm.setSellerId(1L);
        uploadBookForm.setItemName("Book");
        uploadBookForm.setItemPrice(100L);
        uploadBookForm.setItemStock(10);
        uploadBookForm.setAuthor("Author");
        uploadBookForm.setPublisher("tae");
        uploadBookForm.setPublicationDate(LocalDateTime.now());
        itemController.uploadBook(uploadBookForm);
    }
}