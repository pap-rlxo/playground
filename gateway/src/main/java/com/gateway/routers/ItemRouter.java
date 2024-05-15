package com.gateway.routers;

import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/items")
public class ItemRouter {
    static final String ITEM_BASE_URL = "http://localhost:8082/items";
}
