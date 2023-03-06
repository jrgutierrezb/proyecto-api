package com.proyecto.retail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.retail.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>  {

}
