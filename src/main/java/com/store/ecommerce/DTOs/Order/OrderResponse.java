package com.store.ecommerce.DTOs.Order;

import java.time.LocalDateTime;
import java.util.List;

import com.store.ecommerce.DTOs.User.UserResponse;
import com.store.ecommerce.model.Order;
import com.store.ecommerce.model.OrderDetails;

public class OrderResponse {
    private Long id;
    private LocalDateTime orderDate;
    private UserResponse userResponse;
    private List<OrderDetails> orderDetails;

    
    public OrderResponse(){}

    public OrderResponse(Order _Order){
        this.id = _Order.getId();
        this.orderDate = _Order.getDateTime();
        this.userResponse = new UserResponse(_Order.getUser());
        this.orderDetails = _Order.GetOrderList();
    }

    public Long getId(){
        return this.id;
    }

    public LocalDateTime getDateTime(){
        return this.orderDate;
    }

    public UserResponse getUserResponse(){
        return this.userResponse;
    }

    public List<OrderDetails> getOrderDetails(){
        return this.orderDetails;
    }

    public void setId(Long _id){
        this.id = _id;
    }
    public void setUserResponse(UserResponse _UserResponse){
        this.userResponse = _UserResponse;
    }
    public void setOrderDate(LocalDateTime _LocalDateTime){
        this.orderDate = _LocalDateTime;
    }
    public void setOrderList(List<OrderDetails> _ordList){
        this.orderDetails = _ordList;
    }
}
