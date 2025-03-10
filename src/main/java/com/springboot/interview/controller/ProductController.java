package com.springboot.interview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.interview.model.Product;
import com.springboot.interview.service.ProductService;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "https://localhost:4000")
public class ProductController {
   @Autowired 
   private ProductService productService;
   
   @GetMapping("/{productType}")
   @Operation(description="fetch the products by type")
   public ResponseEntity<?> getProducts(@PathVariable("productType") String productType){
	   List<Product> productList = null;
	   try {
		   productList = productService.getProduct(productType);
		   
	   }catch(Exception e) {
		   System.out.println("Exception e " + e);
	   }
	   return new ResponseEntity<>(productList,HttpStatus.OK);
	   
	   
   }
   
   @GetMapping("/filter")
   @Hidden
   public ResponseEntity<?> getProductList(@RequestParam(value = "productType", required = false, defaultValue ="Electronics") String productType){
	   List<Product> productList = null;
	   try {   
		   System.out.println(productType);
		   		productList = productType==null?productService.getProduct():productService.getProduct(productType);
	   }
	   catch(Exception e) {
		   System.out.println("Exception e " + e);
	   }
	   return new ResponseEntity<>(productList,HttpStatus.OK);
   }
}
