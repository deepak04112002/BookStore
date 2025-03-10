package com.BookStore.catalog_service.web.controllers;

import com.BookStore.catalog_service.domain.PagedResult;
import com.BookStore.catalog_service.domain.Product;
import com.BookStore.catalog_service.domain.ProductNotFoundException;
import com.BookStore.catalog_service.domain.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/products")
class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    PagedResult<Product> getProducts(@RequestParam(name = "page",defaultValue = "1")int pageNo) {
       return productService.getProducts(pageNo);
    }

    @GetMapping("/{code}")
    ResponseEntity<Product> getProductByCode(@PathVariable String code) {
       return productService.getProductByCode(code)
               .map(ResponseEntity::ok)
               .orElseThrow(()->ProductNotFoundException.forCode(code));
    }
}
