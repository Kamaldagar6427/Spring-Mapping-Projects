package com.mapping.manyToManyUnidirectional.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mapping.manyToManyUnidirectional.entity.Laptop;
import com.mapping.manyToManyUnidirectional.entity.Student;
import com.mapping.manyToManyUnidirectional.repository.LaptopRepository;
import com.mapping.manyToManyUnidirectional.repository.StudentRepository;

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

//    @PostMapping("/student")
//    public ResponseEntity<?> createNewStudent(@RequestBody Student student) {
//        // Save the student and return ResponseEntity with CREATED status code and the saved student
//        Student savedStudent = studentRepo.save(student);
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
//    }
    
    @PostMapping("/student")
    public ResponseEntity<?> createNewStudent(@RequestBody Student student) {
        // Ensure that the Laptop objects referenced by the student are saved before saving the student
        Set<Laptop> laptops = student.getLaptops();
        if (laptops != null && !laptops.isEmpty()) {
            for (Laptop laptop : laptops) {
                if (laptop.getId() == null) {
                    // Save the laptop object if it's not already saved
                    laptopRepo.save(laptop);
                }
            }
        }

        // Now save the student
        Student savedStudent = studentRepo.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }




//    @PutMapping("/student/{id}")
//    public ResponseEntity<?> updateStudent(@PathVariable Integer id, @RequestBody Student updatedStudent) {
//        Optional<Student> optionalStudent = studentRepo.findById(id);
//
//        if (optionalStudent.isPresent()) {
//            Student existingStudent = optionalStudent.get();
//            existingStudent.setStudentName(updatedStudent.getStudentName());
//            existingStudent.setAbout(updatedStudent.getAbout());
//            existingStudent.setLaptops(updatedStudent.getLaptops());
//
//            studentRepo.save(existingStudent);
//            return ResponseEntity.ok(existingStudent);
//        } else {
//            // Handle case where student with given ID is not found
//            return ResponseEntity.notFound().build();
//        }
//    }
    
    @PutMapping("/student/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Integer id, @RequestBody Student updatedStudent) {
        Optional<Student> optionalStudent = studentRepo.findById(id);

        if (optionalStudent.isPresent()) {
            Student existingStudent = optionalStudent.get();
            existingStudent.setStudentName(updatedStudent.getStudentName());
            existingStudent.setAbout(updatedStudent.getAbout());

            // Ensure that the Laptop objects referenced by the updated student are saved before updating the student
            Set<Laptop> updatedLaptops = updatedStudent.getLaptops();
            if (updatedLaptops != null && !updatedLaptops.isEmpty()) {
                for (Laptop laptop : updatedLaptops) {
                    if (laptop.getId() == null) {
                        // Save the laptop object if it's not already saved
                        laptopRepo.save(laptop);
                    }
                }
            }
            
            existingStudent.setLaptops(updatedLaptops);

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

    @DeleteMapping("/student/laptop")
    public ResponseEntity<?> removeAllStudents() {
        studentRepo.deleteAll();
        laptopRepo.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
