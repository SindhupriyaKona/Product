package com.cg.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.demo.model.Product;
import com.cg.demo.repository.ProductRepository;
	@Service
	public interface ProductService {
	  
		 List<Product> findAll();

		    Product save(Product product);

		    void deleteById(int productId);

		    Product findById(int id);
		}

