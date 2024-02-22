package com.mapping.manyToManyBidirectional.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapping.manyToManyBidirectional.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	Student findStudentById(int studentId);

	

}
