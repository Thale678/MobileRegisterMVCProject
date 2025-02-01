package com.mvc.mobile.service;

import java.util.List;

import com.mvc.mobile.bean.ProductBean;

public interface ProductService {
	
public ProductBean addProduct(ProductBean productBean);
public List<ProductBean> getAllProducts();
public ProductBean getProductByID(Long id);
public boolean validateUser(String username,String password);
	
}
