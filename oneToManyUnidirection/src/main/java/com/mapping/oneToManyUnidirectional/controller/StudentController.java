package com.mapping.oneToManyUnidirectional.controller;

import java.util.ArrayList;
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

import com.mapping.oneToManyUnidirectional.entity.Laptop;
import com.mapping.oneToManyUnidirectional.entity.Student;
import com.mapping.oneToManyUnidirectional.repository.LaptopRepository;
import com.mapping.oneToManyUnidirectional.repository.StudentRepository;

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

	        List<Laptop> updatedLaptops = updatedStudent.getLaptop();

	        if (updatedLaptops != null && !updatedLaptops.isEmpty()) {
	            List<Laptop> managedLaptops = new ArrayList<>();
	            for (Laptop updatedLaptop : updatedLaptops) {
	                Optional<Laptop> optionalLaptop = laptopRepo.findById(updatedLaptop.getId());
	                if (optionalLaptop.isPresent()) {
	                    Laptop existingLaptop = optionalLaptop.get();
	                    existingLaptop.setBrand(updatedLaptop.getBrand());
	                    existingLaptop.setModelName(updatedLaptop.getModelName());
	                    managedLaptops.add(existingLaptop);
	                } else {
	                    // Handle case where laptop with given ID is not found
	                    return ResponseEntity.notFound().build();
	                }
	            }
	            existingStudent.setLaptop(managedLaptops);
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


