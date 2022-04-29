package com.ing.test.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ing.test.exception.BadRequestException;
import com.ing.test.model.Product;
import com.ing.test.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private ObjectMapper objectMapper;
    private final ProductRepository productRepository;

    public ProductController(final ProductRepository productRepository) {
        this.objectMapper = new ObjectMapper();
        this.productRepository = productRepository;
    }

    @GetMapping("/{id}")
    ResponseEntity getProduct(@PathVariable Long id){
        Optional<Product> productOptional = productRepository.findById(id);

        if(!productOptional.isPresent()) {
            throw new BadRequestException("No travel post exists with id " + id);

        } else {
            return ResponseEntity.ok(productOptional.get());
        }
    }

    @GetMapping()
    ResponseEntity getAllProducts() {
        Iterable<Product> products = productRepository.findAll();
        return ResponseEntity.ok(products);
    }


}
