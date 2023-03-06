package com.proyecto.retail.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="assetrequests")
public class Assetrequest extends BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String casenumber;
	
	private Timestamp dateapproved;

	private Timestamp dateapprovedproform;

	private Timestamp dateassigned;
	
	private Timestamp datedelivered;

	private Timestamp datedenied;

	private Timestamp datedeniedproform;

	private Timestamp dateexited;
	
	private Timestamp dateguide;
	
	private Timestamp datewarehouse;
	
	private Timestamp datemaintenance;
	
	private Timestamp datedown;
	
	private Timestamp datedevolution;
	
	private Timestamp dateregister;
	
	private Timestamp dateproform;

	private Timestamp datereceived;
	
	private String deniedobservation;

	private String deniedproformobservation;
	
	private String description;

	@Type(type = "org.hibernate.type.TextType")
	private String filerequest;
	
	@Type(type = "org.hibernate.type.TextType")
	private String filereferralguide;
	
	private String maintenanceobservation;
	
	private String downobservation;
	
	private String numberguide;

	private String sapcode;
	
	private Integer useridapproved;

	private Integer useridapprovedproform;

	private Integer useridassigned;
	
	private Integer useriddenied;

	private Integer useriddeniedproform;

	private Integer useridexited;
	
	private Integer useridguide;
	
	private Integer useridwarehouse;
	
	private Integer useridmaintenance;
	
	private Integer useriddown;
	
	private Integer useriddevolution;
	
	private Integer useridregister;
	
	private Integer useridproform;

	private Integer useridreceived;

	private Integer useridrequested;
	
	@Column(name="catalogid", insertable=false, updatable=false, nullable=false)
	private Integer catalogid;
	
	//bi-directional many-to-one association to Catalog
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="catalogid")
	private Catalog catalog;
	
	@Column(name="prioritytypeid", insertable=false, updatable=false, nullable=false)
	private Integer prioritytypeid;
	
	//bi-directional many-to-one association to Catalog
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="prioritytypeid")
	private Catalog prioritytype;
	
	@Column(name="agencyid", insertable=false, updatable=false, nullable=false)
	private Integer agencyid;
	
	//bi-directional many-to-one association to Agency
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="agencyid")
	private Agency agency;
	
	@Column(name="billingid", insertable=false, updatable=false, nullable=false)
	private Integer billingid;

	//bi-directional many-to-one association to Companytobebilling
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="billingid")
	private Companytobebilling companytobebilling;

	@Column(name="processstateid", insertable=false, updatable=false, nullable=false)
	private Integer processstateid;
	
	//bi-directional many-to-one association to Processstate
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="processstateid")
	private Processstate processstate;
	
	@Column(name="workdepartmentid", insertable=false, updatable=false, nullable=false)
	private Integer workdepartmentid;

	//bi-directional many-to-one association to Workdepartment
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="workdepartmentid")
	private Workdepartment workdepartment;
	
	@Column(name="inventoryproductid", insertable=false, updatable=false, nullable=false)
	private Integer inventoryproductid;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="inventoryproductid")
	private Inventoryproduct inventoryproduct;
	
	//bi-directional many-to-one association to Product
	@JsonIgnore
	@OneToMany(mappedBy="assetrequest")
	private List<Product> products;

	//bi-directional many-to-one association to Proform
	@JsonIgnore
	@OneToMany(mappedBy="assetrequest")
	private List<Proform> proforms;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCasenumber() {
		return casenumber;
	}

	public void setCasenumber(String casenumber) {
		this.casenumber = casenumber;
	}

	public Timestamp getDateapproved() {
		return dateapproved;
	}

	public void setDateapproved(Timestamp dateapproved) {
		this.dateapproved = dateapproved;
	}

	public Timestamp getDateapprovedproform() {
		return dateapprovedproform;
	}

	public void setDateapprovedproform(Timestamp dateapprovedproform) {
		this.dateapprovedproform = dateapprovedproform;
	}

	public Timestamp getDateassigned() {
		return dateassigned;
	}

	public void setDateassigned(Timestamp dateassigned) {
		this.dateassigned = dateassigned;
	}

	public Timestamp getDatedelivered() {
		return datedelivered;
	}

	public void setDatedelivered(Timestamp datedelivered) {
		this.datedelivered = datedelivered;
	}

	public Timestamp getDatedenied() {
		return datedenied;
	}

	public void setDatedenied(Timestamp datedenied) {
		this.datedenied = datedenied;
	}

	public Timestamp getDatedeniedproform() {
		return datedeniedproform;
	}

	public void setDatedeniedproform(Timestamp datedeniedproform) {
		this.datedeniedproform = datedeniedproform;
	}

	public Timestamp getDateexited() {
		return dateexited;
	}

	public void setDateexited(Timestamp dateexited) {
		this.dateexited = dateexited;
	}

	public Timestamp getDatereceived() {
		return datereceived;
	}

	public void setDatereceived(Timestamp datereceived) {
		this.datereceived = datereceived;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFilerequest() {
		return filerequest;
	}

	public void setFilerequest(String filerequest) {
		this.filerequest = filerequest;
	}

	public Integer getUseridassigned() {
		return useridassigned;
	}

	public void setUseridassigned(Integer useridassigned) {
		this.useridassigned = useridassigned;
	}

	public String getSapcode() {
		return sapcode;
	}

	public void setSapcode(String sapcode) {
		this.sapcode = sapcode;
	}

	public Integer getUseridrequested() {
		return useridrequested;
	}

	public void setUseridrequested(Integer useridrequested) {
		this.useridrequested = useridrequested;
	}

	public Integer getAgencyid() {
		return agencyid;
	}

	public void setAgencyid(Integer agencyid) {
		this.agencyid = agencyid;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	public Integer getBillingid() {
		return billingid;
	}

	public void setBillingid(Integer billingid) {
		this.billingid = billingid;
	}

	public Companytobebilling getCompanytobebilling() {
		return companytobebilling;
	}

	public void setCompanytobebilling(Companytobebilling companytobebilling) {
		this.companytobebilling = companytobebilling;
	}

	public Integer getProcessstateid() {
		return processstateid;
	}

	public void setProcessstateid(Integer processstateid) {
		this.processstateid = processstateid;
	}

	public Processstate getProcessstate() {
		return processstate;
	}

	public void setProcessstate(Processstate processstate) {
		this.processstate = processstate;
	}

	public Integer getWorkdepartmentid() {
		return workdepartmentid;
	}

	public void setWorkdepartmentid(Integer workdepartmentid) {
		this.workdepartmentid = workdepartmentid;
	}

	public Workdepartment getWorkdepartment() {
		return workdepartment;
	}

	public void setWorkdepartment(Workdepartment workdepartment) {
		this.workdepartment = workdepartment;
	}
	
	public String getDeniedobservation() {
		return deniedobservation;
	}

	public void setDeniedobservation(String deniedobservation) {
		this.deniedobservation = deniedobservation;
	}

	public String getDeniedproformobservation() {
		return deniedproformobservation;
	}

	public void setDeniedproformobservation(String deniedproformobservation) {
		this.deniedproformobservation = deniedproformobservation;
	}

	public Integer getUseridapproved() {
		return useridapproved;
	}

	public void setUseridapproved(Integer useridapproved) {
		this.useridapproved = useridapproved;
	}

	public Integer getUseridapprovedproform() {
		return useridapprovedproform;
	}

	public void setUseridapprovedproform(Integer useridapprovedproform) {
		this.useridapprovedproform = useridapprovedproform;
	}

	public Integer getUseriddenied() {
		return useriddenied;
	}

	public void setUseriddenied(Integer useriddenied) {
		this.useriddenied = useriddenied;
	}

	public Integer getUseriddeniedproform() {
		return useriddeniedproform;
	}

	public void setUseriddeniedproform(Integer useriddeniedproform) {
		this.useriddeniedproform = useriddeniedproform;
	}

	public Integer getUseridexited() {
		return useridexited;
	}

	public void setUseridexited(Integer useridexited) {
		this.useridexited = useridexited;
	}

	public Integer getUseridreceived() {
		return useridreceived;
	}

	public void setUseridreceived(Integer useridreceived) {
		this.useridreceived = useridreceived;
	}

	public Timestamp getDateproform() {
		return dateproform;
	}

	public void setDateproform(Timestamp dateproform) {
		this.dateproform = dateproform;
	}

	public Integer getUseridproform() {
		return useridproform;
	}

	public void setUseridproform(Integer useridproform) {
		this.useridproform = useridproform;
	}

	public Integer getCatalogid() {
		return catalogid;
	}

	public void setCatalogid(Integer catalogid) {
		this.catalogid = catalogid;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	public Integer getPrioritytypeid() {
		return prioritytypeid;
	}

	public void setPrioritytypeid(Integer prioritytypeid) {
		this.prioritytypeid = prioritytypeid;
	}

	public Catalog getPrioritytype() {
		return prioritytype;
	}

	public void setPrioritytype(Catalog prioritytype) {
		this.prioritytype = prioritytype;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Proform> getProforms() {
		return proforms;
	}

	public void setProforms(List<Proform> proforms) {
		this.proforms = proforms;
	}

	public Timestamp getDateguide() {
		return dateguide;
	}

	public void setDateguide(Timestamp dateguide) {
		this.dateguide = dateguide;
	}

	public Timestamp getDateregister() {
		return dateregister;
	}

	public void setDateregister(Timestamp dateregister) {
		this.dateregister = dateregister;
	}

	public String getFilereferralguide() {
		return filereferralguide;
	}

	public void setFilereferralguide(String filereferralguide) {
		this.filereferralguide = filereferralguide;
	}

	public Integer getUseridguide() {
		return useridguide;
	}

	public void setUseridguide(Integer useridguide) {
		this.useridguide = useridguide;
	}

	public Integer getUseridregister() {
		return useridregister;
	}

	public void setUseridregister(Integer useridregister) {
		this.useridregister = useridregister;
	}

	public String getNumberguide() {
		return numberguide;
	}

	public void setNumberguide(String numberguide) {
		this.numberguide = numberguide;
	}

	public Integer getInventoryproductid() {
		return inventoryproductid;
	}

	public void setInventoryproductid(Integer inventoryproductid) {
		this.inventoryproductid = inventoryproductid;
	}

	public Inventoryproduct getInventoryproduct() {
		return inventoryproduct;
	}

	public void setInventoryproduct(Inventoryproduct inventoryproduct) {
		this.inventoryproduct = inventoryproduct;
	}

	public Timestamp getDatewarehouse() {
		return datewarehouse;
	}

	public void setDatewarehouse(Timestamp datewarehouse) {
		this.datewarehouse = datewarehouse;
	}

	public Integer getUseridwarehouse() {
		return useridwarehouse;
	}

	public void setUseridwarehouse(Integer useridwarehouse) {
		this.useridwarehouse = useridwarehouse;
	}

	public Timestamp getDatemaintenance() {
		return datemaintenance;
	}

	public void setDatemaintenance(Timestamp datemaintenance) {
		this.datemaintenance = datemaintenance;
	}

	public Integer getUseridmaintenance() {
		return useridmaintenance;
	}

	public void setUseridmaintenance(Integer useridmaintenance) {
		this.useridmaintenance = useridmaintenance;
	}

	public String getMaintenanceobservation() {
		return maintenanceobservation;
	}

	public void setMaintenanceobservation(String maintenanceobservation) {
		this.maintenanceobservation = maintenanceobservation;
	}

	public String getDownobservation() {
		return downobservation;
	}

	public void setDownobservation(String downobservation) {
		this.downobservation = downobservation;
	}

	public Integer getUseriddown() {
		return useriddown;
	}

	public void setUseriddown(Integer useriddown) {
		this.useriddown = useriddown;
	}

	public Timestamp getDatedown() {
		return datedown;
	}

	public void setDatedown(Timestamp datedown) {
		this.datedown = datedown;
	}

	public Timestamp getDatedevolution() {
		return datedevolution;
	}

	public void setDatedevolution(Timestamp datedevolution) {
		this.datedevolution = datedevolution;
	}

	public Integer getUseriddevolution() {
		return useriddevolution;
	}

	public void setUseriddevolution(Integer useriddevolution) {
		this.useriddevolution = useriddevolution;
	}
	
}
