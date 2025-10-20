package com.store.ecommerce.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.store.ecommerce.DTOs.OrderService.OrderServiceReq;
import com.store.ecommerce.model.Order;
import com.store.ecommerce.model.OrderDetails;
import com.store.ecommerce.model.Product;
import com.store.ecommerce.repository.OrderDetailsRepo;

@Service
public class OrderDetailsService {
    private final OrderDetailsRepo ordDetRep;
    private final OrderService ordServ;
    private final ProductService prodServ;

    public OrderDetailsService(OrderDetailsRepo _orderDetailsRepo, OrderService _ordserv, ProductService _prodserv){
        this.ordDetRep = _orderDetailsRepo;
        this.ordServ = _ordserv;
        this.prodServ = _prodserv; 
    }

    @Transactional 
    public void addOrderDetails(OrderServiceReq orderService){
        Product product = prodServ.returnProductById(orderService.getProductId()); 
        Order order = ordServ.returnOrderById(orderService.getOrderId()); 
        int quantity = orderService.getProductQuantity();

        // This method will check stock and throw a 400 error if not enough
        prodServ.reduceStock(product, quantity); 

        OrderDetails orddet = new OrderDetails(
                                    order,
                                    product, 
                                    quantity);
        ordDetRep.save(orddet); 
    }

    public void deleteOrderDetails(Long id){
        ordDetRep.deleteById(id);
    }

    public OrderDetails getOrderDetailsByid(Long id){
        return ordDetRep.findById(id).orElseThrow(() -> 
                new ResponseStatusException(HttpStatus.NOT_FOUND, "OrderDetails not found or not exist"));
    }

    public List<OrderDetails> GetUserOrder(Long OrderId){
        return ordDetRep.findByorder_id(OrderId);
    }

    public OrderDetails editOrderDetails(Long orderDetailsID, OrderDetails newOrderDetails){
        OrderDetails oldOrderDetails = getOrderDetailsByid(orderDetailsID);

        if (newOrderDetails.getQuantity() != null) {
            oldOrderDetails.setQuantity(newOrderDetails.getQuantity());
        }

        if (newOrderDetails.getProduct() != null) {
            oldOrderDetails.setProduct(newOrderDetails.getProduct());
        }

        return ordDetRep.save(oldOrderDetails);
    }
    
}
