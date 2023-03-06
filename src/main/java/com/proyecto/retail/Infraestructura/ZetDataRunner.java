package com.proyecto.retail.Infraestructura;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.proyecto.retail.model.Initialconfig;
import com.proyecto.retail.model.Mailsetting;
import com.proyecto.retail.model.Messagetemplate;
import com.proyecto.retail.model.Module;
import com.proyecto.retail.model.Profile;
import com.proyecto.retail.model.Profiletransaction;
import com.proyecto.retail.model.Transaction;
import com.proyecto.retail.service.InitialConfigServiceImp;
import com.proyecto.retail.service.MailsettingServiceImp;
import com.proyecto.retail.service.MessageTemplateServiceImp;
import com.proyecto.retail.service.ModuleServiceImp;
import com.proyecto.retail.service.ProfileServiceImp;
import com.proyecto.retail.service.ProfileTransactionServiceImp;
import com.proyecto.retail.service.TransactionServiceImp;

@Component
public class ZetDataRunner implements CommandLineRunner {
	
	@Autowired
	private MailsettingServiceImp mailSettingServiceImp;
	@Autowired
	private MessageTemplateServiceImp messageTemplateServiceImp;
	@Autowired
	private InitialConfigServiceImp initialConfigServiceImp;
	@Autowired
	private ModuleServiceImp moduleServiceImp;
	@Autowired
	private ProfileServiceImp profileServiceImp;
	@Autowired
	private TransactionServiceImp transactionServiceImp;
	@Autowired
	private ProfileTransactionServiceImp profileTransactionServiceImp;

	@Override
	public void run(String... args) throws Exception {
		this.SetterMailSettings();
		this.SetterMessageTemplates();
		this.SetterInitialConfigs();
		this.SetterProfiles();
		this.SetterModules();
		this.SetterTransactions();
		this.SetterProfileTransactions();
	}
	
	private void SetterMailSettings() {
		Mailsetting mailsetting = this.mailSettingServiceImp.findByStateTrueAndUsermail("jhon.gutierrez.briones95@gmail.com");
		if(mailsetting == null) {
			mailsetting = new Mailsetting();
			mailsetting.setHost("smtp.gmail.com");
			mailsetting.setUsermail("jhon.gutierrez.briones95@gmail.com");
			mailsetting.setPass("lportfhrdfyvzuhp");
			mailsetting.setAuth("true");
			mailsetting.setStarttls("true");
			mailsetting.setPort("587");
			this.mailSettingServiceImp.saveMailSetting(mailsetting);
		}
	}

	private void SetterMessageTemplates() {
		Messagetemplate messageTemplate = this.messageTemplateServiceImp.findByName("REGISTRA_USUARIO");
		if(messageTemplate == null) {
			messageTemplate = new Messagetemplate();
			messageTemplate.setName("REGISTRA_USUARIO");
			messageTemplate.setDescription("plantilla de registro de usuarios");
			messageTemplate.setMessage("Hola @param, \r\n" + "	Su nombre de usuario es @param se le genero la clave temporal <strong>@param</strong>");
			messageTemplate.setParameternumber(3);
			this.messageTemplateServiceImp.saveMessageTemplate(messageTemplate);
		}
		
		messageTemplate = this.messageTemplateServiceImp.findByName("CLAVE_TEMPORAL");
		if(messageTemplate == null) {
			messageTemplate = new Messagetemplate();
			messageTemplate.setName("CLAVE_TEMPORAL");
			messageTemplate.setDescription("plantilla de clave temporal de usuarios");
			messageTemplate.setMessage("Hola @param, \r\n" + "	Se genero la clave temporal <strong>@param</strong>");
			messageTemplate.setParameternumber(2);
			this.messageTemplateServiceImp.saveMessageTemplate(messageTemplate);
		}
	}
	
