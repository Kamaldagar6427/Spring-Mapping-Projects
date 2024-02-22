package com.mapping.manyToOneUnidirectional.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mapping.manyToOneUnidirectional.entity.Laptop;
import com.mapping.manyToOneUnidirectional.entity.Student;
import com.mapping.manyToOneUnidirectional.repository.LaptopRepository;
import com.mapping.manyToOneUnidirectional.repository.StudentRepository;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private LaptopRepository laptopRepo;

    @GetMapping("/student")
    public ResponseEntity<?> getAllStudents() {
        return ResponseEntity.ok(studentRepo.findAll());
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable int id) {
        Optional<Student> student = studentRepo.findById(id);
        return student.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/student")
    public ResponseEntity<?> createNewStudent(@RequestBody Student student) {
        // Save the student and return ResponseEntity with CREATED status code and the saved student
        Student savedStudent = studentRepo.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Integer id, @RequestBody Student updatedStudent) {
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
                existingStudent.setLaptop(null); // Set laptop to null if no laptop provided
            }

            studentRepo.save(existingStudent);
            return ResponseEntity.ok(existingStudent);
        } else {
            // Handle case where student with given ID is not found
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<?> removeStudentById(@PathVariable int id) {
        Optional<Student> student = studentRepo.findById(id);
        if (student.isPresent()) {
            studentRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            // Handle case where student with given ID is not found
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/student")
    public ResponseEntity<?> removeAllStudents() {
        studentRepo.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
