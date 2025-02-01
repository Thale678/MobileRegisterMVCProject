package com.mvc.mobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mvc.mobile.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
