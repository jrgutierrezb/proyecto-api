package com.proyecto.retail.Infraestructura;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.proyecto.retail.model.Mailsetting;


public class MailConfig {
	
	private Properties props;
	private Session session;
	
	
	public MailConfig(Mailsetting mailSetting) {
		config(mailSetting);
	}
	
	public Session getSession() {
		return session;
	}

	private void config(Mailsetting mailSetting) {
		// Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.

	    props = System.getProperties();
	    props.put("mail.smtp.host", mailSetting.getHost());  //El servidor SMTP de Google
	    props.put("mail.smtp.user", mailSetting.getUsermail());
	    props.put("mail.smtp.clave", mailSetting.getPass());    //La clave de la cuenta
	    props.put("mail.smtp.auth", mailSetting.getAuth());    //Usar autenticación mediante usuario y clave
	    props.put("mail.smtp.starttls.enable", mailSetting.getStarttls()); //Para conectar de manera segura al servidor SMTP
	    props.put("mail.smtp.port", mailSetting.getPort()); //El puerto SMTP seguro de Google
	    
	    session = Session.getDefaultInstance(props);
	}
}
