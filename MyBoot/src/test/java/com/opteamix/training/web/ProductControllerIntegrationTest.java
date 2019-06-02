package com.opteamix.training.web;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.opteamix.training.MyBootApplication;
import com.opteamix.training.dal.ProductDAO;
import com.opteamix.training.domain.Product;

@RunWith(SpringRunner.class)
@SpringBootTest(
  webEnvironment=SpringBootTest.WebEnvironment.MOCK,
  classes = MyBootApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
  locations = "classpath:application-test.properties")

public class ProductControllerIntegrationTest {

    @Autowired
    MockMvc mvc;
    
    @Autowired
    ProductDAO dao;
    
    @Test
    public void testGetById() throws Exception {
        //Arrange
        Product data = new Product("test", 1999, 1000);
        //data.setId(1);
        Product created = dao.save(data);
        int id = created.getId();
        //Act and Assert
        mvc.perform(get("/products/{id}", id).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(id)));
        
    }

}