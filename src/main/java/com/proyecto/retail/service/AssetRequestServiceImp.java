package com.proyecto.retail.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.retail.Dto.ProformDto;
import com.proyecto.retail.Dto.RequestAssignedDto;
import com.proyecto.retail.Dto.WarehouseRequestDto;
import com.proyecto.retail.Infraestructura.ExecuteProcedure;
import com.proyecto.retail.Infraestructura.SqlQueries;
import com.proyecto.retail.model.Agency;
import com.proyecto.retail.model.Assetrequest;
import com.proyecto.retail.model.Catalog;
import com.proyecto.retail.model.Companytobebilling;
import com.proyecto.retail.model.Inventory;
import com.proyecto.retail.model.Inventoryproduct;
import com.proyecto.retail.model.Processstate;
import com.proyecto.retail.model.Product;
import com.proyecto.retail.model.Productproform;
import com.proyecto.retail.model.Proform;
import com.proyecto.retail.model.Workdepartment;
import com.proyecto.retail.repository.AgencyRepository;
import com.proyecto.retail.repository.AssetRequestRepository;
import com.proyecto.retail.repository.CatalogRepository;
import com.proyecto.retail.repository.CompanyToBeBillingRepository;
import com.proyecto.retail.repository.InventoryRepository;
import com.proyecto.retail.repository.InventoryproductRepository;
import com.proyecto.retail.repository.ProcessStateRepository;
import com.proyecto.retail.repository.ProductProformRepository;
import com.proyecto.retail.repository.ProductRepository;
import com.proyecto.retail.repository.ProformRepository;
import com.proyecto.retail.repository.WorkDepartmentRepository;

@Service
public class AssetRequestServiceImp implements AssetRequestService {
	
	@Autowired
	private AssetRequestRepository assetRequestRepository;
	
	@Autowired
	private ProcessStateRepository processStateRepository;
	
	@Autowired
	private AgencyRepository agencyRepository; 
	
	@Autowired
	private WorkDepartmentRepository workDepartmentRepository; 
	
	@Autowired
	private CompanyToBeBillingRepository companyToBeBillingRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CatalogRepository catalogRepository;
	
	@Autowired
	private ProformRepository proformRepository;
	
	@Autowired
	private ProductProformRepository productProformRepository;
	
	@Autowired
	private ExecuteProcedure executeProcedure;
	
	@Autowired
	private InventoryproductRepository inventoryproductRepository;
	
