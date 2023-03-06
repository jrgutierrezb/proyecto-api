package com.proyecto.retail.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.retail.Infraestructura.ResponseHandler;
import com.proyecto.retail.model.Mailsetting;
import com.proyecto.retail.service.MailsettingServiceImp;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class MailSettingController {
	
	@Autowired
	private MailsettingServiceImp mailsettingServiceImp;
	
	
	// Save operation
    @PostMapping(value = "/mailsettings")
    public ResponseEntity<Object> saveMailSetting(@RequestBody Mailsetting mailsetting)
    {
    	try {
    		
			mailsetting = this.mailsettingServiceImp.saveMailSetting(mailsetting);
			
			return ResponseHandler.generateResponse(false, 0, "Successfully added data!", HttpStatus.OK, mailsetting);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
    // 	Read operation By MailSetting
    @GetMapping(value = "/mailsettings")
    public ResponseEntity<Object> findByMailSetting()
    {
    	try {
    		Mailsetting mailsetting = null;
    		mailsetting = this.mailsettingServiceImp.findByMailSetting();
        	if(mailsetting == null) {
        		return ResponseHandler.generateResponse(true, 1, "mail settings does not exists ", HttpStatus.OK, null);
        	}
        	
        	return ResponseHandler.generateResponse(false, 0, "OK", HttpStatus.OK, mailsetting);
    	} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
 // Update operation
    @PutMapping(value = "/mailsettings/{id}")
    public ResponseEntity<Object> updateUser(@RequestBody Mailsetting mailsetting, @PathVariable("id") Integer MailsettingId)
    {
    	
    	try {
    		
    		mailsetting = this.mailsettingServiceImp.updateMailSetting(mailsetting, MailsettingId);
    		if(mailsetting == null) {
    			return ResponseHandler.generateResponse(true, 1, "error al actualizar configuraciones de mail", HttpStatus.OK, null);
    		}
    		return ResponseHandler.generateResponse(false, 0, "Successfully updated data!", HttpStatus.OK, mailsetting);
    	}
    	catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }

}
