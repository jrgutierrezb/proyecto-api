package com.proyecto.retail.service;

import java.util.concurrent.Callable;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.retail.model.Mailsetting;
import com.proyecto.retail.model.Messagebox;

public class EmailService implements Callable<Boolean> {

	
	private Mailsetting mailSetting;
	private Messagebox messageBox;
	private Session session;
	private String cuerpo;
	
	public EmailService(Mailsetting mailSetting, Messagebox messageBox, Session session, String cuerpo) {
		this.mailSetting = mailSetting;
		this.messageBox = messageBox;
		this.session = session;
		this.cuerpo = cuerpo;
	}

	@Override
	public Boolean call() {
		return send(messageBox.getTorecipients(), messageBox.getSubject());
		
	}
	
	private Boolean send(String destinatario, String asunto) {
	    MimeMessage message = new MimeMessage(session);

	    try 
	    {
	        message.setFrom(new InternetAddress(mailSetting.getUsermail()));
	        message.addRecipients(Message.RecipientType.TO, destinatario);   //Se podrían añadir varios de la misma manera
	        message.setSubject(asunto);
	        message.setText(cuerpo,"utf-8", "html");
	        Transport transport = session.getTransport("smtp");
	        transport.connect(mailSetting.getHost(), mailSetting.getUsermail(), mailSetting.getPass());
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	        
	        return true;
	    }
	    catch (MessagingException me) {
	        me.printStackTrace();   //Si se produce un error
	        return false;
	    }
	}

}
