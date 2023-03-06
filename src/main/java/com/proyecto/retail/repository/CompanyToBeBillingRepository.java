package com.proyecto.retail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.retail.model.Companytobebilling;

public interface CompanyToBeBillingRepository extends JpaRepository<Companytobebilling, Integer> {
	List<Companytobebilling> findByStateTrue();
}
