package com.mapping.manyToManyUnidirectional.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapping.manyToManyUnidirectional.entity.Laptop;

public interface LaptopRepository extends JpaRepository<Laptop, Integer>{

	Laptop findLaptopById(int laptopId);

	

}
