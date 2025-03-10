package com.springboot.interview.model;

import jakarta.persistence.Entity;

@Entity
public class Product {
   Integer productId;
   public Product(Integer productId, String productName, String productCategory, String productType) {
	this.productId = productId;
	this.productName = productName;
	this.productCategory = productCategory;
	this.productType = productType;
}
String productName;
   String productCategory;
   String productType;
public Integer getProductId() {
	return productId;
}
public void setProductId(Integer productId) {
	this.productId = productId;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public String getProductCategory() {
	return productCategory;
}
public void setProductCategory(String productCategory) {
	this.productCategory = productCategory;
}
public String getProductType() {
	return productType;
}
public void setProductType(String productType) {
	this.productType = productType;
}
   
   
}
