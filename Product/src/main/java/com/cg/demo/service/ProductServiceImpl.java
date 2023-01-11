package com.cg.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.demo.model.Product;
import com.cg.demo.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
    ProductRepository productRepository;

	@Override
	public List<Product> findAll() {
        List<Product> list=productRepository.findAll();
        System.out.println(list);
        return list;
    }

    @Override
    public Product save(Product Product) {
        System.out.println(Product);
        return productRepository.save(Product);
    }

    @Override
    public void deleteById(int productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Product findById(int id) {
        Optional<Product> op = productRepository.findById(id);
        if(op.isPresent()) {
        	Product productDetails=op.get();
            return productDetails;
        }
        else {
            return null;
        }
    }

	
}
