package com.mvc.mobile.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "specification_data")
public class SpecificationEnity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int specid;
	private String colour;
    private String storage;
    private String camera;
    private String displaySize;
    private String os;
    
    @OneToOne(mappedBy = "specificationEnity")   //(fetch = FetchType.EAGER)  -  optional
	private ProductEntity product_data;
}
