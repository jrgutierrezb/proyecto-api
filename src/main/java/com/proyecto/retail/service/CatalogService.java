package com.proyecto.retail.service;

import java.util.HashMap;
import java.util.List;

import com.proyecto.retail.model.Catalog;

public interface CatalogService {
	
	List<Catalog> fetchAll();
	
	List<Catalog> findByCodeAndStateTrue(String code);
	
	List<HashMap<String, Object>> findByBillingCompanyAndAgencyAndDepartment(Integer companyid, Integer agencyid, Integer departmentid);
}
