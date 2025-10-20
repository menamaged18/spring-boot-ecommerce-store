package com.store.ecommerce.DTOs.OrderDetails;

import com.store.ecommerce.model.OrderDetails;

public class OrderDetailsEntityRes {
    private Long id; 
    private Long productId; 
    private Long orderId;
    private Integer productQuantity;

    public OrderDetailsEntityRes(OrderDetails _OrderDetails){
        this.id = _OrderDetails.getId();
        this.productId = _OrderDetails.getProduct().getId();
        this.orderId = _OrderDetails.getOrder().getId();
        this.productQuantity = _OrderDetails.getQuantity();
    }

    // getters
    public Long getId(){
        return this.id;
    }
    public Long getProductId(){
        return this.productId;
    }
    public Long getOrderId(){
        return this.orderId; 
    }
    public Integer getQuantity(){
        return this.productQuantity;
    }
}
