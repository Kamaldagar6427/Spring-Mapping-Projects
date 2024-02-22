package com.mapping.manyToOneUnidirectional.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapping.manyToOneUnidirectional.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	Student findStudentById(int studentId);

	

}
