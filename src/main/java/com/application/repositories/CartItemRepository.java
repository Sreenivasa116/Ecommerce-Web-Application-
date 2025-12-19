package com.application.repositories;

import com.application.entities.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity,Integer> {
    List<CartItemEntity> findByCartId(Integer cartId);

    @Modifying
    @Query("DELETE FROM CartItemEntity c WHERE c.cart.id = :cartId")
    void deleteAllByCart_Id(@Param("cartId")Integer cartId);
}
