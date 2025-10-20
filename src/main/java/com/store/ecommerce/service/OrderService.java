package com.store.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.store.ecommerce.model.CustomUser;
import com.store.ecommerce.model.Order;
import com.store.ecommerce.model.OrderDetails;
import com.store.ecommerce.repository.OrderRepo;

@Service
public class OrderService {
    private final OrderRepo orderRepo;
    private final UserService userServ;

    public OrderService(OrderRepo or, UserService userServ){
        this.orderRepo = or;
        this.userServ = userServ;
    }

    public Order returnOrderById(Long id){
        return orderRepo.findById(id).orElseThrow(()-> new RuntimeException("Order not found!"));
    }

    public Order makeOrder(Long UserId){
        Order ord = new Order();
        try {
            // This line can throw a RuntimeException if the user isn't found
            CustomUser user = userServ.ReturnCustomUserById(UserId);
            ord.setUser(user);
            orderRepo.save(ord); // This might also throw a DataAccessException, which could be worth catching
        } catch (RuntimeException e) {
            System.err.println("Error making order: " + e.getMessage());
        }
        return ord;
    }

    public Order addOrder(CustomUser user, OrderDetails orderDetails){
        Order ord = new Order();
        ord.setUser(user);
        ord.addOrderDetails(orderDetails);
        return orderRepo.save(ord);
    }

    public Order addOrder(CustomUser user, List<OrderDetails> listofOrders){
        Order ord = new Order();
        ord.setUser(user);
        ord.setOrderList(listofOrders);
        return orderRepo.save(ord);
    }

    public void deleteOrder(Long id){
        orderRepo.deleteById(id);
    }

    public List<Order> getAllOrders(){
        return orderRepo.findAll();
    }

    public Order getOrderById(Long id){
        return orderRepo.findById(id).orElseThrow(()-> new RuntimeException("Order not found")); 
    }

    public List<Order> getAllUserOrders(Long userId){
        return orderRepo.findByUser_id(userId);
    }

    public Order EditOrder(Long orderId, Order newOrder){
        Order oldOrder = returnOrderById(orderId);
        if (newOrder.getUser() != null) {
            oldOrder.setUser(newOrder.getUser());
        }

        if (newOrder.GetOrderList() != null) {
            oldOrder.setOrderList(newOrder.GetOrderList());
        }

        return orderRepo.save(oldOrder);
    }

}
