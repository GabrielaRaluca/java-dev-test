package com.ing.test.repository;

import com.ing.test.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {
    public Optional<Product> findById(Long id);
    public Optional<List<Product>> getAll();
}
