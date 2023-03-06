package com.proyecto.retail.service;

import java.util.List;

import com.proyecto.retail.model.Agency;

public interface AgencyService {
	List<Agency> fetchAgencyList();
	Agency findById(Integer agencyId);
}
