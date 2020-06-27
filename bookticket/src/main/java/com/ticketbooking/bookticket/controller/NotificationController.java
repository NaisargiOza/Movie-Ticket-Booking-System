package com.ticketbooking.bookticket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ticketbooking.bookticket.service.NotificationService;

@Controller
public class NotificationController {
    
	@Autowired
	NotificationService notificationService;
	
	@Autowired
	SeatController seatController;
	
	@Autowired
	TheatreController theatreController;
	
	private Logger logger=LoggerFactory.getLogger(AuthenticationController.class);
	
	private String email;
	
	public String getEmail() {
		return email;
	}

	@RequestMapping(value = "/mail", method = RequestMethod.GET)
	public ModelAndView mail() {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("email",email);
		modelAndView.setViewName("mail");
		return modelAndView;
	}

	@RequestMapping(value = "/notif", method = RequestMethod.POST)
	public ModelAndView notif(@RequestParam("email") String e,ModelAndView modelAndView) {
		//ModelAndView modelAndView=new ModelAndView();
		email=e;
		try {
			notificationService.sendNotification(email,seatController.getNum(),theatreController.getM_name(),seatController.getTheatre_name());
		}catch(MailException g) {
		  logger.info("Error sending mail: "+g.getMessage());	
		}
		modelAndView.setViewName("notif"); // resources/template/home.html
		return modelAndView;
	}
}
