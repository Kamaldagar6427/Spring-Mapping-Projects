package com.mapping.manyToManyBidirectional.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "jpa_laptop")
public class Laptop {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "laptop_id")
    private Integer id;
    
    private String modelName;
    private String brand;
    
    @ManyToMany(mappedBy = "laptops", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Student> students;
	public Laptop() {
		
	}

	public Laptop(Integer id, String modelName, String brand, Set<Student> students) {
		super();
		this.id = id;
		this.modelName = modelName;
		this.brand = brand;
		this.students = students;
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

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Laptop [id=" + id + ", modelName=" + modelName + ", brand=" + brand + ", students=" + students + "]";
	}

	
    
    
}
