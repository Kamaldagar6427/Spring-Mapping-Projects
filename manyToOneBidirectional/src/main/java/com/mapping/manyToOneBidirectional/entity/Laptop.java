package com.mapping.manyToOneBidirectional.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    
    @OneToMany(mappedBy = "laptop") // Bidirectional relationship mapping
    @JsonManagedReference
    private List<Student> students; // A laptop can have multiple students

	public Laptop() {
		
	}

	public Laptop(Integer id, String modelName, String brand, List<Student> students) {
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

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> student) {
		this.students = student;
	}

	@Override
	public String toString() {
		return "Laptop [id=" + id + ", modelName=" + modelName + ", brand=" + brand + "]";
	}
    
    
}
