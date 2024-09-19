package com.exercise.trading_system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "StockId cannot be null")
    @PositiveOrZero(message = "StockId must be zero or a positive number")
    @NumberFormat
    private Integer stockId;
    @NotNull(message = "StockId cannot be null")
    @PositiveOrZero(message = "Quantity must be zero or a positive number")
    private Integer quantity;
    @NotNull(message = "StockId cannot be null")
    @PositiveOrZero(message = "Price must be zero or a positive number")
    private double price;

    public OrderStock(int stockId, int quantity, double price) {
        this.stockId=stockId;
        this.quantity=quantity;
        this.price=price;
    }
}
