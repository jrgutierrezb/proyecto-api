package com.proyecto.retail.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
public class User extends BaseEntity implements UserDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String cellphone;
	
	@JsonIgnore
	private boolean enabled = true;

	private String firstname;

	private String identification;
	
	@JsonIgnore
	public boolean istemporalpassword = true;

	private String lastname;

	private String mail;

	@JsonIgnore
	 private String password;

	private String telephone;

	private String username;
	
	@Column(name="agencyid", insertable=false, updatable=false, nullable=false)
	private Integer agencyid;

	public Integer getAgencyid() {
		return agencyid;
	}

	public void setAgencyid(Integer agencyid) {
		this.agencyid = agencyid;
	}
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="agencyid")
	private Agency agency;
	
	
	@Column(name="profileid", insertable=false, updatable=false, nullable=false)
	private Integer profileid;

	public Integer getProfileid() {
		return profileid;
	}

	public void setProfileid(Integer profileid) {
		this.profileid = profileid;
	}

	//bi-directional many-to-one association to Profile
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="profileid")
	private Profile profile;
	
	@Column(name="companyid", insertable=false, updatable=false, nullable=false)
	private Integer companyid;
	
	//bi-directional many-to-one association to Companytobebilling
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="companyid")
	private Companytobebilling companytobebilling;
	
	@Column(name="workdepartmentid", insertable=false, updatable=false, nullable=false)
	private Integer workdepartmentid;
	
	//bi-directional many-to-one association to Workdepartment
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="workdepartmentid")
	private Workdepartment workdepartment;

	public User() {
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.enabled;
	}

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getIdentification() {
		return this.identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public boolean isIstemporalpassword() {
		return istemporalpassword;
	}

	public void setIstemporalpassword(boolean istemporalpassword) {
		this.istemporalpassword = istemporalpassword;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		Set<Authority> autoridades = new HashSet<>();
        autoridades.add(new Authority(this.profile.getName()));
        return autoridades;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public Integer getCompanyid() {
		return companyid;
	}

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

	public Companytobebilling getCompanytobebilling() {
		return companytobebilling;
	}

	public void setCompanytobebilling(Companytobebilling companytobebilling) {
		this.companytobebilling = companytobebilling;
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

}