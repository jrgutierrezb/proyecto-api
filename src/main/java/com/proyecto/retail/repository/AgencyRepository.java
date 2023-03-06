package com.proyecto.retail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.retail.model.Agency;

public interface AgencyRepository extends JpaRepository<Agency, Integer> {
	List<Agency> findByStateTrue();
}
