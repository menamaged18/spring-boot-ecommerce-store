package com.store.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.ecommerce.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long>{
    
}
