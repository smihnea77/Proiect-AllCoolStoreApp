package com.allcoolstore.repository;

import com.allcoolstore.model.Cart;
import com.allcoolstore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
