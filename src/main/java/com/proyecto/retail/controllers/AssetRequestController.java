package com.proyecto.retail.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.retail.Dto.AssetrequestDto;
import com.proyecto.retail.Dto.ProformDto;
import com.proyecto.retail.Dto.RequestAssignedDto;
import com.proyecto.retail.Dto.WarehouseRequestDto;
import com.proyecto.retail.Infraestructura.ResponseHandler;
import com.proyecto.retail.model.Assetrequest;
import com.proyecto.retail.model.Processstate;
import com.proyecto.retail.model.Proform;
import com.proyecto.retail.model.User;
import com.proyecto.retail.service.AssetRequestServiceImp;
import com.proyecto.retail.service.ProcessStateServiceImp;
import com.proyecto.retail.service.UserServiceImp;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AssetRequestController {

	@Autowired
	private AssetRequestServiceImp assetRequestServiceImp;
	
	@Autowired 
    private UserServiceImp userServiceImp;
	
	@Autowired
	private ProcessStateServiceImp processStateServiceImp;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping(value = "/assetRequests")
    public ResponseEntity<Object> saveUser(@RequestBody AssetrequestDto assetRequestDto)
    {
		try	{
			
			Assetrequest assetRequest = modelMapper.map(assetRequestDto, Assetrequest.class);
			assetRequest = this.assetRequestServiceImp.SaveAssetRequest(assetRequest);
			
			return ResponseHandler.generateResponse(false, 0, "Successfully added data!", HttpStatus.OK, assetRequest);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
	
	// Read operation By Id
    @RequestMapping(value = "/assetRequests/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> findById(@PathVariable("id") Integer assetRequestId)
    {
    	try {
    		Assetrequest assetRequest = null;
    		assetRequest = this.assetRequestServiceImp.findById(assetRequestId);
        	if(assetRequest == null) {
        		return ResponseHandler.generateResponse(true, 1, "user does not exists ", HttpStatus.OK, null);
        	}
        	
        	AssetrequestDto assetrequestDto = modelMapper.map(assetRequest, AssetrequestDto.class);
        	
        	User userRequested = this.userServiceImp.findById(assetrequestDto.getUseridrequested());
        	
        	assetrequestDto.setUsernamerequested(userRequested.getFirstname() + " " + userRequested.getLastname());
        	
        	return ResponseHandler.generateResponse(false, 0, "OK", HttpStatus.OK, assetrequestDto);
    	} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
 // Read operation By Id
    @RequestMapping(value = "/assetRequests/filters", method = RequestMethod.POST)
    public ResponseEntity<Object> getById(@RequestBody Map<String, Object> parameters)
    {
    	try {
    		List<HashMap<String, Object>> result = this.assetRequestServiceImp.getById(parameters);
        	return ResponseHandler.generateResponse(false, 0, "OK", HttpStatus.OK, result);
    	} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
	
	// Update operation
    @RequestMapping(value = "/assetRequests/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateAssetRequest(@RequestBody AssetrequestDto assetRequestDto, 
    		@PathVariable("id") Integer assetRequestId)
    {
    	try {
    		Assetrequest assetRequest = modelMapper.map(assetRequestDto, Assetrequest.class);
    		Assetrequest assetRequestExists = this.assetRequestServiceImp.findById(assetRequestId);
    		if(assetRequestExists == null) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}
    		
    		if(!(assetRequestExists.getProcessstate().getCode().equals("INGRESADO") || assetRequestExists.getProcessstate().getCode().equals("PENDIENTE"))) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}
    		
			assetRequest = this.assetRequestServiceImp.UpdateAssetRequest(assetRequest, assetRequestId);
			if(assetRequest == null) {
				return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
			}
			
			return ResponseHandler.generateResponse(false, 0, "Successfully updated data!", HttpStatus.OK, assetRequest);
			
		} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
    // Update operation
    @RequestMapping(value = "/assetRequests/assigned/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> assignUser(@RequestBody RequestAssignedDto assetRequestDto, 
    		@PathVariable("id") Integer assetRequestId)
    {
    	try {
    		
    		Assetrequest assetRequestExists = this.assetRequestServiceImp.findById(assetRequestId);
    		if(assetRequestExists == null) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}
    		
    		if(!assetRequestExists.getProcessstate().getCode().equals("INGRESADO") ) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}
    		
    		Assetrequest assetRequest = this.assetRequestServiceImp.AssignAssetRequest(assetRequestDto, assetRequestId);
			if(assetRequest == null) {
				return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
			}
			
			return ResponseHandler.generateResponse(false, 0, "Successfully assigned request!", HttpStatus.OK, assetRequest);
			
		} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
    // Update operation
    @RequestMapping(value = "/assetRequests/approve/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> approveRequest(@RequestBody AssetrequestDto assetRequestDto, 
    		@PathVariable("id") Integer assetRequestId)
    {
    	try {
    		
    		Assetrequest assetRequestExists = this.assetRequestServiceImp.findById(assetRequestId);
    		if(assetRequestExists == null) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}
    		
    		if(!assetRequestExists.getProcessstate().getCode().equals("PENDIENTE")) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}
    		
    		Assetrequest assetRequest = modelMapper.map(assetRequestDto, Assetrequest.class);
			assetRequest = this.assetRequestServiceImp.ApproveAssetRequest(assetRequest, assetRequestId);
			
			if(assetRequest == null) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}
			
			return ResponseHandler.generateResponse(false, 0, "Successfully added data!", HttpStatus.OK, assetRequest);
			
		} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
 // Update operation
    @RequestMapping(value = "/assetRequests/denied/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> deniedRequest(@RequestBody Assetrequest assetRequest, 
    		@PathVariable("id") Integer assetRequestId)
    {
    	try {
    		
    		Assetrequest assetRequestExists = this.assetRequestServiceImp.findById(assetRequestId);
    		if(assetRequestExists == null) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}
    		
    		if(!assetRequestExists.getProcessstate().getCode().equals("PENDIENTE")) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}
			
			assetRequest = this.assetRequestServiceImp.DeniedAssetRequest(assetRequest, assetRequestId);
			
			if(assetRequest == null) {
				return ResponseHandler.generateResponse(true, 1, "Error denied request!", HttpStatus.OK, null);
			}
			
			return ResponseHandler.generateResponse(false, 0, "Successfully denied request!", HttpStatus.OK, assetRequest);
			
		} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
 // Update operation
    @RequestMapping(value = "/assetRequests/warehouse/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> wareHouseRequest(@RequestBody Assetrequest assetRequest, 
    		@PathVariable("id") Integer assetRequestId)
    {
    	try {
    		
    		Assetrequest assetRequestExists = this.assetRequestServiceImp.findById(assetRequestId);
    		if(assetRequestExists == null) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}
    		
    		if(!assetRequestExists.getProcessstate().getCode().equals("PENDIENTE")) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}
			
			assetRequest = this.assetRequestServiceImp.WareHouseAssetRequest(assetRequest, assetRequestId);
			
			if(assetRequest == null) {
				return ResponseHandler.generateResponse(true, 1, "Error denied request!", HttpStatus.OK, null);
			}
			
			return ResponseHandler.generateResponse(false, 0, "Successfully denied request!", HttpStatus.OK, assetRequest);
			
		} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
 // Update operation
    @RequestMapping(value = "/assetRequests/maintenance/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> MaintenanceUser(@RequestBody WarehouseRequestDto warehouseRequestDto, 
    		@PathVariable("id") Integer assetRequestId)
    {
    	try {
    		Assetrequest assetRequestExists = this.assetRequestServiceImp.findById(assetRequestId);
    		if(assetRequestExists == null) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}
    		
    		if(!(assetRequestExists.getProcessstate().getCode().equals("BODEGA") || assetRequestExists.getProcessstate().getCode().equals("MANTENIMIENTO"))) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}
			
    		Assetrequest assetRequest = this.assetRequestServiceImp.MaintenanceAssetRequest(warehouseRequestDto, assetRequestId);
			
			if(assetRequest == null) {
				return ResponseHandler.generateResponse(true, 1, "Error denied request!", HttpStatus.OK, null);
			}
			
			return ResponseHandler.generateResponse(false, 0, "Successfully denied request!", HttpStatus.OK, assetRequest);
    	} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
 // Update operation
    @RequestMapping(value = "/assetRequests/down/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> DownUser(@RequestBody WarehouseRequestDto warehouseRequestDto, 
    		@PathVariable("id") Integer assetRequestId)
    {
    	try {
    		Assetrequest assetRequestExists = this.assetRequestServiceImp.findById(assetRequestId);
    		if(assetRequestExists == null) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}
    		
    		if(!(assetRequestExists.getProcessstate().getCode().equals("BODEGA") || assetRequestExists.getProcessstate().getCode().equals("MANTENIMIENTO"))) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}
			
    		Assetrequest assetRequest = this.assetRequestServiceImp.DownAssetRequest(warehouseRequestDto, assetRequestId);
			
			if(assetRequest == null) {
				return ResponseHandler.generateResponse(true, 1, "Error denied request!", HttpStatus.OK, null);
			}
			
			return ResponseHandler.generateResponse(false, 0, "Successfully denied request!", HttpStatus.OK, assetRequest);
    	} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
 // Update operation
    @RequestMapping(value = "/assetRequests/devolution/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> DevolutionUser(@RequestBody WarehouseRequestDto warehouseRequestDto, 
    		@PathVariable("id") Integer assetRequestId)
    {
    	try {
    		Assetrequest assetRequestExists = this.assetRequestServiceImp.findById(assetRequestId);
    		if(assetRequestExists == null) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}
    		
    		if(!(assetRequestExists.getProcessstate().getCode().equals("BODEGA") || assetRequestExists.getProcessstate().getCode().equals("MANTENIMIENTO"))) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}
			
    		Assetrequest assetRequest = this.assetRequestServiceImp.DevolutionAssetRequest(warehouseRequestDto, assetRequestId);
			
			if(assetRequest == null) {
				return ResponseHandler.generateResponse(true, 1, "Error denied request!", HttpStatus.OK, null);
			}
			
			return ResponseHandler.generateResponse(false, 0, "Successfully denied request!", HttpStatus.OK, assetRequest);
    	} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
 // Update operation
    @RequestMapping(value = "/assetRequests/proform", method = RequestMethod.POST)
    public ResponseEntity<Object> proformRequest(@RequestBody AssetrequestDto assetRequestDto)
    {
    	try {
    		
    		Assetrequest assetRequestExists = this.assetRequestServiceImp.findById(assetRequestDto.getId());
    		if(assetRequestExists == null) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}
    		
    		if(!(assetRequestExists.getProcessstate().getCode().equals("APROBADO") || assetRequestExists.getProcessstate().getCode().equals("ENVIO_PROFORMA"))) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}

    		Assetrequest assetRequest = modelMapper.map(assetRequestDto, Assetrequest.class);
    		assetRequest = this.assetRequestServiceImp.ProformAssetRequest(assetRequest);
    		if(assetRequest == null) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}

			return ResponseHandler.generateResponse(false, 0, "Successfully proform request!", HttpStatus.OK, assetRequest);
			
		} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
 // Update operation
    @RequestMapping(value = "/assetRequests/approveproform", method = RequestMethod.POST)
    public ResponseEntity<Object> approvedProformRequest(@RequestBody AssetrequestDto assetRequestDto)
    {
    	try {
    		
    		Assetrequest assetRequestExists = this.assetRequestServiceImp.findById(assetRequestDto.getId());
    		if(assetRequestExists == null) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}
    		
    		if(!assetRequestExists.getProcessstate().getCode().equals("ENVIO_PROFORMA")) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}

    		Assetrequest assetRequest = modelMapper.map(assetRequestDto, Assetrequest.class);
    		assetRequest = this.assetRequestServiceImp.ApproveProformAssetRequest(assetRequest);
    		if(assetRequest == null) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}
			
			return ResponseHandler.generateResponse(false, 0, "Successfully proform request!", HttpStatus.OK, assetRequest);
			
		} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
 // Update operation
    @RequestMapping(value = "/assetRequests/deniedproform", method = RequestMethod.POST)
    public ResponseEntity<Object> deniedProformRequest(@RequestBody AssetrequestDto assetRequestDto)
    {
    	try {
    		
    		Assetrequest assetRequestExists = this.assetRequestServiceImp.findById(assetRequestDto.getId());
    		if(assetRequestExists == null) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}
    		
    		if(!assetRequestExists.getProcessstate().getCode().equals("ENVIO_PROFORMA")) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}

    		Assetrequest assetRequest = modelMapper.map(assetRequestDto, Assetrequest.class);
    		assetRequest = this.assetRequestServiceImp.DeniedProformAssetRequest(assetRequest);
    		if(assetRequest == null) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}
			
			return ResponseHandler.generateResponse(false, 0, "Successfully proform request!", HttpStatus.OK, assetRequest);
			
		} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
 // Update operation
    @RequestMapping(value = "/assetRequests/guide", method = RequestMethod.POST)
    public ResponseEntity<Object> GuideAssetRequest(@RequestBody AssetrequestDto assetRequestDto)
    {
    	try {
    		
    		Assetrequest assetRequestExists = this.assetRequestServiceImp.findById(assetRequestDto.getId());
    		if(assetRequestExists == null) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}
    		
    		if(!assetRequestExists.getProcessstate().getCode().equals("PROFORMA_APROBADA")) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}

    		Assetrequest assetRequest = modelMapper.map(assetRequestDto, Assetrequest.class);
    		assetRequest = this.assetRequestServiceImp.GuideAssetRequest(assetRequest);
    		if(assetRequest == null) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}
			
			return ResponseHandler.generateResponse(false, 0, "Successfully proform request!", HttpStatus.OK, assetRequest);
			
		} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
 // Update operation
    @RequestMapping(value = "/assetRequests/register", method = RequestMethod.POST)
    public ResponseEntity<Object> RegisterAssetRequest(@RequestBody AssetrequestDto assetRequestDto)
    {
    	try {
    		
    		Assetrequest assetRequestExists = this.assetRequestServiceImp.findById(assetRequestDto.getId());
    		if(assetRequestExists == null) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}
    		
    		if(!assetRequestExists.getProcessstate().getCode().equals("GUIA_REMISION")) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}

    		Assetrequest assetRequest = modelMapper.map(assetRequestDto, Assetrequest.class);
    		assetRequest = this.assetRequestServiceImp.RegisterAssetRequest(assetRequest);
    		if(assetRequest == null) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}
			
			return ResponseHandler.generateResponse(false, 0, "Successfully proform request!", HttpStatus.OK, assetRequest);
			
		} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
 // Update operation
    @RequestMapping(value = "/assetRequests/dispath/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> DispathAssetRequest(@RequestBody AssetrequestDto assetRequestDto, 
    		@PathVariable("id") Integer assetRequestId)
    {
    	try {
    		
    		Assetrequest assetRequestExists = this.assetRequestServiceImp.findById(assetRequestDto.getId());
    		if(assetRequestExists == null) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}
    		
    		if(!assetRequestExists.getProcessstate().getCode().equals("RECIBIDO_CDI")) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}

    		Assetrequest assetRequest = modelMapper.map(assetRequestDto, Assetrequest.class);
    		assetRequest = this.assetRequestServiceImp.OutAssetRequest(assetRequest, assetRequestId);
    		if(assetRequest == null) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}
			
			return ResponseHandler.generateResponse(false, 0, "Successfully proform request!", HttpStatus.OK, assetRequest);
			
		} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
 // Update operation
    @RequestMapping(value = "/assetRequests/received/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> ReceivedAssetRequest(@RequestBody AssetrequestDto assetRequestDto, 
    		@PathVariable("id") Integer assetRequestId)
    {
    	try {
    		
    		Assetrequest assetRequestExists = this.assetRequestServiceImp.findById(assetRequestDto.getId());
    		if(assetRequestExists == null) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}
    		
    		if(!(assetRequestExists.getProcessstate().getCode().equals("DESPACHADO") || assetRequestExists.getProcessstate().getCode().equals("DEVOLUCION"))) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}

    		Assetrequest assetRequest = modelMapper.map(assetRequestDto, Assetrequest.class);
    		assetRequest = this.assetRequestServiceImp.ReceivedAssetRequest(assetRequest, assetRequestId);
    		if(assetRequest == null) {
    			return ResponseHandler.generateResponse(true, 1, "Error updated data!", HttpStatus.OK, null);
    		}
			
			return ResponseHandler.generateResponse(false, 0, "Successfully proform request!", HttpStatus.OK, assetRequest);
			
		} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
    
    // Read operation
    @RequestMapping(value = "/assetRequests/{profileId}/{userid}", method = RequestMethod.GET)
    public ResponseEntity<Object> fetchAssetRequests(@PathVariable("userid") Integer userid)
    {
    	try {
    		List<Assetrequest> assetrequests = null;
    		
    		assetrequests = this.assetRequestServiceImp.findByUseridrequested(userid);
    		
    		if(assetrequests == null || assetrequests.isEmpty()) {
    			return ResponseHandler.generateResponse(true, 1, "No Data Found ", HttpStatus.OK, null);
    		}
    		List<Map<String, Object>> listRequests = assetrequests.stream().map(item -> {
    			Map<String, Object> rquestDto = new HashMap<String, Object>();
    			rquestDto.put("id", item.getId());
        		rquestDto.put("description", item.getDescription());
        		rquestDto.put("derparmentdescription", item.getWorkdepartment().getDescription());
        		rquestDto.put("agencydescripcion", item.getAgency().getDescription());
        		rquestDto.put("state", item.getProcessstate().getDescription());
        		rquestDto.put("date", item.getDatecreate());
        		rquestDto.put("companydescription", item.getCompanytobebilling().getDescription());
        		rquestDto.put("type", item.getCatalog().getName());
        		return rquestDto;
    		}).collect(Collectors.toList());
    		
    		return ResponseHandler.generateResponse(false, 0, "OK", HttpStatus.OK, listRequests);
    	} catch (Exception e) {
    		return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
    	}
    }
    
 // Read operation
    @RequestMapping(value = "/assetRequests/assigned", method = RequestMethod.GET)
    public ResponseEntity<Object> fetchRequestAssigned()
    {
    	try {
    		List<Assetrequest> assetrequests = null;
    		
    		List<String> codes = new ArrayList<String>();
    		codes.add("INGRESADO");
    		codes.add("PENDIENTE"); 
    		codes.add("APROBADO"); 
    		codes.add("RECHAZADO");
    		
    		List<Processstate> processStates = processStateServiceImp.findByCodeIn(codes);
    		
    		List<Integer> statesid = new ArrayList<Integer>();
    		processStates.stream().forEach(item -> {
    			statesid.add(item.getId());
    		});
    		
    		
    		assetrequests = this.assetRequestServiceImp.findByProcessstateidIn(statesid);
    		
    		if(assetrequests == null || assetrequests.isEmpty()) {
    			return ResponseHandler.generateResponse(true, 1, "No Data Found ", HttpStatus.OK, null);
    		}
    		List<Map<String, Object>> listRequests = assetrequests.stream().map(item -> {
    			Map<String, Object> requestDto = new HashMap<String, Object>();
    			requestDto.put("id", item.getId());
        		requestDto.put("description", item.getDescription());
        		requestDto.put("derparmentdescription", item.getWorkdepartment().getDescription());
        		requestDto.put("agencydescripcion", item.getAgency().getDescription());
        		requestDto.put("state", item.getProcessstate().getDescription());
        		requestDto.put("date", item.getDatecreate());
        		requestDto.put("companydescription", item.getCompanytobebilling().getDescription());
        		requestDto.put("filerequest", item.getFilerequest());
        		requestDto.put("type", item.getCatalog().getName());
        		return requestDto;
    		}).collect(Collectors.toList());
    		
    		return ResponseHandler.generateResponse(false, 0, "OK", HttpStatus.OK, listRequests);
    	} catch (Exception e) {
    		return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
    	}
    }
    
 // Read operation By Id
    @RequestMapping(value = "/assetRequests/assigned/filters", method = RequestMethod.POST)
    public ResponseEntity<Object> fetchRequestAssignedFilter(@RequestBody Map<String, Object> parameters)
    {
    	try {
    		List<HashMap<String, Object>> result = this.assetRequestServiceImp.fetchRequestAssignedFilter(parameters);
        	return ResponseHandler.generateResponse(false, 0, "OK", HttpStatus.OK, result);
    	} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
 // Read operation By Id
    @RequestMapping(value = "/assetRequests/scanner/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> findProductById(@PathVariable("id") Integer id)
    {
    	try {
    		List<HashMap<String, Object>> result = this.assetRequestServiceImp.findProductById(id);
        	return ResponseHandler.generateResponse(false, 0, "OK", HttpStatus.OK, result);
    	} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
 // Read operation
    @RequestMapping(value = "/assetRequests/denied", method = RequestMethod.GET)
    public ResponseEntity<Object> fetchRequestDenied()
    {
    	try {
    		List<Assetrequest> assetrequests = null;
    		
    		List<String> codes = new ArrayList<String>();
    		codes.add("APROBADO");
    		codes.add("ENVIO_PROFORMA"); 
    		codes.add("PROFORMA_APROBADA"); 
    		codes.add("PROFORMA_RECHAZADA");
    		
    		List<Processstate> processStates = processStateServiceImp.findByCodeIn(codes);
    		
    		List<Integer> statesid = new ArrayList<Integer>();
    		processStates.stream().forEach(item -> {
    			statesid.add(item.getId());
    		});
    		
    		
    		assetrequests = this.assetRequestServiceImp.findByProcessstateidIn(statesid);
    		
    		if(assetrequests == null || assetrequests.isEmpty()) {
    			return ResponseHandler.generateResponse(true, 1, "No Data Found ", HttpStatus.OK, null);
    		}
    		List<Map<String, Object>> listRequests = assetrequests.stream().map(item -> {
    			Map<String, Object> requestDto = new HashMap<String, Object>();
    			requestDto.put("id", item.getId());
        		requestDto.put("description", item.getDescription());
        		requestDto.put("derparmentdescription", item.getWorkdepartment().getDescription());
        		requestDto.put("agencydescripcion", item.getAgency().getDescription());
        		requestDto.put("state", item.getProcessstate().getDescription());
        		requestDto.put("date", item.getDatecreate());
        		requestDto.put("companydescription", item.getCompanytobebilling().getDescription());
        		requestDto.put("filerequest", item.getFilerequest());
        		requestDto.put("type", item.getCatalog().getName());
        		return requestDto;
    		}).collect(Collectors.toList());
    		
    		return ResponseHandler.generateResponse(false, 0, "OK", HttpStatus.OK, listRequests);
    	} catch (Exception e) {
    		return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
    	}
    }
    
 // Read operation By Id
    @RequestMapping(value = "/assetRequests/denied/filters", method = RequestMethod.POST)
    public ResponseEntity<Object> fetchRequestDeniedFilter(@RequestBody Map<String, Object> parameters)
    {
    	try {
    		List<HashMap<String, Object>> result = this.assetRequestServiceImp.fetchRequestDeniedFilter(parameters);
        	return ResponseHandler.generateResponse(false, 0, "OK", HttpStatus.OK, result);
    	} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
    // Read operation By Id
    @RequestMapping(value = "/assetRequests/warehouse/filters", method = RequestMethod.POST)
    public ResponseEntity<Object> fetchRequestWareHouseFilter(@RequestBody Map<String, Object> parameters)
    {
    	try {
    		List<HashMap<String, Object>> result = this.assetRequestServiceImp.fetchRequestWareHouseFilter(parameters);
        	return ResponseHandler.generateResponse(false, 0, "OK", HttpStatus.OK, result);
    	} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
 
    
 // Read operation
    @RequestMapping(value = "/assetRequests/manager", method = RequestMethod.GET)
    public ResponseEntity<Object> fetchRequestManagerApproved()
    {
    	try {
    		List<Assetrequest> assetrequests = null;
    		
    		List<String> codes = new ArrayList<String>();
    		codes.add("ENVIO_PROFORMA"); 
    		codes.add("PROFORMA_APROBADA"); 
    		codes.add("PROFORMA_RECHAZADA");
    		
    		List<Processstate> processStates = processStateServiceImp.findByCodeIn(codes);
    		
    		List<Integer> statesid = new ArrayList<Integer>();
    		processStates.stream().forEach(item -> {
    			statesid.add(item.getId());
    		});
    		
    		
    		assetrequests = this.assetRequestServiceImp.findByProcessstateidIn(statesid);
    		
    		if(assetrequests == null || assetrequests.isEmpty()) {
    			return ResponseHandler.generateResponse(true, 1, "No Data Found ", HttpStatus.OK, null);
    		}
    		List<Map<String, Object>> listRequests = assetrequests.stream().map(item -> {
    			Map<String, Object> requestDto = new HashMap<String, Object>();
    			requestDto.put("id", item.getId());
        		requestDto.put("description", item.getDescription());
        		requestDto.put("derparmentdescription", item.getWorkdepartment().getDescription());
        		requestDto.put("agencydescripcion", item.getAgency().getDescription());
        		requestDto.put("state", item.getProcessstate().getDescription());
        		requestDto.put("date", item.getDatecreate());
        		requestDto.put("companydescription", item.getCompanytobebilling().getDescription());
        		requestDto.put("filerequest", item.getFilerequest());
        		requestDto.put("type", item.getCatalog().getName());
        		return requestDto;
    		}).collect(Collectors.toList());
    		
    		return ResponseHandler.generateResponse(false, 0, "OK", HttpStatus.OK, listRequests);
    	} catch (Exception e) {
    		return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
    	}
    }
    
 // Read operation By Id
    @RequestMapping(value = "/assetRequests/manager/filters", method = RequestMethod.POST)
    public ResponseEntity<Object> fetchRequestManagerApprovedFilter(@RequestBody Map<String, Object> parameters)
    {
    	try {
    		List<HashMap<String, Object>> result = this.assetRequestServiceImp.fetchRequestManagerApprovedFilter(parameters);
        	return ResponseHandler.generateResponse(false, 0, "OK", HttpStatus.OK, result);
    	} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
 // Read operation By Id
    @RequestMapping(value = "/assetRequests/inventory/filters", method = RequestMethod.POST)
    public ResponseEntity<Object> fetchRequestInventoryApprovedFilter(@RequestBody Map<String, Object> parameters)
    {
    	try {
    		List<HashMap<String, Object>> result = this.assetRequestServiceImp.fetchRequestInventoyFilter(parameters);
        	return ResponseHandler.generateResponse(false, 0, "OK", HttpStatus.OK, result);
    	} catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
    }
    
    // Delete operation
    @RequestMapping(value = "/assetRequests/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUserById(@PathVariable("id")
                                       Integer assetRequestId)
    {
    	try {
    		
    		List<Processstate> process = this.processStateServiceImp.findByCode("INGRESADO");
    		
    		if(process == null || process.isEmpty()) {
    			return ResponseHandler.generateResponse(true, 1, "Error deleted data!", HttpStatus.OK, null);
    		}
    		
    		Assetrequest assetrequest = assetRequestServiceImp.findByIdAndProcessstateid(assetRequestId, process.get(0).getId());
    		
    		if(assetrequest == null) {
    			return ResponseHandler.generateResponse(true, 1, "Estado no es ingresado!", HttpStatus.OK, null);
    		}
    		
    		Boolean deleted = this.assetRequestServiceImp.DeleteAssetRequestById(assetRequestId);
    		
    		if(!deleted) {
    			return ResponseHandler.generateResponse(true, 1, "Error deleted data!", HttpStatus.OK, null);
    		}
    		
    		return ResponseHandler.generateResponse(false, 0, "Successfully deleted data!", HttpStatus.OK, null);
    	}
    	catch (Exception e) {
			return ResponseHandler.generateResponse(true, 99, e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
        
    }
    
    
}
