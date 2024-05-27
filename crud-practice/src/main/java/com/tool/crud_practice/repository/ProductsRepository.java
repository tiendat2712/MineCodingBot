package com.tool.crud_practice.repository;

import com.tool.crud_practice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Product, Integer> {
    boolean existsByName(String name);
}
