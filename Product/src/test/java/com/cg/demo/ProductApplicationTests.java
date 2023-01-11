package com.cg.demo;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.demo.model.Product;
import com.cg.demo.repository.ProductRepository;
import com.cg.demo.service.ProductService;

@SpringBootTest
class ProductApplicationTests {

	@Autowired
	ProductService productService;

	@MockBean
	ProductRepository productRepository;

	@Test
	public void testReadAll() {
		when(productRepository.findAll()).thenReturn(Stream
				.of(new Product(100,"watch",20.45f), new Product(101,  "hoodie",500.34f)).collect(Collectors.toList()));
		assertEquals(2, productService.findAll().size());      //assertEquals(Object expected, Object actual)
	}

	@Test
	public void saveProductTest() {
		Product productDetails=new Product(100,"watch",20.45f);
		when(productRepository.save(productDetails)).thenReturn(productDetails);
		assertEquals(productDetails, productService.save(productDetails));
	}

	@Test
	public void getProductByIdTest() {
		int productId=206;
		Product productDetails=new Product(100,"watch",20.45f);
		System.out.println("Hello"+ productId);
		when(productRepository.findById(productId)).thenReturn(java.util.Optional.of(productDetails));
		System.out.println(productService.findById(productId)+"and"+productDetails);
		assertEquals(productDetails, productService.findById(productId));
	}
}