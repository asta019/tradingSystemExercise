package com.exercise.trading_system.service;

import com.exercise.trading_system.dao.OrderTotalPrice;
import com.exercise.trading_system.model.OrderStock;

import java.util.List;

public interface OrderService {
    OrderStock addOrder(OrderStock orderStock);
    List<OrderStock> viewOrders();
    OrderTotalPrice getTotalOrder(Integer stockId);
}
