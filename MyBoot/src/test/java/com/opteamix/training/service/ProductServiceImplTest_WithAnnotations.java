package com.opteamix.training.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.opteamix.training.dal.ProductDAO;
import com.opteamix.training.domain.Product;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest_WithAnnotations {
	@Mock
	ProductDAO mockDAO;
	
	@Test
	public void addNewProduct_Returns_Valid_Id_When_Value_Gt_MinValue() {
		// Arrange
		ProductServiceImpl service = new ProductServiceImpl();
		Product toBeAdded = new Product("test", 10000, 10);
		//ProductDAO mockDAO = Mockito.mock(ProductDAO.class);
		Product returned = new Product("test", 10000, 10);
		returned.setId(1);
		Mockito.when(mockDAO.save(toBeAdded)).thenReturn(returned);
		service.setDao(mockDAO);
		// Act
		int id = service.addNewProduct(toBeAdded);
		// Assert
		assertTrue(id > 0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void addNewProduct_Throws_When_Value_Lt_MinValue() {
		//Arrange
		ProductServiceImpl service = new ProductServiceImpl();
		Product toBeAdded = new Product("test",9999,1);
		//Act
		service.addNewProduct(toBeAdded);
	}

}
