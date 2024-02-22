package com.mapping.manyToManyUnidirectional.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapping.manyToManyUnidirectional.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	Student findStudentById(int studentId);

	

}
