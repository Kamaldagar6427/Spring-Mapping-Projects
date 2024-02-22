package com.mapping.oneToOneBidirectional.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "jpa_student")
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_id")
    private int id;
    
    private String studentName;
    private String about;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_laptop_id")
    @JsonManagedReference
    private Laptop laptop;
        
    public Student() {
    
    }

    public Student(int id, String studentName, String about, Laptop laptop) {
        this.id = id;
        this.studentName = studentName;
        this.about = about;
        this.laptop = laptop;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", studentName=" + studentName + ", about=" + about + ", laptop=" + laptop + "]";
    }
}
