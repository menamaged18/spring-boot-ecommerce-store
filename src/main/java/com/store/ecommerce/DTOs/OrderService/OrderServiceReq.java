package com.store.ecommerce.DTOs.OrderService;

public class OrderServiceReq {
    private Long orderId; 
    private Long productId; 
    private int productQuantity;

    public Long getOrderId(){
        return this.orderId;
    }

    public Long getProductId(){
        return this.productId;
    }

    public int getProductQuantity(){
        return this.productQuantity;
    }
}
