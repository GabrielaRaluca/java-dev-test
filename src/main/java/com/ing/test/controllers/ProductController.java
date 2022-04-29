package com.ing.test.controllers;

import com.ing.test.exception.BadRequestException;
import com.ing.test.model.Product;
import com.ing.test.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;
    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    ResponseEntity getProduct(@PathVariable Long id){
        LOGGER.info("Trying to get product with id " + id);
        Optional<Product> productOptional = productService.getProduct(id);

        if(!productOptional.isPresent()) {
            LOGGER.info("Product " + id + " not found!");
            throw new BadRequestException("No travel post exists with id " + id);

        } else {
            return ResponseEntity.ok(productOptional.get());
        }
    }

    @GetMapping()
    ResponseEntity getAllProducts() {
        LOGGER.info("Trying to get the list of products");
        return ResponseEntity.ok(productService.getProducts());
    }

    @PostMapping()
    ResponseEntity createProduct(@RequestBody Product product) {
        LOGGER.info("Trying to create a new product");
        Optional<Product> productOptional = productService.createProduct(product);
        if (!productOptional.isPresent()) {
            LOGGER.info("Product with the same name already exists!");
            throw new BadRequestException("Product with the same name already exists!");
        } else {
            return ResponseEntity.ok(productOptional.get());
        }
    }

    @PutMapping("/{id}")
    ResponseEntity updateProduct(@PathVariable Long id, @RequestBody Product product) {
        LOGGER.info("Trying to update product with id " + id);
        Optional<Product> productOptional = productService.updateProduct(id, product);
        if (!productOptional.isPresent()) {
            LOGGER.info("Product with id " + id + " does not exist!");
            throw new BadRequestException("Product with id " + id + " does not exist!");
        } else {
            return ResponseEntity.ok(productOptional.get());
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteProduct(@PathVariable Long id) {
        LOGGER.info("Trying to delete product with id " + id);
        Optional<Product> productOptional = productService.deleteProduct(id);
        if (!productOptional.isPresent()) {
            LOGGER.info("Product with id " + id + " does not exist!");
            throw new BadRequestException("Product with id " + id + " does not exist!");
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}
