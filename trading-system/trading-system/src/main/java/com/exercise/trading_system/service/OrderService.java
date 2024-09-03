package com.exercise.trading_system.service;

import com.exercise.trading_system.dao.OrderTotalPrice;
import com.exercise.trading_system.model.OrderStock;
import com.exercise.trading_system.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    private OrderTotalPrice orderTotalPrice;
    public OrderStock addOrder(OrderStock orderStock) {
        orderRepository.save(orderStock);
        return orderStock;
    }

    public List<OrderStock> viewOrders() {
        return orderRepository.findAll();
    }


    public OrderTotalPrice getTotalOrder(Integer stockId) {
        List<OrderStock> orderStocks=orderRepository.findByStockId(stockId);
        double totalPrice, totalStockPrice=0;
        for(OrderStock orderStock: orderStocks){
            totalPrice=(orderStock.getQuantity() * orderStock.getPrice());
            totalStockPrice=totalStockPrice+totalPrice;
        }
        orderTotalPrice=new OrderTotalPrice(stockId, totalStockPrice);
        return orderTotalPrice;
    }
}
