package com.BEJ.Bej.controller;


import com.BEJ.Bej.entity.Product;
import com.BEJ.Bej.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/")
public class ProductController {

    ProductService productService;

    @GetMapping
    List<Product> getProducts() {
        return productService.getProducts();
    }

}
