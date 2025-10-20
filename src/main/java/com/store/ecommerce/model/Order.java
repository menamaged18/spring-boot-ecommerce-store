package com.store.ecommerce.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomUser user;

    // to retrieve order details
    // what table will store is for example number of products 
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetails> orderDetails;


    @DateTimeFormat(pattern = "dd-MM-yyyy") // For request parameters/form submissions
    @JsonFormat(pattern = "dd-MM-yyyy") // For JSON serialization/deserialization
    private LocalDateTime orderDate = LocalDateTime.now();

    public Order(){}

    public Long getId(){
        return this.id;
    }

    public CustomUser getUser() {
        return user;
    }

    public void setUser(CustomUser user) {
        this.user = user;
    }

    public List<OrderDetails> GetOrderList(){
        return this.orderDetails;
    }

    public void setOrderList(List<OrderDetails> orderlist){
        this.orderDetails = orderlist;
    }

    public void addOrderDetails(OrderDetails ord){
        this.orderDetails.add(ord);
    }

    public LocalDateTime getDateTime(){
        return this.orderDate;
    }
}
