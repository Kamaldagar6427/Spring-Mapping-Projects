package com.mapping.oneToOneBidirectional.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mapping.oneToOneBidirectional.entity.Laptop;
import com.mapping.oneToOneBidirectional.entity.Student;
import com.mapping.oneToOneBidirectional.repository.LaptopRepository;
import com.mapping.oneToOneBidirectional.repository.StudentRepository;

@RestController
public class StudentController {

	@Autowired
	private StudentRepository studentRepo;
	@Autowired
	private LaptopRepository laptopRepo;
	
	
	
	@GetMapping("/student")
	public List<Student> getAllStudent(){
		return studentRepo.findAll();
	}
	
	@GetMapping("/student/{id}")
	public void getStudentById(@PathVariable int id) {
		studentRepo.findById(id);
	}
	
	@PostMapping("/student")
	public Student creteNewStudent(@RequestBody Student student) {
		return studentRepo.save(student);
	}

	
	@PutMapping("/student/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable Integer id, @RequestBody Student updatedStudent) {
	    Optional<Student> optionalStudent = studentRepo.findById(id);
	    
	    if (optionalStudent.isPresent()) {
	        Student existingStudent = optionalStudent.get();
	        existingStudent.setStudentName(updatedStudent.getStudentName());
	        existingStudent.setAbout(updatedStudent.getAbout());
	        
	        Laptop updatedLaptop = updatedStudent.getLaptop();
	        
	        if (updatedLaptop != null) {
	            Optional<Laptop> optionalLaptop = laptopRepo.findById(updatedLaptop.getId());
	            if (optionalLaptop.isPresent()) {
	                Laptop existingLaptop = optionalLaptop.get();
	                existingLaptop.setBrand(updatedLaptop.getBrand());
	                existingLaptop.setModelName(updatedLaptop.getModelName());
	                existingStudent.setLaptop(existingLaptop);
	            } else {
	                // Handle case where laptop with given ID is not found
	                return ResponseEntity.notFound().build();
	            }
	        } else {
	            existingStudent.setLaptop(null);
	        }
	        
	        studentRepo.save(existingStudent);
	        return ResponseEntity.ok(existingStudent);
	    } else {
	        // Handle case where student with given ID is not found
	        return ResponseEntity.notFound().build();
	    }
	}

	
	
	@DeleteMapping("/student/{id}")
	public void removeStudentById(@PathVariable int id) {
		studentRepo.deleteById(id);
	}
	
	@DeleteMapping("/student")
	public void removeAllStudent() {
		studentRepo.deleteAll();
	}
}


