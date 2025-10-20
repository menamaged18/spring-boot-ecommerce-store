package com.store.ecommerce.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.ecommerce.DTOs.OrderDetails.OrderDetailsEntityRes;
import com.store.ecommerce.DTOs.OrderService.OrderServiceReq;
import com.store.ecommerce.model.OrderDetails;
import com.store.ecommerce.service.OrderDetailsService;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/orderDetails/")
public class OrderDetailsController {
    private final OrderDetailsService ordDetServ;

    public OrderDetailsController(OrderDetailsService _ordDetServ){
        this.ordDetServ = _ordDetServ;
    }

    @PostMapping("")
    public OrderServiceReq addOrderDetails(@RequestBody OrderServiceReq orddet){
        ordDetServ.addOrderDetails(orddet);
        return orddet;
    }

    @GetMapping("{id}")
    public OrderDetailsEntityRes getOrderDetails(@PathVariable Long id){
        return new OrderDetailsEntityRes(ordDetServ.getOrderDetailsByid(id));
    }

    @GetMapping("userOrder/{orderId}")
    public List<OrderDetailsEntityRes> getUserOrder(@PathVariable Long orderId) {
        return ordDetServ.GetUserOrder(orderId).stream()
                            .map(orderDetails -> new OrderDetailsEntityRes(orderDetails))
                            .collect(Collectors.toList());
    }

    @PutMapping("editOrderDetails/{id}")
    public OrderDetailsEntityRes editOrderDetails(@PathVariable Long id, @RequestBody OrderDetails _OrderDetails) { 
        return new OrderDetailsEntityRes(ordDetServ.editOrderDetails(id, _OrderDetails));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteOrderDetails(@PathVariable Long id){
        ordDetServ.deleteOrderDetails(id);
        return ResponseEntity.noContent().build();
    }
    
}
