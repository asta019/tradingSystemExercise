package com.exercise.trading_system.service;

import com.exercise.trading_system.model.Stock;
import com.exercise.trading_system.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
    StockRepository stockRepository;

    @Override
    public Stock createStock(Stock stock) {
        stockRepository.save((stock));
        return stock;
    }

}
