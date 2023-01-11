package com.cg.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.demo.model.Product;
import com.cg.demo.repository.ProductRepository;
import com.cg.demo.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @PostMapping("/add")
    public ResponseEntity<String> insertCrops(@RequestBody Product product) {
        try {
            productService.save(product);
            return new ResponseEntity<String>("Product added successfully", HttpStatus.CREATED);
        }catch(Exception e) {
            return new ResponseEntity<String>("There have some problem in the entry", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/allProduct")
    public ResponseEntity<Object> getAllCrops(){
        try {
            List<Product> list=new ArrayList<>();
            productService.findAll().forEach(list::add);
            if(!list.isEmpty()) {
                return new ResponseEntity<Object>(list, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<Object>("There is no product in the list", HttpStatus.NO_CONTENT);
            }
        }catch(Exception e) {
            return new ResponseEntity<Object>("Connection problem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateProduct/{productId}")
    public ResponseEntity<Object> updateProductById(@PathVariable("productId") int productId, @RequestBody Product product)
    {
        try {
            Optional<Product> op=productRepository.findById(productId);
            if(op.isPresent())
            {
            	Product productDetails1=op.get();
                productService.save(product);
                return new ResponseEntity<Object>("The Data is updated successfully for id "+productDetails1.getProductId(), HttpStatus.OK);

            }
            else
            {
                return new ResponseEntity<Object>("ProductID NOT FOUND", HttpStatus.NOT_FOUND);
            }
        }catch(Exception e) {
            return new ResponseEntity<Object>("Connection problem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteProduct/{productId}")
    public ResponseEntity<String> deleteProductById(@PathVariable("productId") int productId) {
        try {
            Optional<Product> op = productRepository.findById(productId);
            if(op.isPresent()) {
                productService.deleteById(productId);
                return new ResponseEntity<String>("Product deleted Successfully", HttpStatus.OK);
            }
            else {
                return new ResponseEntity<String>("There is no such Product Id", HttpStatus.NO_CONTENT);
            }
        }catch(Exception e) {
            return new ResponseEntity<String>("Connection problem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}