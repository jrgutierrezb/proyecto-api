package com.proyecto.retail.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.retail.Infraestructura.ResponseHandler;
import com.proyecto.retail.model.Processstate;
import com.proyecto.retail.model.Workdepartment;
import com.proyecto.retail.service.ProcessStateServiceImp;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ProcessStateController {
	
	@Autowired
	private ProcessStateServiceImp processStateServiceImp;
	
	// Read operation
    @RequestMapping(value = "/processStates/{code}", method = RequestMethod.GET)
    public ResponseEntity<Object> getProcessStateByCode(@PathVariable("code") String code)
    {
    	try {
    		List<Processstate> processStates = null;
    		processStates = processStateServiceImp.findByCode(code);
    		
    		if(processStates == null || processStates.isEmpty()) {
        		return ResponseHandler.generateResponse(true, 1, "No Data Found ", HttpStatus.OK, null);
        	}
    		
    		return ResponseHandler.generateResponse(false, 0, "OK", HttpStatus.OK, processStates);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
 // Read operation
    @RequestMapping(value = "/processStates/all", method = RequestMethod.GET)
    public ResponseEntity<Object> getProcessStates()
    {
    	try {
    		List<Processstate> processStates = null;
    		processStates = processStateServiceImp.fechAll();
    		
    		if(processStates == null || processStates.isEmpty()) {
        		return ResponseHandler.generateResponse(true, 1, "No Data Found ", HttpStatus.OK, null);
        	}
    		
    		return ResponseHandler.generateResponse(false, 0, "OK", HttpStatus.OK, processStates);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }

}
