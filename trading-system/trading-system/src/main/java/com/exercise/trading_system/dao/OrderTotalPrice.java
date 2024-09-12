package com.exercise.trading_system.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderTotalPrice {
    private Integer stockId;
    private double total_price;

    public OrderTotalPrice(Integer stockId) {
        this.stockId=stockId;
    }
}
