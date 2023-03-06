package com.proyecto.retail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.retail.model.Catalog;

public interface CatalogRepository extends JpaRepository<Catalog, Integer> {
	List<Catalog> findByStateTrue();
	List<Catalog> findByCodeAndStateTrue(String code);

}
