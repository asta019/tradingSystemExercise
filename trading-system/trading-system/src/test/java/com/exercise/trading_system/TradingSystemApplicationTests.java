package com.exercise.trading_system;

import com.exercise.trading_system.model.OrderStock;
import com.exercise.trading_system.repository.OrderRepository;
import com.exercise.trading_system.service.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TradingSystemApplicationTests {
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	@MockBean
	private OrderRepository orderRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void createOrdersTest(){
		Mockito.when(orderRepository.findAll()).thenReturn(Stream
				.of(new OrderStock(1, 2, 30.0), new OrderStock(1, 3, 28.0), new OrderStock(1,5,29.0)).collect(Collectors.toList()));
		Assertions.assertEquals(3, orderServiceImpl.viewOrders().size());
	}
	@Test
	public void getTotalInvestedTest(){
		Integer stockId=1;
		Mockito.when(orderRepository.findByStockId(stockId))
				.thenReturn(Stream.of(new OrderStock(1,20,30.00)).collect(Collectors.toList()));
		Assertions.assertEquals(1, orderServiceImpl.getTotalOrder(stockId));
	}

}
