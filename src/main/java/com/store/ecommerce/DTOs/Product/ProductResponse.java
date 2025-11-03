package com.store.ecommerce.DTOs.Product;

import java.time.LocalDateTime;

import com.store.ecommerce.model.Product;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductResponse {
    private Long id;
    private String name;
    private Long quantity;
    private Double price;
    private Double cost_price;
    private String category;
    private String description;
    private String brand;
    private Double weight;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private Boolean is_active;

    public ProductResponse(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.quantity = product.getQuantity();
        this.price = product.getPrice();
        this.cost_price = product.getCost_price();
        this.category = product.getCategory();
        this.description = product.getDescription();
        this.brand = product.getBrand();
        this.weight = product.getWeight();
        this.created_at = product.getCreated_at();
        this.updated_at = product.getUpdated_at();
        this.is_active = product.getIs_active();
    }
}
