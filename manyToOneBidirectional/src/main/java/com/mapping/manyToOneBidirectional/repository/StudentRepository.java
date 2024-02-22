package com.mapping.manyToOneBidirectional.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapping.manyToOneBidirectional.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	Student findStudentById(int studentId);

	

}
