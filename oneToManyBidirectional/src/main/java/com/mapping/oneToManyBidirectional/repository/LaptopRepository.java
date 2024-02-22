package com.mapping.oneToManyBidirectional.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapping.oneToManyBidirectional.entity.Laptop;

public interface LaptopRepository extends JpaRepository<Laptop, Integer>{

	Laptop findLaptopById(int laptopId);

	

}
