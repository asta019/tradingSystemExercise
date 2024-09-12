package com.exercise.trading_system;

import com.exercise.trading_system.controller.OrderController;
import com.exercise.trading_system.dao.OrderTotalPrice;
import com.exercise.trading_system.model.OrderStock;
import com.exercise.trading_system.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Allows non-static @BeforeAll
@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper=new ObjectMapper();
    ObjectWriter objectWriter=objectMapper.writer();

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    OrderStock RECORD_1 = new OrderStock(1,2,30.05);
    OrderStock RECORD_2 = new OrderStock(2,20,120.55);
    OrderStock RECORD_3 = new OrderStock(3,29,383.25);
    OrderStock RECORD_4 = new OrderStock(1,29,383.25);

    @BeforeAll
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        this.mockMvc= MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    public void viewAll_success() throws  Exception{
        List<OrderStock> orderStocks=new ArrayList<>(Arrays.asList(RECORD_1,RECORD_2,RECORD_3));
        Mockito.when(orderService.viewOrders()).thenReturn(orderStocks);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/order/viewAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].stockId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].stockId", Matchers.is(3)));
    }

    @Test
    void testGetTotalInvested_success() throws Exception {
        // Arrange: Set up the mock behavior
        Integer stockId = 1;
        List<OrderStock> orderStocks = Arrays.asList(RECORD_1, RECORD_4);

        // Calculate the total price manually
        double totalPrice = (RECORD_1.getQuantity() * RECORD_1.getPrice()) + (RECORD_4.getQuantity() * RECORD_4.getPrice());
        OrderTotalPrice orderTotalPrice = new OrderTotalPrice(stockId, totalPrice);

        // Mock the service method
        Mockito.when(orderService.getTotalOrder(stockId)).thenReturn(orderTotalPrice);

        // Act: Perform the GET request and validate the result
        mockMvc.perform(MockMvcRequestBuilders.get("/api/order/totalOrder/{stockId}", stockId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.stockId").value(stockId));
    }

    @Test
    public void createOrder_success() throws Exception{
        OrderStock orderStock= OrderStock.builder()
                .id(1)
                .stockId(1)
                .quantity(29)
                .price(37.9)
                .build();
        Mockito.when(orderService.addOrder(orderStock)).thenReturn(orderStock);
        String content=objectWriter.writeValueAsString(orderStock);
        MockHttpServletRequestBuilder mockRequest=MockMvcRequestBuilders.post("/api/order/create")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stockId",Matchers.is(1)));
    }
}
