package com.exercise.trading_system.controller;

import com.exercise.trading_system.model.Stock;
import com.exercise.trading_system.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stock")
public class StockController {
    @Autowired
    private StockService stockService;

    @PostMapping("/create")
    public void createStock(@RequestBody Stock stock){
        stockService.createStock(stock);
    }

}
