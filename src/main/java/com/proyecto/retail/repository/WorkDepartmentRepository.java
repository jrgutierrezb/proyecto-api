package com.proyecto.retail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.retail.model.Workdepartment;

public interface WorkDepartmentRepository extends JpaRepository<Workdepartment, Integer> {
	List<Workdepartment> findByStateTrue();
}
