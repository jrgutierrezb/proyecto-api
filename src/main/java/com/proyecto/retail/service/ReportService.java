package com.proyecto.retail.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ReportService {
	
	List<HashMap<String, Object>> getReportByStates(Map<String, Object> parameters);
	List<HashMap<String, Object>> getReportByYears(Integer year);
}
