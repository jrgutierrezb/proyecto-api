package com.proyecto.retail.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.retail.model.Agency;
import com.proyecto.retail.model.Companytobebilling;
import com.proyecto.retail.repository.CompanyToBeBillingRepository;

@Service
public class CompanyToBeBillingServiceImp implements CompanyToBeBillingService {
	
	@Autowired
	private CompanyToBeBillingRepository companyToBeBillingRepository;

	@Override
	public List<Companytobebilling> fetchCompanyToBeBillingList() {
		// TODO Auto-generated method stub
		return companyToBeBillingRepository.findByStateTrue();
	}
	
	@Override
	public Companytobebilling findById(Integer agencyId) {
		// TODO Auto-generated method stub
		Optional<Companytobebilling> company = null;
		company = this.companyToBeBillingRepository.findById(agencyId);
		if(company.isPresent())
			return company.get();
		else
			return null;
	}

}
