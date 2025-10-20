package com.store.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private Long quantity;
    private String name;
    private String category;
    private String description;

    public Product(){}
    public Product(String _name, String _category, String _description, long _quantity){
        this.name = _name;
        this.category = _category;
        this.description = _description;
        this.quantity = _quantity;
    }

    public Long getId(){
        return this.id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}


