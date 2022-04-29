package com.ing.test.controllers;

import com.ing.test.exception.BadRequestException;
import com.ing.test.model.Product;
import com.ing.test.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    ResponseEntity getProduct(@PathVariable Long id){
        Optional<Product> productOptional = productService.getProduct(id);

        if(!productOptional.isPresent()) {
            throw new BadRequestException("No travel post exists with id " + id);

        } else {
            return ResponseEntity.ok(productOptional.get());
        }
    }

    @GetMapping()
    ResponseEntity getAllProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @PostMapping()
    ResponseEntity createProduct(@RequestBody Product product) {
        Optional<Product> productOptional = productService.createProduct(product);
        if (!productOptional.isPresent()) {
            throw new BadRequestException("Product with the same name already exists!");
        } else {
            return ResponseEntity.ok(productOptional.get());
        }
    }

    @PutMapping("/{id}")
    ResponseEntity updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> productOptional = productService.updateProduct(id, product);
        if (!productOptional.isPresent()) {
            throw new BadRequestException("Product with id " + id + " does not exist!");
        } else {
            return ResponseEntity.ok(productOptional.get());
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteProduct(@PathVariable Long id) {

        Optional<Product> productOptional = productService.deleteProduct(id);
        if (!productOptional.isPresent()) {
            throw new BadRequestException("Product with id " + id + " does not exist!");
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}
