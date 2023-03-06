package com.proyecto.retail.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.retail.Infraestructura.ResponseHandler;
import com.proyecto.retail.model.Module;
import com.proyecto.retail.model.Profile;
import com.proyecto.retail.model.Profiletransaction;
import com.proyecto.retail.model.Transaction;
import com.proyecto.retail.service.ProfileServiceImp;
import com.proyecto.retail.service.ProfileTransactionServiceImp;
import com.proyecto.retail.service.TransactionServiceImp;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ProfilePermissionController {
	@Autowired
	private TransactionServiceImp transactionServiceImp;
	
	@Autowired
	private ProfileServiceImp profileServiceImp;
	
	@Autowired
	private ProfileTransactionServiceImp profileTransactionServiceImp;
	
	// Read operation
	@RequestMapping(value = "/profilePermissions/all/{profileId}/{moduleId}", method = RequestMethod.GET)
	public ResponseEntity<Object> fetchProfilePermissionList(@PathVariable("profileId") Integer profileId,
			@PathVariable("moduleId") Integer moduleId)
	{
		try {
        	
        	List<Profiletransaction> transactions = null;
        	transactions = this.transactionServiceImp.findByProfileidAndIdmodule(profileId, moduleId);;
        	if(transactions == null || transactions.isEmpty()) {
        		return ResponseHandler.generateResponse(true, 1, "No Data Found ", HttpStatus.OK, null);
        	}
        	
        	List<Map<String, Object>> data = new ArrayList<>();
        	transactions.stream().forEach((item) -> {
        		Map<String, Object> row = new HashMap<String, Object>();
        		row.put("id", item.getId());
        		row.put("profileid", profileId);
        		row.put("profilename", item.getProfile().getName());
        		row.put("moduleId", moduleId);
        		row.put("modulename", item.getTransaction().getModule().getName());
        		row.put("transactionid", item.getTransaction().getId());
        		row.put("transactionname", item.getTransaction().getName());
        		data.add(row);
        	});


        	return ResponseHandler.generateResponse(false, 0, "OK", HttpStatus.OK, data);
        	
    	} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
	}
	
	// Save operation
    @PostMapping(value = "/profilePermissions")
    public ResponseEntity<Object> saveProfilePermissions(@RequestBody Profiletransaction profiletransaction)
    {
    	try {
    		
    		List<Profiletransaction> exists = this.profileTransactionServiceImp.findByProfileidAndTransactionid(profiletransaction.getProfileid(), profiletransaction.getTransactionid());
    		if(exists != null & !exists.isEmpty() ) {
    			return ResponseHandler.generateResponse(true, 1, "profile and view already exists", HttpStatus.OK, null);
    		}
    		
    		profiletransaction = this.profileTransactionServiceImp.saveProfiletransaction(profiletransaction);
			
			return ResponseHandler.generateResponse(false, 0, "OK", HttpStatus.OK, profiletransaction);
			
		} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
 // Read operation By Id
    @RequestMapping(value = "/profilePermissions/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> findById(@PathVariable("id") Integer profiletransactionId)
    {
    	try {
    		Profiletransaction profiletransaction = null;
    		profiletransaction = this.profileTransactionServiceImp.findById(profiletransactionId);
        	if(profiletransaction == null) {
        		return ResponseHandler.generateResponse(true, 1, "data does not exists ", HttpStatus.OK, null);
        	}
        	
        	return ResponseHandler.generateResponse(false, 0, "OK", HttpStatus.OK, profiletransaction);
    	} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
 // Update operation
    @RequestMapping(value = "/profilePermissions/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateUser(@RequestBody Profiletransaction profiletransaction, @PathVariable("id") Integer id)
    {
    	try {
    		
    		List<Profiletransaction> exists = this.profileTransactionServiceImp.findByProfileidAndTransactionid(profiletransaction.getProfileid(), profiletransaction.getTransactionid());
    		if(exists != null & !exists.isEmpty() & !exists.get(0).getId().equals(id)) {
    			return ResponseHandler.generateResponse(true, 1, "profile and view already exists", HttpStatus.OK, null);
    		}
    		
    		profiletransaction = this.profileTransactionServiceImp.updateProfiletransaction(profiletransaction, id);
    		
    		if(profiletransaction == null) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}
    		
    		return ResponseHandler.generateResponse(false, 0, "Successfully updated data!", HttpStatus.OK, profiletransaction);
    	}
    	catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
 // Delete operation
    @DeleteMapping(value = "/profilePermissions/{id}")
    public ResponseEntity<Object> deletedById(@PathVariable("id")Integer profilePermissionId)
    {
    	try {
    		Boolean deleted = profileTransactionServiceImp.deletedById(profilePermissionId);
    		
    		if(!deleted) {
    			return ResponseHandler.generateResponse(true, 1, "Error deleted data!", HttpStatus.OK, null);
    		}
    		
    		return ResponseHandler.generateResponse(false, 0, "OK", HttpStatus.OK, null);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
}
