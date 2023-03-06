package com.proyecto.retail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.retail.model.Messagebox;

public interface MessageBoxRepository extends JpaRepository<Messagebox, Integer> {
	List<Messagebox> findBySend(Boolean send);
}
