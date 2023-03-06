package com.proyecto.retail.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.retail.Infraestructura.ResponseHandler;
import com.proyecto.retail.model.Module;
import com.proyecto.retail.service.ModuleServiceImp;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ModuleController {
	
	@Autowired
	private ModuleServiceImp moduleServiceImp;
	
	@PostMapping(value = "/modules")
    public ResponseEntity<Object> saveUser(@RequestBody Module module)
    {
		try	{
			List<Module> existsModule = this.moduleServiceImp.findByName(module.getName());
			if(!existsModule.isEmpty()) {
				return ResponseHandler.generateResponse(true, 1, "nombre allready exists", HttpStatus.OK, null);
			}
			
			existsModule = this.moduleServiceImp.findByUrl(module.getUrl());
			if(!existsModule.isEmpty()) {
				return ResponseHandler.generateResponse(true, 1, "Ruta allready exists", HttpStatus.OK, null);
			}
			
			module = this.moduleServiceImp.SaveModule(module);
			
			return ResponseHandler.generateResponse(false, 0, "Successfully added data!", HttpStatus.OK, module);
			
		} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
	
	// Read operation
    @RequestMapping(value = "/modules", method = RequestMethod.GET)
    public ResponseEntity<Object> fetchUserList()
    {
        try {
        	
        	List<Module> modules = null;
        	modules = this.moduleServiceImp.fetchModuleList();
        	if(modules == null || modules.isEmpty()) {
        		return ResponseHandler.generateResponse(true, 1, "No Data Found ", HttpStatus.OK, null);
        	}
        	
        	return ResponseHandler.generateResponse(false, 0, "OK", HttpStatus.OK, modules);
        	
    	} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
    // Read operation By Id
    @RequestMapping(value = "/modules/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> findById(@PathVariable("id") Integer moduleId)
    {
    	try {
    		Module module = null;
        	module = this.moduleServiceImp.findById(moduleId);
        	if(module == null) {
        		return ResponseHandler.generateResponse(true, 1, "user does not exists ", HttpStatus.OK, null);
        	}
        	
        	return ResponseHandler.generateResponse(false, 0, "OK", HttpStatus.OK, module);
    	} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
 // Update operation
    @RequestMapping(value = "/modules/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateUser(@RequestBody Module module, @PathVariable("id") Integer moduleId)
    {
    	try {
    		
    		module = this.moduleServiceImp.UpdateModule(module, moduleId);
    		
    		if(module == null) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}
    		
    		return ResponseHandler.generateResponse(false, 0, "Successfully updated data!", HttpStatus.OK, module);
    	}
    	catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
 // Delete operation
    @RequestMapping(value = "/modules/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUserById(@PathVariable("id")
                                       Integer moduleId)
    {
    	try {
    		Boolean deleted = this.moduleServiceImp.DeleteModuleById(moduleId);
    		
    		if(deleted) {
    			return ResponseHandler.generateResponse(true, 1, "Error deleted data!", HttpStatus.OK, null);
    		}
    		
    		return ResponseHandler.generateResponse(false, 0, "Successfully deleted data!", HttpStatus.OK, null);
    	}
    	catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
        
    }

}
