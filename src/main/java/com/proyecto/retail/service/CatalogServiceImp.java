package com.proyecto.retail.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.retail.Infraestructura.ExecuteProcedure;
import com.proyecto.retail.Infraestructura.SqlQueries;
import com.proyecto.retail.model.Catalog;
import com.proyecto.retail.repository.CatalogRepository;

@Service
public class CatalogServiceImp implements CatalogService {
	
	@Autowired
	private CatalogRepository catalogRepository;
	
	@Autowired
	private ExecuteProcedure executeProcedure;
	
	@Override
	public List<Catalog> fetchAll() {
		return catalogRepository.findByStateTrue();
	}

	@Override
	public List<Catalog> findByCodeAndStateTrue(String code) {
		// TODO Auto-generated method stub
		return catalogRepository.findByCodeAndStateTrue(code);
	}

	@Override
	public List<HashMap<String, Object>> findByBillingCompanyAndAgencyAndDepartment(Integer companyid, Integer agencyid,
			Integer departmentid) {
		// TODO Auto-generated method stub
		SqlQueries sql = new SqlQueries();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("p_departmentid",departmentid);
		parameters.put("p_companyid",companyid);
		parameters.put("p_agencyid",agencyid);
		
		return executeProcedure.executeProcedure(sql.consultCatalogInventoyProduct, parameters);
	}

}