	private void SetterInitialConfigs() {
		Initialconfig initialConfig = this.initialConfigServiceImp.findByStateTrueAndName("CONECTA_SAP");
		if(initialConfig == null) {
			initialConfig = new Initialconfig();
			initialConfig.setName("CONECTA_SAP");
			initialConfig.setDescription("Parametro que establece si se debe conectar a SAP");
			initialConfig.setBooleanvalue(false);
			this.initialConfigServiceImp.saveInitialConfig(initialConfig);
		}
		
		initialConfig = this.initialConfigServiceImp.findByStateTrueAndName("ENDPOINT_SAP_LOGIN");
		if(initialConfig == null) {
			initialConfig = new Initialconfig();
			initialConfig.setName("ENDPOINT_SAP_LOGIN");
			initialConfig.setDescription("Parametro url que autentica las credenciales con SAP");
			initialConfig.setStringvalue("http://hostname:port/api/autenticasap");
			this.initialConfigServiceImp.saveInitialConfig(initialConfig);
		}
	}
	
	private void SetterProfiles() {
		List<Profile> profiles = this.profileServiceImp.findByName("admin");
		Profile profile;
		if(profiles == null || profiles.isEmpty()) {
			profile = new Profile();
			profile.setName("admin");
			profile.setDescription("Perfil Administrador");
			this.profileServiceImp.saveProfile(profile);
		}
		
		profiles = this.profileServiceImp.findByName("SOLICITANTE");
		if(profiles == null || profiles.isEmpty()) {
			profile = new Profile();
			profile.setName("SOLICITANTE");
			profile.setDescription("Usuario Solicitante");
			this.profileServiceImp.saveProfile(profile);
		}
		
		profiles = this.profileServiceImp.findByName("SOPORTE");
		if(profiles == null || profiles.isEmpty()) {
			profile = new Profile();
			profile.setName("SOPORTE");
			profile.setDescription("Usuario Soporte");
			this.profileServiceImp.saveProfile(profile);
		}
		
		profiles = this.profileServiceImp.findByName("VENTAS");
		if(profiles == null || profiles.isEmpty()) {
			profile = new Profile();
			profile.setName("VENTAS");
			profile.setDescription("Usuario Ventas");
			this.profileServiceImp.saveProfile(profile);
		}
		
		profiles = this.profileServiceImp.findByName("GERENTE");
		if(profiles == null || profiles.isEmpty()) {
			profile = new Profile();
			profile.setName("GERENTE");
			profile.setDescription("Usuario Gerente");
			this.profileServiceImp.saveProfile(profile);
		}
		
		profiles = this.profileServiceImp.findByName("CDI");
		if(profiles == null || profiles.isEmpty()) {
			profile = new Profile();
			profile.setName("CDI");
			profile.setDescription("Usuario Centro de distibución");
			this.profileServiceImp.saveProfile(profile);
		}
		
		profiles = this.profileServiceImp.findByName("BODEGA");
		if(profiles == null || profiles.isEmpty()) {
			profile = new Profile();
			profile.setName("BODEGA");
			profile.setDescription("Usuario bodega");
			this.profileServiceImp.saveProfile(profile);
		}
	}
	
	private void SetterModules() {
		List<Module> modules = this.moduleServiceImp.findByName("Administración");
		Module module;
		if(modules == null || modules.isEmpty()) {
			module = new Module();
			module.setName("Administración");
			module.setDescription("Modulo de administración");
			module.setIcon("");
			this.moduleServiceImp.SaveModule(module);
		}
		
		modules = this.moduleServiceImp.findByName("Gestion Activos");
		if(modules == null || modules.isEmpty()) {
			module = new Module();
			module.setName("Gestion Activos");
			module.setDescription("Modulo de gestion de activos");
			module.setIcon("cil-settings");
			module.setUrl("/admin");
			this.moduleServiceImp.SaveModule(module);
		}
	}

