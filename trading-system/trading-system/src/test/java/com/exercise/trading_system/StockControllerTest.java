package com.exercise.trading_system;

import com.exercise.trading_system.controller.StockController;
import com.exercise.trading_system.model.Stock;
import com.exercise.trading_system.service.StockService;
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

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class StockControllerTest {
    private MockMvc mockMvc;

    ObjectMapper objectMapper=new ObjectMapper();
    ObjectWriter objectWriter=objectMapper.writer();

    @Mock
    private StockService stockService;

    @InjectMocks
    private StockController stockController;

    Stock stock1=new Stock(1, "SM", 30.5);
    Stock stock2=new Stock(2,"Robinson",2000.50);
    Stock stock3=new Stock(3, "Ayala", 30000.88);

    @BeforeAll
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        this.mockMvc= MockMvcBuilders.standaloneSetup(stockController).build();
    }

    @Test
    public void create_success() throws Exception{
        Stock stock=Stock.builder()
                .id(1)
                .name("New point")
                .price(450.45)
                .build();

        Mockito.when(stockService.createStock(stock)).thenReturn(stock);
        String content=objectWriter.writeValueAsString(stock);

        MockHttpServletRequestBuilder mockRequest= MockMvcRequestBuilders.post("/api/stock/create")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.is("New point")));
    }

}
