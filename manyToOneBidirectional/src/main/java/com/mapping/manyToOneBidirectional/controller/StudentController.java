package com.mapping.manyToOneBidirectional.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mapping.manyToOneBidirectional.entity.Laptop;
import com.mapping.manyToOneBidirectional.entity.Student;
import com.mapping.manyToOneBidirectional.repository.LaptopRepository;
import com.mapping.manyToOneBidirectional.repository.StudentRepository;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepo;
    @Autowired
    private LaptopRepository laptopRepo;

    @GetMapping("/student")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentRepo.findAll());
    }
    @GetMapping("/laptop")
    public ResponseEntity<List<Laptop>> getAllLaptops() {
    	return ResponseEntity.ok(laptopRepo.findAll());
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable int id) {
        Optional<Student> student = studentRepo.findById(id);
        return student.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    
    @PostMapping("/student")
    public ResponseEntity<?> createNewStudent(@RequestBody Student student) {
        // Ensure that the Laptop object referenced by the student is saved before saving the student
        Laptop laptop = student.getLaptop();
        if (laptop != null && laptop.getId() == null) {
            // Save the laptop object if it's not already saved
            laptopRepo.save(laptop);
        }
        
        // Now save the student
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
            existingStudent.setLaptop(updatedStudent.getLaptop());

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