	@Autowired
	private InventoryRepository inventoryRepository;
	
	
	@Override
	public Assetrequest findById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Assetrequest> assetRequest = this.assetRequestRepository.findById(id);
		if(!assetRequest.isPresent() ) {
			return null;
		}
		return assetRequest.get();
	}
	
	@Override
	public List<HashMap<String, Object>> getById(Map<String, Object> parameters) {
		SqlQueries sql = new SqlQueries();
		return executeProcedure.executeProcedure(sql.consultasolicitud, parameters);
	}
	
	@Override
	public List<HashMap<String, Object>> fetchRequestAssignedFilter(Map<String, Object> parameters) {
		SqlQueries sql = new SqlQueries();
		return executeProcedure.executeProcedure(sql.consultaSolicitudSoporte, parameters);
	}
	
	@Override
	public List<HashMap<String, Object>> findProductById(Integer id) {
		SqlQueries sql = new SqlQueries();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("p_id",id);
		return executeProcedure.executeProcedure(sql.consultaInventoyProductById, parameters);
	}
	
	@Override
	public List<HashMap<String, Object>> fetchRequestDeniedFilter(Map<String, Object> parameters) {
		SqlQueries sql = new SqlQueries();
		return executeProcedure.executeProcedure(sql.consultaSolicitudVentas, parameters);
	}
	
	@Override
	public List<HashMap<String, Object>> fetchRequestWareHouseFilter(Map<String, Object> parameters) {
		SqlQueries sql = new SqlQueries();
		return executeProcedure.executeProcedure(sql.consultaSolicitudBodega, parameters);
	}
	
	@Override
	public List<HashMap<String, Object>> fetchRequestManagerApprovedFilter(Map<String, Object> parameters) {
		SqlQueries sql = new SqlQueries();
		return executeProcedure.executeProcedure(sql.consultaSolicitudGerencia, parameters);
	}
	
	@Override
	public List<HashMap<String, Object>> fetchRequestInventoyFilter(Map<String, Object> parameters) {
		SqlQueries sql = new SqlQueries();
		return executeProcedure.executeProcedure(sql.consultaSolicitudInventario, parameters);
	}
	
	@Override
	public Assetrequest findByIdAndProcessstateid(Integer id, Integer idState) {
		Assetrequest assetRequest = this.assetRequestRepository.findByIdAndProcessstateidAndStateTrue(id, idState);
		if(assetRequest == null) {
			return null;
		}
		return assetRequest;
	}

	@Override
	public Assetrequest SaveAssetRequest(Assetrequest assetrequest) {
		// TODO Auto-generated method stub
		List<Processstate> processstates = this.processStateRepository.findByCodeAndStateTrue("INGRESADO");
		if(processstates.isEmpty()) {
			return null;
		}
		
		Processstate processstate = processstates.get(0);
		assetrequest.setProcessstate(processstate);
		
		Agency agency = null;
		agency = agencyRepository.getById(assetrequest.getAgencyid());
		if(agency == null) {
			return null;
		}
		assetrequest.setAgency(agency);
		Workdepartment workdDepartment = null;
		workdDepartment = workDepartmentRepository.getById(assetrequest.getWorkdepartmentid());
		
		if(workdDepartment == null) {
			return null;
		}
		assetrequest.setWorkdepartment(workdDepartment);
		
		Companytobebilling companyToBeBilling = null;
		
		companyToBeBilling = companyToBeBillingRepository.getById(assetrequest.getBillingid());
		
		if(companyToBeBilling == null) {
			return null;
		}
		assetrequest.setCompanytobebilling(companyToBeBilling);
		
		Catalog catalog = this.catalogRepository.getById(assetrequest.getCatalogid());
		if(catalog == null) {
			return null;
		}
		
		assetrequest.setCatalog(catalog);
		
		Catalog priority = this.catalogRepository.getById(assetrequest.getPrioritytypeid());
		if(priority == null) {
			return null;
		}
		
		assetrequest.setPrioritytype(priority);
		
		if(catalog.getName().equals("Mantenimiento")) {
			Inventoryproduct inventoryproduct = this.inventoryproductRepository.getById(assetrequest.getInventoryproductid());
			if(inventoryproduct == null) {
				return null;
			}
			
			assetrequest.setInventoryproduct(inventoryproduct);
		}
		
		
		Assetrequest assetrequestSave = this.assetRequestRepository.save(assetrequest);
		
		List<Product> products = assetrequestSave.getProducts();
		products.stream().forEach(item -> {
			Catalog catalogProduct = null;
			item.setAssetrequest(assetrequestSave);
			catalogProduct = this.catalogRepository.getById(item.getCatalogid());
			item.setCatalog(catalogProduct);
		
			this.productRepository.save(item);
		});
		return assetrequestSave;
	}
	
	@Override
	public Assetrequest UpdateAssetRequest(Assetrequest assetrequest, Integer assetRequestId) {
		// TODO Auto-generated method stub
		Optional<Assetrequest> assetRequestExists = this.assetRequestRepository.findById(assetrequest.getId());
		if(!assetRequestExists.isPresent() ) {
			return null;
		}
		
		Assetrequest assetRequestUpdate = assetRequestExists.get();
		
		Processstate processstate = this.processStateRepository.getById(assetrequest.getProcessstateid());
		if(processstate == null) {
			return null;
		}
		assetRequestUpdate.setProcessstate(processstate);
				
		Agency agency = null;
		agency = agencyRepository.getById(assetrequest.getAgencyid());
		if(agency == null) {
			return null;
		}
		assetRequestUpdate.setAgency(agency);
		Workdepartment workdDepartment = null;
		workdDepartment = workDepartmentRepository.getById(assetrequest.getWorkdepartmentid());
				
		if(workdDepartment == null) {
			return null;
		}
		assetRequestUpdate.setWorkdepartment(workdDepartment);
				
		Companytobebilling companyToBeBilling = null;
				
		companyToBeBilling = companyToBeBillingRepository.getById(assetrequest.getBillingid());
				
		if(companyToBeBilling == null) {
			return null;
		}
		
		Catalog catalog = this.catalogRepository.getById(assetrequest.getCatalogid());
		if(catalog == null) {
			return null;
		}
		
		Catalog priority = this.catalogRepository.getById(assetrequest.getPrioritytypeid());
		if(priority == null) {
			return null;
		}
		
		if(catalog.getName().equals("Mantenimiento")) {
			Inventoryproduct inventoryproduct = this.inventoryproductRepository.getById(assetrequest.getInventoryproductid());
			if(inventoryproduct == null) {
				return null;
			}
			
			assetRequestUpdate.setInventoryproduct(inventoryproduct);
		}

		
		assetRequestUpdate.setCompanytobebilling(companyToBeBilling);
		assetRequestUpdate.setDescription(assetrequest.getDescription());
		assetRequestUpdate.setCasenumber(assetrequest.getCasenumber());
		assetRequestUpdate.setFilerequest(assetrequest.getFilerequest());
		assetRequestUpdate.setCatalog(catalog);
		assetrequest.setPrioritytype(priority);
				
		Assetrequest assetrequestSave = this.assetRequestRepository.save(assetRequestUpdate);
		
		List<Product> products = assetrequest.getProducts();
		products.stream().forEach(item -> {
			Optional<Product> productExists = this.productRepository.findById(item.getId());
			Product productUpdate = productExists.get();
			Catalog catalogProduct = null;
			productUpdate.setAssetrequest(assetrequestSave);
			catalogProduct = this.catalogRepository.getById(item.getCatalogid());
			productUpdate.setCatalog(catalogProduct);
			productUpdate.setQuantity(item.getQuantity());
			this.productRepository.save(productUpdate);
		});		
		
		return assetrequestSave;
	}
	
	@Override
	public boolean DeleteAssetRequestById(Integer assetRequestId) {
		Optional<Assetrequest> assetRequestExists = this.assetRequestRepository.findById(assetRequestId);
		if(!assetRequestExists.isPresent() ) {
			return false;
		}
		Assetrequest assetRequestUpdate = assetRequestExists.get();
		assetRequestUpdate.setState(false);
		assetRequestRepository.save(assetRequestUpdate);
		return true;
	}

	@Override
	public Assetrequest AssignAssetRequest(RequestAssignedDto assetrequest, Integer assetRequestId) {
		Optional<Assetrequest> assetRequestExists = this.assetRequestRepository.findById(assetrequest.getId());
		if(!assetRequestExists.isPresent() ) {
			return null;
		}
		Assetrequest assetRequestUpdate = assetRequestExists.get();
		
		List<Processstate> processstates = this.processStateRepository.findByCodeAndStateTrue("PENDIENTE");
		if(processstates.isEmpty()) {
			return null;
		}
		
		Processstate processstate = processstates.get(0);
		assetRequestUpdate.setProcessstate(processstate);
		assetRequestUpdate.setUseridassigned(assetrequest.getUseridassigned());
		assetRequestUpdate.setDateassigned(new Timestamp(new Date().getTime()));
		
		return this.assetRequestRepository.save(assetRequestUpdate);
	}

	@Override
	public Assetrequest ApproveAssetRequest(Assetrequest assetrequest, Integer assetRequestId) {
		Optional<Assetrequest> assetRequestExists = this.assetRequestRepository.findById(assetrequest.getId());
		if(!assetRequestExists.isPresent() ) {
			return null;
		}
		Assetrequest assetRequestUpdate = assetRequestExists.get();
		List<Processstate> processstates = null;
		processstates = this.processStateRepository.findByCodeAndStateTrue("APROBADO");
		
		if(processstates.isEmpty()) {
			return null;
		}

		List<Product> products = assetrequest.getProducts();
		products.stream().forEach(item -> {
			Product product = this.productRepository.getById(item.getId());
			Catalog catalogProduct = this.catalogRepository.getById(item.getCatalogproductid());
			product.setCatalogproduct(catalogProduct);
			product.setTechnicaldescription(item.getTechnicaldescription());
			
			this.productRepository.save(product);
		});
		
		Processstate processstate = processstates.get(0);
		assetRequestUpdate.setProcessstate(processstate);
		assetRequestUpdate.setUseridapproved(assetrequest.getUseridapproved());
		assetRequestUpdate.setDescription(assetrequest.getDescription());
		assetRequestUpdate.setDateapproved(new Timestamp(new Date().getTime()));
		
		Assetrequest assetrequestSave = this.assetRequestRepository.save(assetRequestUpdate);
		
		return assetrequestSave;
	}
	
	@Override
	public Assetrequest DeniedAssetRequest(Assetrequest assetrequest, Integer assetRequestId) {
		Optional<Assetrequest> assetRequestExists = this.assetRequestRepository.findById(assetrequest.getId());
		if(!assetRequestExists.isPresent() ) {
			return null;
		}
		Assetrequest assetRequestUpdate = assetRequestExists.get();
		List<Processstate> processstates = null;
		processstates = this.processStateRepository.findByCodeAndStateTrue("RECHAZADO");
		
		if(processstates.isEmpty()) {
			return null;
		}
		
		Processstate processstate = processstates.get(0);
		assetRequestUpdate.setProcessstate(processstate);
		assetRequestUpdate.setUseriddenied(assetrequest.getUseridwarehouse());
		assetRequestUpdate.setDatedenied(new Timestamp(new Date().getTime()));
		assetRequestUpdate.setDeniedobservation(assetrequest.getDeniedobservation());
		
		return this.assetRequestRepository.save(assetRequestUpdate);
	}
	
	@Override
	public Assetrequest WareHouseAssetRequest(Assetrequest assetrequest, Integer assetRequestId) {
		Optional<Assetrequest> assetRequestExists = this.assetRequestRepository.findById(assetrequest.getId());
		if(!assetRequestExists.isPresent() ) {
			return null;
		}
		Assetrequest assetRequestUpdate = assetRequestExists.get();
		List<Processstate> processstates = null;
		processstates = this.processStateRepository.findByCodeAndStateTrue("BODEGA");
		
		if(processstates.isEmpty()) {
			return null;
		}
		
		List<Catalog> catalogs = this.catalogRepository.findByCodeAndStateTrue("EP");
		
		if(catalogs.isEmpty()) {
			return null;
		}
		
		Optional<Catalog> catalogExists = catalogs.stream().filter((item) -> item.getName().equals("BODEGA")).findFirst();
		if(!catalogExists.isPresent()) {
			return null;
		}
		
		Inventoryproduct inventoryProduct = this.inventoryproductRepository.getById(assetrequest.getInventoryproductid());
		if(inventoryProduct == null) {
			return null;
		}
		inventoryProduct.setProductstate(catalogExists.get());
		
		inventoryProduct = this.inventoryproductRepository.save(inventoryProduct);
		
		Processstate processstate = processstates.get(0);
		assetRequestUpdate.setProcessstate(processstate);
		assetRequestUpdate.setUseridwarehouse(assetrequest.getUseridwarehouse());
		assetRequestUpdate.setDatewarehouse(new Timestamp(new Date().getTime()));
		
		return this.assetRequestRepository.save(assetRequestUpdate);
	}
	
	@Override
	public Assetrequest MaintenanceAssetRequest(WarehouseRequestDto assetrequest, Integer assetRequestId) {
		Optional<Assetrequest> assetRequestExists = this.assetRequestRepository.findById(assetrequest.getId());
		if(!assetRequestExists.isPresent() ) {
			return null;
		}
		Assetrequest assetRequestUpdate = assetRequestExists.get();
		List<Processstate> processstates = null;
		processstates = this.processStateRepository.findByCodeAndStateTrue("MANTENIMIENTO");
		
		if(processstates.isEmpty()) {
			return null;
		}
		
		List<Catalog> catalogs = this.catalogRepository.findByCodeAndStateTrue("EP");
		
		if(catalogs.isEmpty()) {
			return null;
		}
		
		Optional<Catalog> catalogExists = catalogs.stream().filter((item) -> item.getName().equals("MANTENIMIENTO")).findFirst();
		if(!catalogExists.isPresent()) {
			return null;
		}
		
		Inventoryproduct inventoryProduct = this.inventoryproductRepository.getById(assetrequest.getInventoryproductid());
		if(inventoryProduct == null) {
			return null;
		}
		inventoryProduct.setProductstate(catalogExists.get());
		
		inventoryProduct = this.inventoryproductRepository.save(inventoryProduct);
		
		Processstate processstate = processstates.get(0);
		assetRequestUpdate.setProcessstate(processstate);
		assetRequestUpdate.setUseridmaintenance(assetrequest.getUserid());
		assetRequestUpdate.setDatemaintenance(new Timestamp(new Date().getTime()));
		assetRequestUpdate.setMaintenanceobservation(assetrequest.getObservation());
		
		return this.assetRequestRepository.save(assetRequestUpdate);
	}
	
	@Override
	public Assetrequest DownAssetRequest(WarehouseRequestDto assetrequest, Integer assetRequestId) {
		Optional<Assetrequest> assetRequestExists = this.assetRequestRepository.findById(assetrequest.getId());
		if(!assetRequestExists.isPresent() ) {
			return null;
		}
		Assetrequest assetRequestUpdate = assetRequestExists.get();
		List<Processstate> processstates = null;
		processstates = this.processStateRepository.findByCodeAndStateTrue("BAJA");
		
		if(processstates.isEmpty()) {
			return null;
		}
		
		List<Catalog> catalogs = this.catalogRepository.findByCodeAndStateTrue("EP");
		
		if(catalogs.isEmpty()) {
			return null;
		}
		
		Optional<Catalog> catalogExists = catalogs.stream().filter((item) -> item.getName().equals("BAJA")).findFirst();
		if(!catalogExists.isPresent()) {
			return null;
		}
		
		Inventoryproduct inventoryProduct = this.inventoryproductRepository.getById(assetrequest.getInventoryproductid());
		if(inventoryProduct == null) {
			return null;
		}
		inventoryProduct.setProductstate(catalogExists.get());
		
		inventoryProduct = this.inventoryproductRepository.save(inventoryProduct);
		
		Processstate processstate = processstates.get(0);
		assetRequestUpdate.setProcessstate(processstate);
		assetRequestUpdate.setUseriddown(assetrequest.getUserid());
		assetRequestUpdate.setDatedown(new Timestamp(new Date().getTime()));
		assetRequestUpdate.setDownobservation(assetrequest.getObservation());
		
		return this.assetRequestRepository.save(assetRequestUpdate);
	}
	
	@Override
	public Assetrequest DevolutionAssetRequest(WarehouseRequestDto assetrequest, Integer assetRequestId) {
		Optional<Assetrequest> assetRequestExists = this.assetRequestRepository.findById(assetrequest.getId());
		if(!assetRequestExists.isPresent() ) {
			return null;
		}
		Assetrequest assetRequestUpdate = assetRequestExists.get();
		List<Processstate> processstates = null;
		processstates = this.processStateRepository.findByCodeAndStateTrue("DEVOLUCION");
		
		if(processstates.isEmpty()) {
			return null;
		}
		
		Processstate processstate = processstates.get(0);
		assetRequestUpdate.setProcessstate(processstate);
		assetRequestUpdate.setUseriddevolution(assetrequest.getUserid());
		assetRequestUpdate.setDatedevolution(new Timestamp(new Date().getTime()));
		
		return this.assetRequestRepository.save(assetRequestUpdate);
	}

	@Override
	public Assetrequest ProformAssetRequest(Assetrequest assetrequest) {
		// TODO Auto-generated method stub
		Optional<Assetrequest> assetRequestExists = this.assetRequestRepository.findById(assetrequest.getId());
		if(!assetRequestExists.isPresent() ) {
			return null;
		}
		
		Assetrequest assetRequestUpdate = assetRequestExists.get();
		List<Processstate> processstates = this.processStateRepository.findByCodeAndStateTrue("ENVIO_PROFORMA");
		if(processstates.isEmpty()) {
			return null;
		}

		List<Proform> proforms = assetrequest.getProforms();
		
		for (Proform item:proforms) {
			item.setAssetrequest(assetRequestUpdate);
			List<Productproform> productProforms = item.getProductproforms();
			Proform proformSave = this.proformRepository.save(item);
			productProforms.stream().forEach(itemPF -> {
				itemPF.setProform(proformSave);
				Product product = this.productRepository.getById(itemPF.getProductid());
				itemPF.setProduct(product);
				this.productProformRepository.save(itemPF);
			});
		}
		
		Processstate processstate = processstates.get(0);
		
		assetRequestUpdate.setProcessstate(processstate);
		assetRequestUpdate.setUseridproform(assetrequest.getUseridproform());
		assetRequestUpdate.setDateproform(new Timestamp(new Date().getTime()));
		this.assetRequestRepository.save(assetRequestUpdate);
		return assetRequestUpdate;
	}

	@Override
	public Assetrequest ApproveProformAssetRequest(Assetrequest assetrequest) {
		// TODO Auto-generated method stub
		Optional<Assetrequest> assetRequestExists = this.assetRequestRepository.findById(assetrequest.getId());
		if(!assetRequestExists.isPresent() ) {
			return null;
		}
				
		Assetrequest assetRequestUpdate = assetRequestExists.get();
		List<Processstate> processstates = this.processStateRepository.findByCodeAndStateTrue("PROFORMA_APROBADA");
		if(processstates.isEmpty()) {
			return null;
		}

		List<Proform> proforms = assetrequest.getProforms();
				
		for (Proform item:proforms) {
			item.setAssetrequest(assetRequestUpdate);
			Proform proformSave = this.proformRepository.save(item);
			List<Productproform> productProforms = item.getProductproforms();
			productProforms.stream().forEach(itemPF -> {
				itemPF.setProform(proformSave);
				Product product = this.productRepository.getById(itemPF.getProductid());
				itemPF.setProduct(product);
				this.productProformRepository.save(itemPF);
			});
		}
				
		Processstate processstate = processstates.get(0);
				
		assetRequestUpdate.setProcessstate(processstate);
		assetRequestUpdate.setUseridapprovedproform(assetrequest.getUseridapprovedproform());
		assetRequestUpdate.setDateapprovedproform(new Timestamp(new Date().getTime()));
		this.assetRequestRepository.save(assetRequestUpdate);
		return assetRequestUpdate;
	}
	
	@Override
	public Assetrequest DeniedProformAssetRequest(Assetrequest assetrequest) {
		Optional<Assetrequest> assetRequestExists = this.assetRequestRepository.findById(assetrequest.getId());
		if(!assetRequestExists.isPresent() ) {
			return null;
		}
		Assetrequest assetRequestUpdate = assetRequestExists.get();
		List<Processstate> processstates = null;
		processstates = this.processStateRepository.findByCodeAndStateTrue("PROFORMA_RECHAZADA");
		
		if(processstates.isEmpty()) {
			return null;
		}
		
		Processstate processstate = processstates.get(0);
		assetRequestUpdate.setProcessstate(processstate);
		assetRequestUpdate.setUseriddeniedproform(assetrequest.getUseriddeniedproform());
		assetRequestUpdate.setDatedeniedproform(new Timestamp(new Date().getTime()));
		assetRequestUpdate.setDeniedproformobservation(assetrequest.getDeniedproformobservation());
		
		return this.assetRequestRepository.save(assetRequestUpdate);
	}
	
	@Override
	public Assetrequest GuideAssetRequest(Assetrequest assetrequest) {
		Optional<Assetrequest> assetRequestExists = this.assetRequestRepository.findById(assetrequest.getId());
		if(!assetRequestExists.isPresent() ) {
			return null;
		}
		Assetrequest assetRequestUpdate = assetRequestExists.get();
		List<Processstate> processstates = null;
		processstates = this.processStateRepository.findByCodeAndStateTrue("GUIA_REMISION");
		
		if(processstates.isEmpty()) {
			return null;
		}
		
		Processstate processstate = processstates.get(0);
		assetRequestUpdate.setProcessstate(processstate);
		assetRequestUpdate.setUseridguide(assetrequest.getUseridguide());
		assetRequestUpdate.setDateguide(new Timestamp(new Date().getTime()));
		assetRequestUpdate.setFilereferralguide(assetrequest.getFilereferralguide());
		assetRequestUpdate.setNumberguide(assetrequest.getNumberguide());
		
		return this.assetRequestRepository.save(assetRequestUpdate);
	}
	
	@Override
	public Assetrequest RegisterAssetRequest(Assetrequest assetrequest) {
		Optional<Assetrequest> assetRequestExists = this.assetRequestRepository.findById(assetrequest.getId());
		if(!assetRequestExists.isPresent() ) {
			return null;
		}
		Assetrequest assetRequestUpdate = assetRequestExists.get();
		List<Processstate> processstates = null;
		processstates = this.processStateRepository.findByCodeAndStateTrue("RECIBIDO_CDI");
		
		if(processstates.isEmpty()) {
			return null;
		}
		
		List<Product> products = assetrequest.getProducts();
		List<Inventoryproduct> inventoryproductsins = new ArrayList<Inventoryproduct>();
		for (Product item:products) {
			item.setAssetrequest(assetRequestUpdate);
			List<Inventoryproduct> inventoryproducts = item.getInventoryproducts();
			for(Inventoryproduct itemIP: inventoryproducts) {
				Agency agency = agencyRepository.getById(assetrequest.getAgencyid());
				if(agency == null) {
					return null;
				}
				itemIP.setAgency(agency);
				
				 Workdepartment workdDepartment = workDepartmentRepository.getById(itemIP.getWorkdepartmentid());		
				if(workdDepartment == null) {
					return null;
				}
				itemIP.setWorkdepartment(workdDepartment);
						
				Companytobebilling companyToBeBilling = companyToBeBillingRepository.getById(itemIP.getBillingid());	
				if(companyToBeBilling == null) {
					return null;
				}
				itemIP.setCompanytobebilling(companyToBeBilling);
				
				Product product = productRepository.getById(itemIP.getProductid());
				if(product == null) {
					return null;
				}
				itemIP.setProduct(product);
				
				Inventory inventory = inventoryRepository.getById(itemIP.getInventoryid());
				if(inventory == null) {
					return null;
				}
				itemIP.setInventory(inventory);
				
				Catalog producState = catalogRepository.getById(itemIP.getProductstateid());
				if(producState == null) {
					return null;
				}
				itemIP.setProductstate(producState);
				itemIP.setDatein(new Timestamp(new Date().getTime()));;
				inventoryproductsins.add(itemIP);
			}
			inventoryproductsins = this.inventoryproductRepository.saveAll(inventoryproductsins);
			item.setInventoryproducts(inventoryproductsins);
		}
		
		Processstate processstate = processstates.get(0);
		assetRequestUpdate.setProcessstate(processstate);
		assetRequestUpdate.setUseridguide(assetrequest.getUseridregister());
		assetRequestUpdate.setDateregister(new Timestamp(new Date().getTime()));
		
		return this.assetRequestRepository.save(assetRequestUpdate);
	}
	
	@Override
	public Assetrequest OutAssetRequest(Assetrequest assetrequest, Integer assetRequestId) {
		// TODO Auto-generated method stub
		Optional<Assetrequest> assetRequestExists = this.assetRequestRepository.findById(assetrequest.getId());
		if(!assetRequestExists.isPresent() ) {
			return null;
		}
		Assetrequest assetRequestUpdate = assetRequestExists.get();
		List<Processstate> processstates = this.processStateRepository.findByCodeAndStateTrue("DESPACHADO");
		if(processstates.isEmpty()) {
			return null;
		}
				
		Processstate processstate = processstates.get(0);
		assetRequestUpdate.setProcessstate(processstate);
		assetRequestUpdate.setUseridexited(assetrequest.getUseridexited());
		assetRequestUpdate.setDateexited(new Timestamp(new Date().getTime()));
		
				
		return this.assetRequestRepository.save(assetRequestUpdate);
	}

	@Override
	public Assetrequest ReceivedAssetRequest(Assetrequest assetrequest, Integer assetRequestId) {
		// TODO Auto-generated method stub
		Optional<Assetrequest> assetRequestExists = this.assetRequestRepository.findById(assetrequest.getId());
		if(!assetRequestExists.isPresent() ) {
			return null;
		}
		Assetrequest assetRequestUpdate = assetRequestExists.get();
		List<Processstate> processstates = this.processStateRepository.findByCodeAndStateTrue("RECIBIDO");
		if(processstates.isEmpty()) {
			return null;
		}
		
		Catalog catalog = this.catalogRepository.getById(assetrequest.getCatalogid());
		if(catalog == null) {
			return null;
		}
		
		if(catalog.getName().equals("Mantenimiento")) {
			Inventoryproduct inventoryproductUpdate = this.inventoryproductRepository.getById(assetrequest.getInventoryproductid());
			if(inventoryproductUpdate == null) {
				return null;
			}
			
			List<Catalog> producStates = catalogRepository.findByCodeAndStateTrue("EP");
			if(producStates.isEmpty()) {
				return null;
			}
			
			Optional<Catalog> producState = producStates.stream().filter((item) -> item.getName().equals("OPERATIVO")).findFirst();
			if(!producState.isPresent()) {
				return null;
			}
			inventoryproductUpdate.setProductstate(producState.get());
			this.inventoryproductRepository.save(inventoryproductUpdate);
		}
		else {
			List<Product> products = assetrequest.getProducts();
			List<Inventoryproduct> inventoryproductsupdate = new ArrayList<Inventoryproduct>();
			for (Product item:products) {
				item.setAssetrequest(assetRequestUpdate);
				List<Inventoryproduct> inventoryproducts = item.getInventoryproducts();
				for(Inventoryproduct itemIP: inventoryproducts) {
					Inventoryproduct inventoryproductUpdate = this.inventoryproductRepository.getById(itemIP.getId());
					if(inventoryproductUpdate == null) {
						return null;
					}
					
					Catalog producState = catalogRepository.getById(itemIP.getProductstateid());
					if(producState == null) {
						return null;
					}
					inventoryproductUpdate.setProductstate(producState);
					inventoryproductsupdate.add(inventoryproductUpdate);
				}
				inventoryproductsupdate = this.inventoryproductRepository.saveAll(inventoryproductsupdate);
				item.setInventoryproducts(inventoryproductsupdate);
			}
		}
		
		
		Processstate processstate = processstates.get(0);
		assetRequestUpdate.setProcessstate(processstate);
		assetRequestUpdate.setUseridreceived(assetrequest.getUseridreceived());
		assetRequestUpdate.setDatereceived(new Timestamp(new Date().getTime()));
		
		return this.assetRequestRepository.save(assetRequestUpdate);
	}

	@Override
	public List<Assetrequest> fetchAssetRequestList() {
		// TODO Auto-generated method stub
		return this.assetRequestRepository.findByStateTrue();
	}

	@Override
	public List<Assetrequest> findByUseridrequestedAndProcessstateidIn(Integer useridrequested,List<Integer> processstateid) {
		// TODO Auto-generated method stub
		return this.assetRequestRepository.findByUseridrequestedAndProcessstateidInAndStateTrue(useridrequested, processstateid);
	}
	
	@Override
	public List<Assetrequest> findByProcessstateidIn(List<Integer> processstateid) {
		// TODO Auto-generated method stub
		return this.assetRequestRepository.findByProcessstateidInAndStateTrue(processstateid);
	}

	@Override
	public List<Assetrequest> findByUseridupdateAndProcessstateidIn(String useridupdate, List<Integer> processstateid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Assetrequest> findByUseridrequested(Integer useridrequested) {
		// TODO Auto-generated method stub
		return this.assetRequestRepository.findByUseridrequestedAndStateTrue(useridrequested);
	}

	@Override
	public Assetrequest SendAssetRequest(Assetrequest assetrequest, Integer assetRequestId) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

}
