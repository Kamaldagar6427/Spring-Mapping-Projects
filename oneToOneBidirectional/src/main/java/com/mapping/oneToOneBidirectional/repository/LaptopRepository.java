package com.mapping.oneToOneBidirectional.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapping.oneToOneBidirectional.entity.Laptop;

public interface LaptopRepository extends JpaRepository<Laptop, Integer>{

	Laptop findLaptopById(int laptopId);

	

}
