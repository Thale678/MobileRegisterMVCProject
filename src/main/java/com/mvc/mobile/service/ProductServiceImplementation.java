package com.mvc.mobile.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.mvc.mobile.bean.ProductBean;
import com.mvc.mobile.entity.ProductEntity;
import com.mvc.mobile.entity.SpecificationEnity;
import com.mvc.mobile.repository.LoginRepository;
import com.mvc.mobile.repository.ProductRepository;

@Service
public class ProductServiceImplementation implements ProductService{
	
	@Autowired
	public ProductRepository productRepository;
	
	@Autowired
	public LoginRepository loginRepository;

	@Override
	public ProductBean addProduct(ProductBean productBean) {
		// TODO Auto-generated method stub
		
		ProductEntity productEntity = new ProductEntity();
		
		BeanUtils.copyProperties(productBean, productEntity);
		//System.out.println(productEntity);
		SpecificationEnity specificationEnity = new SpecificationEnity();
		BeanUtils.copyProperties(productBean, specificationEnity);
		specificationEnity.setProduct_data(productEntity);
		System.out.println(specificationEnity); 
		productEntity.setSpecificationEnity(specificationEnity);
		System.out.println(productEntity);
		/*specificationEnity.setCamera(productBean.getCamera());
		specificationEnity.setColour(productBean.getColour());
		specificationEnity.setDisplaySize(productBean.getDisplaySize());
		specificationEnity.setOs(productBean.getOs());
		specificationEnity.setStorage(productBean.getStorage());
		specificationEnity.setProduct_data(productEntity);*/
		
		ProductEntity save = productRepository.save(productEntity);
		
		productBean.setProductid(save.getProductid());
		
		
		
		
		return productBean;
	}

	@Override
	public List<ProductBean> getAllProducts() {
		// TODO Auto-generated method stub
		List<ProductEntity> productsentity = productRepository.findAll();
		
		List<ProductBean> products = new ArrayList<>();
		
		for(ProductEntity E:productsentity) {
			ProductBean P = new ProductBean();
			
			BeanUtils.copyProperties(E, P);
			BeanUtils.copyProperties(E.getSpecificationEnity(), P);
			
			products.add(P);
			System.out.println(products);
			
			
			
			
		}
		
		return products;
	}

	@Override
	public ProductBean getProductByID(Long id) {
		// TODO Auto-generated method stub
		Optional<ProductEntity> E = productRepository.findById(id);
		ProductBean product = new ProductBean();
		BeanUtils.copyProperties(E.get(), product);
		BeanUtils.copyProperties(E.get().getSpecificationEnity(), product);
		return product;
	}

	@Override
	public boolean validateUser(String username, String password) {
		// TODO Auto-generated method stub
		return loginRepository.existsByUsernameAndPassword(username, password);
	}

}
