package com.proyecto.retail.Infraestructura;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.proyecto.retail.model.Mailsetting;
import com.proyecto.retail.model.Messagebox;
import com.proyecto.retail.model.Messageparameter;
import com.proyecto.retail.model.Messagetemplate;
import com.proyecto.retail.service.EmailService;
import com.proyecto.retail.service.MailsettingServiceImp;
import com.proyecto.retail.service.MessageboxServiceImp;

@Component
public class ScheduledTasks {
	
	@Autowired
	private MessageboxServiceImp messageboxServiceImp;
	
	@Autowired
	private MailsettingServiceImp mailsettingServiceImp;

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(initialDelayString="${task.initialDelay}", fixedDelayString = "${task.fixedDelay}")
	public void EmailTask() {
		try {
			List<Mailsetting> mailsettings = this.mailsettingServiceImp.fetchMailSettingList();
			if(mailsettings.isEmpty() ) {
				return;
			}
			Mailsetting mailsetting = mailsettings.get(0);
			MailConfig mailConfig = new MailConfig(mailsetting);
			
			List<Messagebox> messageBoxes = this.messageboxServiceImp.findBySend(false);
			ExecutorService executor;
			List<EmailService> myCallables;
			if(!messageBoxes.isEmpty()) {
		        executor = Executors.newFixedThreadPool(5);
		        myCallables = new ArrayList<>();
			}
			else
				return;
			
			for(Messagebox message: messageBoxes) {
				String cuerpo;
				Messagetemplate messagetemplate = message.getMessagetemplate();
				List<Messageparameter> messageparameter = message.getMessageparameters();
				messageparameter.stream().sorted(Comparator.comparing(Messageparameter::getId)).collect(Collectors.toList());
				cuerpo = messagetemplate.getMessage();
				for(int i = 0; i < messageparameter.size(); i++) {
					cuerpo = cuerpo.replaceFirst( "@param", messageparameter.get(i).getValue());
				}
				myCallables.add(new EmailService(mailsetting, message, mailConfig.getSession(), cuerpo));
				message = this.messageboxServiceImp.updateMessagebox(message, message.getId());
			}
			executor.invokeAll(myCallables);
			executor.shutdown();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
