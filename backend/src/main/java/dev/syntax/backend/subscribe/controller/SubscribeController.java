package dev.syntax.backend.subscribe.controller;


import dev.syntax.backend.product.service.ProductService;
import dev.syntax.backend.subscribe.service.SubscribeService;
import dev.syntax.backend.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RequestMapping("/subscribe")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SubscribeController {
    private SubscribeService subscribeService;
    private UserService userService;
    private ProductService productService;

    public SubscribeController(SubscribeService subscribeService, UserService userService, ProductService productService) {
        this.subscribeService = subscribeService;
        this.userService = userService;
        this.productService = productService;
    }

}
