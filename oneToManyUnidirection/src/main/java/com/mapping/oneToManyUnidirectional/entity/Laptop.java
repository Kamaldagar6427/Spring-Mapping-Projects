package com.mapping.oneToManyUnidirectional.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "jpa_laptop")
public class Laptop   {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "laptop_id")
	private Integer id;
	private String modelName;
	private String brand;
	
	public Laptop() {
		
	}

	public Laptop(Integer id, String modelName, String brand) {
		super();
		this.id = id;
		this.modelName = modelName;
		this.brand = brand;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "Laptop [id=" + id + ", modelName=" + modelName + ", brand=" + brand + "]";
	}
	

	
	
	



	
	
}
