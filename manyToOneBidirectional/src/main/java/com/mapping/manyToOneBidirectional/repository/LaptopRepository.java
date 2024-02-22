package com.mapping.manyToOneBidirectional.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapping.manyToOneBidirectional.entity.Laptop;

public interface LaptopRepository extends JpaRepository<Laptop, Integer>{

	Laptop findLaptopById(int laptopId);

	

}
