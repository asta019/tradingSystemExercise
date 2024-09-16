package com.exercise.trading_system.controller;

import com.exercise.trading_system.model.Stock;
import com.exercise.trading_system.service.StockService;
import com.exercise.trading_system.service.StockServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Stock> createStock(@Valid @RequestBody Stock stock){
        stockService.createStock(stock);
        return new ResponseEntity<Stock>(stock, HttpStatus.OK);
    }

}
