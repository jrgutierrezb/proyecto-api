package com.proyecto.retail.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.retail.Infraestructura.ResponseHandler;
import com.proyecto.retail.service.ReportServiceImp;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ReportController {
	
	@Autowired
	private ReportServiceImp reportServiceImp; 

	
	// Read operation By Id
    @RequestMapping(value = "/report/states", method = RequestMethod.POST)
    public ResponseEntity<Object> reportStates(@RequestBody Map<String, Object> parameters)
    {
    	try {
    		List<HashMap<String, Object>> result = this.reportServiceImp.getReportByStates(parameters);
        	return ResponseHandler.generateResponse(false, 0, "OK", HttpStatus.OK, result);
    	} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
    @RequestMapping(value = "/report/years/{year}", method = RequestMethod.GET)
    public ResponseEntity<Object> reportByYears(@PathVariable("year") Integer year)
    {
    	try {
    		List<HashMap<String, Object>> result = this.reportServiceImp.getReportByYears(year);
        	return ResponseHandler.generateResponse(false, 0, "OK", HttpStatus.OK, result);
    	} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
 // Read operation By Id
    @RequestMapping(value = "/report/general", method = RequestMethod.POST)
    public ResponseEntity<Object> reportGeneral(@RequestBody Map<String, Object> parameters)
    {
    	try {
    		List<HashMap<String, Object>> result = this.reportServiceImp.getGeneralReport(parameters);
        	return ResponseHandler.generateResponse(false, 0, "OK", HttpStatus.OK, result);
    	} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
}
