package com.allcoolstore.repository;

import com.allcoolstore.model.Cart;
import com.allcoolstore.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select * from orders where user_id=?1", nativeQuery = true)
    List<Order> findAllOrdersCurrentUser(@Param("id") Long id);

}
