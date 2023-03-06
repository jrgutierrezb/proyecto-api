package com.proyecto.retail.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.retail.Dto.IconDto;
import com.proyecto.retail.Dto.LoginDto;
import com.proyecto.retail.Dto.MenuDto;
import com.proyecto.retail.Infraestructura.ResponseHandler;
import com.proyecto.retail.model.Transaction;
import com.proyecto.retail.model.User;
import com.proyecto.retail.model.Module;
import com.proyecto.retail.security.JwtUtils;
import com.proyecto.retail.service.TransactionServiceImp;
import com.proyecto.retail.service.ModuleServiceImp;
import com.proyecto.retail.service.UserDetailsServiceImpl;
import com.proyecto.retail.service.UserServiceImp;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SecurityController {
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
    private UserDetailsServiceImpl userDetailsService;
	
	// Annotation
    @Autowired 
    private UserServiceImp userServiceImp;
    
    @Autowired
    private JwtUtils jwtUtils;
    
    @Autowired
    private TransactionServiceImp transactionServiceImp;
    
    @Autowired
    private ModuleServiceImp moduleServiceImp;

	// Save operation
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Object> login(@RequestBody LoginDto login)
    {
    	List<User> existsUser = null;
    	User user = null;
    	try {
    		
    		existsUser = this.userServiceImp.findByUsernameAndIstemporalpassword(login.getUserName(), true);
    		
     		if(existsUser.isEmpty()) {
    			return ResponseHandler.generateResponse(true,  1, "Clave expirada.", HttpStatus.OK, null);
    		}
    		
    		existsUser = this.userServiceImp.findByUserName(login.getUserName());
    		if(existsUser.isEmpty()) {
    			return ResponseHandler.generateResponse(true,  1, "Usuario no Existe.", HttpStatus.OK, null);
    		}
    		
    		try{
                autenticar(login.getUserName(),login.getPassword());
            }catch (Exception exception){
                exception.printStackTrace();
                throw new Exception("Usuario no encontrado");
            }
    		
    		user = existsUser.get(0);
    		
    		UserDetails userDetails =  this.userDetailsService.loadUserByUsername(login.getUserName());
    		
    		String token = this.jwtUtils.generateToken(userDetails, String.valueOf(user.getId()));
    		
    		Map<String, Object> response = new HashMap<String, Object>();
    		response.put("user", user);
    		response.put("token", token);
    		
    		return ResponseHandler.generateResponse(false, 0, "Login exitoso", HttpStatus.OK, response);
    		
    	} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
    // Save operation
    @RequestMapping(value = "/menu/{idProfile}", method = RequestMethod.GET)
    public ResponseEntity<Object> getMenuByProfile(@RequestHeader HttpHeaders headers,
    		@PathVariable("idProfile") Integer idProfile)
    {
    	try {
    		String canal = headers.getFirst("canal");
    		List<Transaction> transactions = null;
    		List<MenuDto> menuDto = null;
    		if(canal.equals("WEB")) {
    			transactions = this.transactionServiceImp.fetchTransactionList(idProfile);
    		}
    		else {
    			List<Module> modules = moduleServiceImp.findByName("Administración");
    			List<Integer> modulesid = modules.stream().map(item -> {
    				Integer id = item.getId();
    				return id;
    			}).collect(Collectors.toList());
    			modules = moduleServiceImp.findByName("Reportes");
    			modules.stream().forEach((item) -> {
    				modulesid.add(item.getId());
    			});
    			transactions = this.transactionServiceImp.transactionWithModuleidList(idProfile, modulesid);
    		}
    		
    		
        	if(transactions.isEmpty()) {
        		return ResponseHandler.generateResponse(true, 1, "transactions does not exists ", HttpStatus.OK, null);
        	}
        	
        	menuDto = new ArrayList<MenuDto>();
        	for(Transaction transaccion: transactions) {
        		if(!menuDto.stream().anyMatch(x-> x.getId().equals(transaccion.getIdmodule()))) {
        			Module module = this.moduleServiceImp.findById(transaccion.getIdmodule());
        			IconDto icon = new IconDto();
        			MenuDto menu = new MenuDto();
        			menu.setId(module.getId());
        			menu.setName(module.getName());
        			menu.setUrl(module.getUrl());
        			icon.setName(module.getIcon());
        			menu.setIconComponent(icon);
        			menuDto.add(menu);
        		}
        	}
        	
        	for(MenuDto module: menuDto) {
        		List<Transaction> filterTransaccion = transactions.stream().filter(x -> x.getIdmodule().equals(module.getId())).collect(Collectors.toList());
        		List<MenuDto> childrens = new ArrayList<MenuDto>();
        		
        		for(Transaction children: filterTransaccion) {
        			MenuDto menu = new MenuDto();
            		menu.setId(children.getId());
        			menu.setName(children.getName());
        			menu.setUrl(module.getUrl() + children.getUrl());
        			List<String> permissions = new ArrayList<String>();
        			menu.setPermissions(permissions);
        			childrens.add(menu);
        		}
        		module.setChildren(childrens);
        		
        	}
        	
        	return ResponseHandler.generateResponse(false, 0, "OK", HttpStatus.OK, menuDto);
    	} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
    private void autenticar(String username,String password) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (DisabledException exception){
            throw  new Exception("USUARIO DESHABILITADO " + exception.getMessage());
        }catch (BadCredentialsException e){
            throw  new Exception("Credenciales invalidas " + e.getMessage());
        }
    }
}
