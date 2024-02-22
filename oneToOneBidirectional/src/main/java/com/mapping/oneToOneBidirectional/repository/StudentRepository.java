package com.mapping.oneToOneBidirectional.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapping.oneToOneBidirectional.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	Student findStudentById(int studentId);

	

}
