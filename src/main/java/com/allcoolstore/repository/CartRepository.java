package com.allcoolstore.repository;

import com.allcoolstore.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {


    @Transactional
    @Modifying
    @Query("delete from Cart c where c.product.id=:id")
    void deleteByProductId(@Param("id") Long id);

    //@Query("select * from Cart c where c.user.id=:id")
    @Query(value = "select * from shopping_cart where user_id=?1", nativeQuery = true)
    List <Cart> findAllCurrentUser(@Param("id") Long id);
}