	private void SetterTransactions() {
		List<Module> modules = this.moduleServiceImp.findByName("Administración");
		Module module = null;
		List<Transaction> transactions;
		Transaction transaction;
		if(modules != null && !modules.isEmpty()) {
			module = modules.get(0);
		}
		
		if(module != null ) {
			transactions = this.transactionServiceImp.findByName("Usuarios");
			if(transactions == null || transactions.isEmpty()) {
				transaction = new Transaction();
				transaction.setName("Usuarios");
				transaction.setDescription("Mantenimiento de usuarios");
				transaction.setUrl("/users");
				transaction.setModule(module);
				this.transactionServiceImp.SaveTransaction(transaction);
			}
			
			transactions = this.transactionServiceImp.findByName("Configuracion mail");
			if(transactions == null || transactions.isEmpty()) {
				transaction = new Transaction();
				transaction.setName("Configuracion mail");
				transaction.setDescription("Mantenimiento de configuracion mail");
				transaction.setUrl("/mailsettings");
				transaction.setModule(module);
				this.transactionServiceImp.SaveTransaction(transaction);
			}
			
			transactions = this.transactionServiceImp.findByName("Modulos");
			if(transactions == null || transactions.isEmpty()) {
				transaction = new Transaction();
				transaction.setName("Modulos");
				transaction.setDescription("Mantenimiento de modulos");
				transaction.setUrl("/modules");
				transaction.setModule(module);
				this.transactionServiceImp.SaveTransaction(transaction);
			}
			
			transactions = this.transactionServiceImp.findByName("Pantallas");
			if(transactions == null || transactions.isEmpty()) {
				transaction = new Transaction();
				transaction.setName("Pantallas");
				transaction.setDescription("Mantenimiento de pantallas");
				transaction.setUrl("/transactions");
				transaction.setModule(module);
				this.transactionServiceImp.SaveTransaction(transaction);
			}
			
			transactions = this.transactionServiceImp.findByName("Permisos Perfiles");
			if(transactions == null || transactions.isEmpty()) {
				transaction = new Transaction();
				transaction.setName("Permisos Perfiles");
				transaction.setDescription("Mantenimiento para asignar permisos a los perfiles");
				transaction.setUrl("/profilepermission");
				transaction.setModule(module);
				this.transactionServiceImp.SaveTransaction(transaction);
			}
		}
		
		modules = this.moduleServiceImp.findByName("Gestion Activos");
		module = null;
		if(modules != null && !modules.isEmpty()) {
			module = modules.get(0);
		}
		
		if(module != null ) {
			transactions = this.transactionServiceImp.findByName("Solicitudes");
			if(transactions == null || transactions.isEmpty()) {
				transaction = new Transaction();
				transaction.setName("Solicitudes");
				transaction.setDescription("Solicitudes de los activos tecnologicos");
				transaction.setUrl("/solicitudes");
				transaction.setModule(module);
				this.transactionServiceImp.SaveTransaction(transaction);
			}
		}
	}

	private void SetterProfileTransactions() {
		this.SetterValidateProfileTransaction("admin", "Usuarios");
		this.SetterValidateProfileTransaction("admin", "Configuracion mail");
		this.SetterValidateProfileTransaction("admin", "Modulos");
		this.SetterValidateProfileTransaction("admin", "Pantallas");
		this.SetterValidateProfileTransaction("admin", "Permisos Perfiles");
		this.SetterValidateProfileTransaction("admin", "Solicitudes");
	}
	
	private void SetterValidateProfileTransaction(String profileName, String transactionName) {
		List<Profile> profiles = this.profileServiceImp.findByName(profileName);
		Profile profile = null;
		List<Transaction> transactions = null;
		Transaction transaction = null;
		List<Profiletransaction> profiletransactions = null;
		Profiletransaction profiletransactionpermission;
		if(profiles == null || profiles.isEmpty()) {
			return;
		}
		
		
		transactions = this.transactionServiceImp.findByName(transactionName);
		if(transactions == null || transactions.isEmpty()) {
			return;
		}
		
		profile = profiles.get(0);
		transaction = transactions.get(0);
		
		profiletransactions = this.profileTransactionServiceImp.findByProfileidAndTransactionid(profile.getId(), transaction.getId());
					
		if(profiletransactions == null || profiletransactions.isEmpty()) {
			profiletransactionpermission = new Profiletransaction();
			profiletransactionpermission.setProfile(profile);
			profiletransactionpermission.setTransaction(transaction);
			this.profileTransactionServiceImp.saveProfiletransaction(profiletransactionpermission);
		}
	}
	
}
