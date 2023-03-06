package com.proyecto.retail.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.retail.Infraestructura.ExecuteProcedure;
import com.proyecto.retail.Infraestructura.SqlQueries;

@Service
public class ReportServiceImp implements ReportService {
	
	@Autowired
	private ExecuteProcedure executeProcedure;

	@Override
	public List<HashMap<String, Object>> getReportByStates(Map<String, Object> parameters) {
		SqlQueries sql = new SqlQueries();
		return executeProcedure.executeProcedure(sql.reportPorEstados, parameters);
	}

	@Override
	public List<HashMap<String, Object>> getReportByYears(Integer year) {
		// TODO Auto-generated method stub
		SqlQueries sql = new SqlQueries();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("p_year", year);
		return executeProcedure.executeProcedure(sql.reportPorAnios, parameters);
	}
}
