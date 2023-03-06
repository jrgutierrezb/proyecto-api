package com.proyecto.retail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.retail.model.Mailsetting;

public interface MailsettingRepository extends JpaRepository<Mailsetting, Integer> {
	List<Mailsetting> findByStateTrue();
	List<Mailsetting> findByStateTrueAndUsermail(String usermMail);
}
