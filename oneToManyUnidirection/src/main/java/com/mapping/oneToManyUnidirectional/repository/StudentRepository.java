package com.mapping.oneToManyUnidirectional.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapping.oneToManyUnidirectional.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	Student findStudentById(int studentId);

	

}
