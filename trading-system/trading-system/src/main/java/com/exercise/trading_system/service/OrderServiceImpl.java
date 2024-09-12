package com.exercise.trading_system.service;

import com.exercise.trading_system.dao.OrderTotalPrice;
import com.exercise.trading_system.model.OrderStock;
import com.exercise.trading_system.repository.OrderRepository;
import com.exercise.trading_system.utilities.InvalidOrderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    private OrderTotalPrice orderTotalPrice;

    @Override
    public OrderStock addOrder(OrderStock orderStock) {
        if(orderStock == null){
            throw new InvalidOrderException("Stock is null.");
        }
        orderRepository.save(orderStock);
        return orderStock;
    }
    @Override
    public List<OrderStock> viewOrders() {
        return orderRepository.findAll();
    }
    @Override
    public OrderTotalPrice getTotalOrder(Integer stockId) {
        List<OrderStock> orderStocks=orderRepository.findByStockId(stockId);
        if(orderStocks==null)
            throw new InvalidOrderException("Order is not found.");

        double totalPrice, totalStockPrice=0;
        for(OrderStock orderStock: orderStocks){
            totalPrice=(orderStock.getQuantity() * orderStock.getPrice());
            totalStockPrice=totalStockPrice+totalPrice;
        }
        orderTotalPrice=new OrderTotalPrice(stockId, totalStockPrice);
        return orderTotalPrice;
    }
}
