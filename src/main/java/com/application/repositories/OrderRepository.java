package com.application.repositories;

import com.application.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Integer> {

    public List<OrderEntity> findByUserId(Integer userId);

}
