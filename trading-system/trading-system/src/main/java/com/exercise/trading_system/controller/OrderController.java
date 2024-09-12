package com.exercise.trading_system.controller;

import com.exercise.trading_system.dao.OrderTotalPrice;
import com.exercise.trading_system.model.OrderStock;
import com.exercise.trading_system.service.OrderService;
import com.exercise.trading_system.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order")

public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("create")
    public ResponseEntity<OrderStock> createOrder(@RequestBody OrderStock orderStock){
        orderService.addOrder(orderStock);
       return new ResponseEntity<>(orderStock, HttpStatus.OK);
    }

    @GetMapping("viewAll")
    public ResponseEntity<List<OrderStock>> viewOrders(){
        return new ResponseEntity<>(orderService.viewOrders(),HttpStatus.OK);
    }

    @GetMapping("totalOrder/{stockId}")
    public ResponseEntity<OrderTotalPrice> getTotalInvested(@PathVariable Integer stockId){
        return new ResponseEntity<OrderTotalPrice>(orderService.getTotalOrder(stockId),HttpStatus.OK);
    }
}
