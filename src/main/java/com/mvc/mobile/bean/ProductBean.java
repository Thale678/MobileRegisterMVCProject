package com.mvc.mobile.bean;

import lombok.Data;

@Data
public class ProductBean {
	private Long productid;

    private String brand;
    private String model;
    private String colour;
    private String storage;
    private String camera;
    private String displaySize;
    private String os;
    private String price;
    private String description;
}
