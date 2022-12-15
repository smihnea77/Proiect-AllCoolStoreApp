package com.allcoolstore.repository;


import com.allcoolstore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product,Long> {
   // Product findByType(String type);
    Optional<Product> findByName(String name);
}
