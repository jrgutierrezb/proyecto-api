package com.proyecto.retail.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.retail.model.Companytobebilling;
import com.proyecto.retail.model.Workdepartment;
import com.proyecto.retail.repository.WorkDepartmentRepository;

@Service
public class WorkDeparmentServiceImp implements WorkDeparmentService {
	
	@Autowired
	private WorkDepartmentRepository workDepartmentRepository;

	@Override
	public List<Workdepartment> fetchWorkDepartmentList() {
		// TODO Auto-generated method stub
		return workDepartmentRepository.findByStateTrue();
	}
	
	@Override
	public Workdepartment findById(Integer workDepartmentId) {
		// TODO Auto-generated method stub
		Optional<Workdepartment> workDepartment = null;
		workDepartment = this.workDepartmentRepository.findById(workDepartmentId);
		if(workDepartment.isPresent())
			return workDepartment.get();
		else
			return null;
	}

}
