package com.store.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.ecommerce.model.OrderDetails;
import java.util.List;


@Repository
public interface OrderDetailsRepo extends JpaRepository<OrderDetails, Long> {
    // to get the order details
    List<OrderDetails> findByorder_id(Long orederId);
}
