package com.proyecto.retail.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.retail.Infraestructura.ResponseHandler;
import com.proyecto.retail.model.Catalog;
import com.proyecto.retail.model.Inventory;
import com.proyecto.retail.service.CatalogServiceImp;
import com.proyecto.retail.service.InventoryServiceImp;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CatalogController {
	
	@Autowired
	private CatalogServiceImp catalogServiceImp;
	
	@Autowired
	private InventoryServiceImp inventoryServiceImp;
	
	@RequestMapping(value = "/catalog", method = RequestMethod.GET)
    public ResponseEntity<Object> fetchAll()
    {
    	try {
    		List<Catalog> catalogs = catalogServiceImp.fetchAll();
    		if(catalogs == null || catalogs.isEmpty()) {
    			return ResponseHandler.generateResponse(true, 1, "No Data Found ", HttpStatus.OK, null);
    		}
    		
    		return ResponseHandler.generateResponse(false, 0, "OK", HttpStatus.OK, catalogs);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
	
	// Read operation By Id
    @RequestMapping(value = "/catalog/{code}", method = RequestMethod.GET)
    public ResponseEntity<Object> findByCode(@PathVariable("code") String code)
    {
    	try {
    		List<Catalog> catalogs = catalogServiceImp.findByCodeAndStateTrue(code);
    		if(catalogs == null || catalogs.isEmpty()) {
    			return ResponseHandler.generateResponse(true, 1, "No Data Found ", HttpStatus.OK, null);
    		}
    		
    		return ResponseHandler.generateResponse(false, 0, "OK", HttpStatus.OK, catalogs);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
 // Read operation By Id
    @RequestMapping(value = "/catalog/inventory", method = RequestMethod.GET)
    public ResponseEntity<Object> findByCode(@RequestParam( name="companyid" ,required=false) Integer companyid, 
    		@RequestParam( name="agencyid" ,required=false) Integer agencyid,
    		@RequestParam( name="departmentid" ,required=false) Integer departmentid)
    {
    	try {
    		List<HashMap<String, Object>> result = this.catalogServiceImp.findByBillingCompanyAndAgencyAndDepartment(companyid, agencyid, departmentid);
        	return ResponseHandler.generateResponse(false, 0, "OK", HttpStatus.OK, result);
    	} catch (Exception e) {
			// TODO: handle exception
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
    // Read operation By Id
    @RequestMapping(value = "/inventory/{code}", method = RequestMethod.GET)
    public ResponseEntity<Object> findByInventaryCode(@PathVariable("code") String code)
    {
    	try {
    		List<Inventory> result = this.inventoryServiceImp.findByCatalogCode(code);
        	return ResponseHandler.generateResponse(false, 0, "OK", HttpStatus.OK, result);
    	} catch (Exception e) {
			// TODO: handle exception
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }

}
