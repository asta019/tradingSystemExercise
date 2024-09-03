package com.exercise.trading_system.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class OrderTotalPrice {
    private Integer stockId;
    private double total_price;

}
