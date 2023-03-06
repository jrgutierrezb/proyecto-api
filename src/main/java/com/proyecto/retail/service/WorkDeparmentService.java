package com.proyecto.retail.service;

import java.util.List;

import com.proyecto.retail.model.Workdepartment;

public interface WorkDeparmentService {

	List<Workdepartment> fetchWorkDepartmentList();
	Workdepartment findById(Integer workDepartmentId);
}
