package com.mvc.mobile.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "logindata")
public class LoginEntity {
	@Id
	private int id;
	private String username;
	private String password;
	private String status;
	private String role;
}
