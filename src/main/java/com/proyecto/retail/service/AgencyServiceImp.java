package com.proyecto.retail.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.retail.model.Agency;
import com.proyecto.retail.model.Profile;
import com.proyecto.retail.repository.AgencyRepository;

@Service
public class AgencyServiceImp implements AgencyService {
	
	@Autowired
	private AgencyRepository agencyRepository;

	@Override
	public List<Agency> fetchAgencyList() {
		// TODO Auto-generated method stub
		return agencyRepository.findByStateTrue();
	}
	
	@Override
	public Agency findById(Integer agencyId) {
		// TODO Auto-generated method stub
		Optional<Agency> agency = null;
		agency = this.agencyRepository.findById(agencyId);
		if(agency.isPresent())
			return agency.get();
		else
			return null;
	}

}
