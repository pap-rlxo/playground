package com.discovery.controller.external;

import com.common.dto.SearchDiscoveryItemDto;
import com.discovery.service.external.ExternalDiscoveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/external/discovery")
public class ExternalDiscoveryController {

    private final ExternalDiscoveryService externalDiscoveryService;

    @GetMapping("/for-test")
    public ResponseEntity<Void> uploadBook() {
        externalDiscoveryService.search(new SearchDiscoveryItemDto());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
