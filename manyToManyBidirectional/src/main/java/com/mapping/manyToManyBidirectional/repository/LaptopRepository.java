package com.mapping.manyToManyBidirectional.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapping.manyToManyBidirectional.entity.Laptop;

public interface LaptopRepository extends JpaRepository<Laptop, Integer>{

	Laptop findLaptopById(int laptopId);

	

}
