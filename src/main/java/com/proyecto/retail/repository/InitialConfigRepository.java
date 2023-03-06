package com.proyecto.retail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.retail.model.Initialconfig;

public interface InitialConfigRepository extends JpaRepository<Initialconfig, Integer> {
	
	public Initialconfig findByStateTrueAndName(String name);
}
