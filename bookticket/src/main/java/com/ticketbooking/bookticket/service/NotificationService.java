package com.ticketbooking.bookticket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ticketbooking.bookticket.model.User;

@Service
public class NotificationService {

	private JavaMailSender javaMailSender;
	
	@Autowired
	public NotificationService(JavaMailSender javaMailSender) {
		this.javaMailSender=javaMailSender;
	}
	
	public void sendNotification(String m,int n,String m_name,String t_name) throws MailException{
		SimpleMailMessage mail=new SimpleMailMessage();
		mail.setTo(m);
		mail.setFrom("naisargioza24@gmail.com");
		mail.setSubject("Booking confirmed");
		mail.setText("Thank you for booking with us.Your booking for "+n+" seats has been confirmed in "+t_name+" for movie "+m_name+".");
		javaMailSender.send(mail);
	}
}
