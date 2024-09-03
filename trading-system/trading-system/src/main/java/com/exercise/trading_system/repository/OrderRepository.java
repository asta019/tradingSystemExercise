package com.exercise.trading_system.repository;

import com.exercise.trading_system.model.OrderStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderStock, Integer> {
    @Query(value="SELECT * FROM order_stock WHERE stock_id=:stockId", nativeQuery = true)
    List<OrderStock> findByStockId(int stockId);
}
