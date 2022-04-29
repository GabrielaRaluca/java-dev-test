package com.ing.test.services;

import com.ing.test.model.Product;
import com.ing.test.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }

    public Iterable<Product> getProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> createProduct(Product newProduct) {
        Optional<Product> productOptional = productRepository.findById(newProduct.getId());

        if (productOptional.isPresent()) {
            return Optional.empty();
        } else {
            return Optional.of(productRepository.save(newProduct));
        }
    }

    public Optional<Product> deleteProduct(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            productRepository.deleteById(id);
            return Optional.of(productOptional.get());
        } else {
            return Optional.empty();
        }
    }

    public Optional<Product> updateProduct(Long id, Product product) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            product.setId(productOptional.get().getId());
            return Optional.of(productRepository.save(product));
        } else {
            return Optional.empty();
        }
    }
}
