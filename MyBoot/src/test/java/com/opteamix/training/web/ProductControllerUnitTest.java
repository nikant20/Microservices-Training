package com.opteamix.training.web;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.opteamix.training.domain.Product;
import com.opteamix.training.service.ProductService;

@RunWith(SpringRunner.class)
@WebMvcTest(value= {ProductController.class})
public class ProductControllerUnitTest {

    @Autowired
    MockMvc mockMvc;
    
    @MockBean
    ProductService service;
    
    @Test
    public void test_GET_Product_Item_Must_Return_Success() throws Exception {
        //Arrange
        int id = 1;
        Product dataReturned = new Product("test", 9999, 1000);
        dataReturned.setId(id);
        Mockito.when(service.findById(id)).thenReturn(dataReturned);
        //Act and Assert
        mockMvc.perform(get("/products/{id}", 1).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(1)));    
    }

}