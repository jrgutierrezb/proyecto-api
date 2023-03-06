package com.proyecto.retail.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.retail.Infraestructura.ResponseHandler;
import com.proyecto.retail.model.Agency;
import com.proyecto.retail.model.Companytobebilling;
import com.proyecto.retail.service.CompanyToBeBillingServiceImp;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CompanyToBeBillingController {
	
	@Autowired
	private CompanyToBeBillingServiceImp companyToBeBillingServiceImp;
	
	// Read operation
    @RequestMapping(value = "/companytobebilling", method = RequestMethod.GET)
    public ResponseEntity<Object> fetchCompanyToBeBillingList()
    {
    	try {
    		List<Companytobebilling> companytobebilling = null;
    		companytobebilling = companyToBeBillingServiceImp.fetchCompanyToBeBillingList();
    		
    		if(companytobebilling == null || companytobebilling.isEmpty()) {
        		return ResponseHandler.generateResponse(true, 1, "No Data Found ", HttpStatus.OK, null);
        	}
    		
    		return ResponseHandler.generateResponse(false, 0, "OK", HttpStatus.OK, companytobebilling);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }

}
