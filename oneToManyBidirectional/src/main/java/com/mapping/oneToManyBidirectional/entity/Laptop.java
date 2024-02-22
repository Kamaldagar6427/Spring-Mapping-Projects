package com.mapping.oneToManyBidirectional.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    
    @ManyToOne 
    @JoinColumn(name = "student_id")
    @JsonBackReference
    private Student student;
    
    public Laptop() {
        
    }

    public Laptop(Integer id, String modelName, String brand, Student student) {
        super();
        this.id = id;
        this.modelName = modelName;
        this.brand = brand;
        this.student = student;
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Laptop [id=" + id + ", modelName=" + modelName + ", brand=" + brand + ", student=" + student + "]";
    }
}
