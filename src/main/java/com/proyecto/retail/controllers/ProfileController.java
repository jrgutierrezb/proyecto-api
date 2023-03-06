package com.proyecto.retail.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.retail.Infraestructura.ResponseHandler;
import com.proyecto.retail.model.Profile;
import com.proyecto.retail.service.ProfileServiceImp;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ProfileController {
	
	 @Autowired
	 private ProfileServiceImp profileServiceImp;
	 
	 // Save operation
	 @RequestMapping(value = "/profiles", method = RequestMethod.POST)
	 public ResponseEntity<Object> saveProfile(@RequestBody Profile profile)
	 {
	    List<Profile> exists = null;
	    try {
	    	exists = profileServiceImp.findByName(profile.getName().toUpperCase());
	    		
	    	if(!exists.isEmpty())
	    	{
	    		return ResponseHandler.generateResponse(true, 1, "name allready exists", HttpStatus.OK, null);
	    	}
	    	profile.setName(profile.getName().toUpperCase());
	    	profile = profileServiceImp.saveProfile(profile);
	    		
	    	return ResponseHandler.generateResponse(false, 0, "Successfully added data!", HttpStatus.OK, profile);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
	 }
	 
	 // Read operation
	 @RequestMapping(value = "/profiles", method = RequestMethod.GET)
	 public ResponseEntity<Object> fetchProfileList()
	 {
		 try {
	        	
	        List<Profile> profiles = null;
	        profiles = this.profileServiceImp.fetchProfileList();
	        if(profiles == null || profiles.isEmpty()) {
	        	return ResponseHandler.generateResponse(true, 1, "No Data Found ", HttpStatus.OK, null);
	        }
	        	
	        return ResponseHandler.generateResponse(false, 0, "OK", HttpStatus.OK, profiles);
	        	
	    } catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
	 }
}
