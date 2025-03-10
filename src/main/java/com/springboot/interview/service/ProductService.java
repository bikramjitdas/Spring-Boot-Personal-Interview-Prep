package com.springboot.interview.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.springboot.interview.model.Product;

@Service
public class ProductService {
	// Trying to replicate without using db,
	List<Product> productList = Stream.of(
			new Product (123,"Iphone 14" , "Mobile" ,"Electronics"),
			new Product (125,"Sony Bravia" , "TV" ,"Electronics"),
			new Product (128,"Maharaja Mixer Grinder", "Mixer Grinder","Home Essentials")
			
	).collect(Collectors.toList());
	
	// Here actually repository will come.
	public List<Product> getProduct(){
		return productList.stream().collect(Collectors.toList());
	}
	public List<Product> getProduct(String productType){
			return productList.
					stream().
					filter(product->(product.getProductType().equals(productType))).
					collect(Collectors.toList());
					
	}
	

}
