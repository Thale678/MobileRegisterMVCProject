package com.mvc.mobile.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mvc.mobile.entity.LoginEntity;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity,Integer>{
	
	public Optional<LoginEntity>  findByUsernameAndPassword(String username,String password);
	public boolean  existsByUsernameAndPassword(String username,String password);

}
