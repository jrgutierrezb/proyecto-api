package com.proyecto.retail.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.retail.Infraestructura.ResponseHandler;
import com.proyecto.retail.model.Agency;
import com.proyecto.retail.model.Companytobebilling;
import com.proyecto.retail.model.Messagebox;
import com.proyecto.retail.model.Messageparameter;
import com.proyecto.retail.model.Messagetemplate;
import com.proyecto.retail.model.Profile;
import com.proyecto.retail.model.User;
import com.proyecto.retail.model.Workdepartment;
import com.proyecto.retail.repository.WorkDepartmentRepository;
import com.proyecto.retail.service.AgencyServiceImp;
import com.proyecto.retail.service.CompanyToBeBillingServiceImp;
import com.proyecto.retail.service.MessageParameterServiceImp;
import com.proyecto.retail.service.MessageTemplateServiceImp;
import com.proyecto.retail.service.MessageboxServiceImp;
import com.proyecto.retail.service.ProfileServiceImp;
import com.proyecto.retail.service.UserServiceImp;
import com.proyecto.retail.service.WorkDeparmentServiceImp;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {

	// Annotation
    @Autowired 
    private UserServiceImp userServiceImp;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private ProfileServiceImp profileServiceImp;
    
    @Autowired
    private AgencyServiceImp agencyServiceImp;
    
    @Autowired
    private CompanyToBeBillingServiceImp companyToBeBillingServiceImp;
    
    @Autowired
    private WorkDeparmentServiceImp workDeparmentServiceImp;
    
    @Autowired
    private MessageTemplateServiceImp messageTemplateServiceImp;
    
    @Autowired
    private MessageboxServiceImp messageboxServiceImp;
    
    @Autowired
    private MessageParameterServiceImp messageParameterServiceImp;
 
    // Save operation
    @PostMapping(value = "/users")
    public ResponseEntity<Object> saveUser(@RequestBody User user)
    {
    	List<User> existsUser = null;
    	Profile profile = null;
    	try {
    		existsUser = this.userServiceImp.findByIdentification(user.getIdentification());
    		/*if(!existsUser.isEmpty()) 
    		{
    			return ResponseHandler.generateResponse(true, 1, "identification allready exists", HttpStatus.OK, null);
    		}*/
    		
    		existsUser = this.userServiceImp.findByUserName(user.getUsername());
    		if(!existsUser.isEmpty()) 
    		{
    			return ResponseHandler.generateResponse(true, 1, "user name allready exists", HttpStatus.OK, null);
    		}
    		
    		/*existsUser = this.userServiceImp.findByMail(user.getMail());
    		if(!existsUser.isEmpty()) 
    		{
    			return ResponseHandler.generateResponse(true, 1, "mail allready exists", HttpStatus.OK, null);
    		}*/
    		
    		profile = this.profileServiceImp.findById(user.getProfileid());
    		if(profile == null) 
    		{
    			return ResponseHandler.generateResponse(true, 1, "profile does not exists", HttpStatus.OK, null);
    		}
    		
    		Agency agency = this.agencyServiceImp.findById(user.getAgencyid());
    		if(agency == null) 
    		{
    			return ResponseHandler.generateResponse(true, 1, "agency does not exists", HttpStatus.OK, null);
    		}
    		
    		Companytobebilling company = this.companyToBeBillingServiceImp.findById(user.getCompanyid());
    		if(company == null) 
    		{
    			return ResponseHandler.generateResponse(true, 1, "company does not exists", HttpStatus.OK, null);
    		}
    		
    		Workdepartment workDepartment = this.workDeparmentServiceImp.findById(user.getWorkdepartmentid());
    		if(workDepartment == null) 
    		{
    			return ResponseHandler.generateResponse(true, 1, "department does not exists", HttpStatus.OK, null);
    		}
    		
    		user.setProfile(profile);
    		user.setAgency(agency);
    		user.setCompanytobebilling(company);
    		user.setWorkdepartment(workDepartment);
    		String passwordTmp = this.userServiceImp.generateRandomPassword(8);
    		user.setPassword(passwordTmp);
    		
    		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    		user = this.userServiceImp.saveUser(user);
    		
    		Messagetemplate messagetemplate = this.messageTemplateServiceImp.findByName("REGISTRA_USUARIO");
    		if(messagetemplate != null) {
    			Messagebox messagebox = new Messagebox();
    			messagebox.setSubject("NUEVO USUARIO");
    			messagebox.setTorecipients(user.getMail());
    			messagebox.setMessagetemplate(messagetemplate);
    			messagebox = this.messageboxServiceImp.saveMessagebox(messagebox);
    			Messageparameter messageparameter; 
    			int parameterNumber = messagetemplate.getParameternumber();
    			for( int i = 1; i <= parameterNumber; i++) {
    				messageparameter = new Messageparameter();
    				switch(i) {
    				case 1:
    					messageparameter.setValue(user.getFirstname() + " " + user.getLastname());
    					break;
    				case 2:
    					messageparameter.setValue(user.getUsername());
    					break;
    				case 3:
    					messageparameter.setValue(passwordTmp);
    					break;
    				}
    				
    				messageparameter.setMessagebox(messagebox);
    				this.messageParameterServiceImp.saveMessageParameter(messageparameter);
    			}
    		}
    		
    		return ResponseHandler.generateResponse(false, 0, "Successfully added data!", HttpStatus.OK, user);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
    // Read operation
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<Object> fetchUserList()
    {
        try {
        	
        	List<User> users = null;
        	users = this.userServiceImp.fetchUserList();
        	if(users == null || users.isEmpty()) {
        		return ResponseHandler.generateResponse(true, 1, "No Data Found ", HttpStatus.OK, null);
        	}
        	
        	return ResponseHandler.generateResponse(false, 0, "OK", HttpStatus.OK, users);
        	
    	} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
 // Read operation By Id
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> findById(@PathVariable("id") Integer userId)
    {
    	try {
    		User user = null;
        	user = this.userServiceImp.findById(userId);
        	if(user == null) {
        		return ResponseHandler.generateResponse(true, 1, "user does not exists ", HttpStatus.OK, null);
        	}
        	
        	return ResponseHandler.generateResponse(false, 0, "OK", HttpStatus.OK, user);
    	} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
 
    // Update operation
    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable("id") Integer userId)
    {
    	Profile profile = null;
    	try {
    		profile = this.profileServiceImp.findById(user.getProfileid());
    		if(profile == null) 
    		{
    			return ResponseHandler.generateResponse(true, 1, "profile does not exists", HttpStatus.OK, null);
    		}
    		
    		Agency agency = this.agencyServiceImp.findById(user.getAgencyid());
    		if(agency == null) 
    		{
    			return ResponseHandler.generateResponse(true, 1, "agency does not exists", HttpStatus.OK, null);
    		}
    		
    		Companytobebilling company = this.companyToBeBillingServiceImp.findById(user.getCompanyid());
    		if(company == null) 
    		{
    			return ResponseHandler.generateResponse(true, 1, "company does not exists", HttpStatus.OK, null);
    		}
    		
    		Workdepartment workDepartment = this.workDeparmentServiceImp.findById(user.getWorkdepartmentid());
    		if(workDepartment == null) 
    		{
    			return ResponseHandler.generateResponse(true, 1, "department does not exists", HttpStatus.OK, null);
    		}
    		
    		user.setProfile(profile);
    		user.setAgency(agency);
    		user.setCompanytobebilling(company);
    		user.setWorkdepartment(workDepartment);
    		
    		user = this.userServiceImp.updateUser(user, userId);
    		return ResponseHandler.generateResponse(false, 0, "Successfully updated data!", HttpStatus.OK, user);
    	}
    	catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
 
    // Delete operation
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUserById(@PathVariable("id")
                                       Integer userId)
    {
    	try {
    		this.userServiceImp.deleteUserById(userId);
    		return ResponseHandler.generateResponse(false, 0, "Successfully deleted data!", HttpStatus.OK, null);
    	}
    	catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
        
    }
}
