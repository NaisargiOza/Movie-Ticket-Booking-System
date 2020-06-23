package com.ticketbooking.bookticket.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ticketbooking.bookticket.model.Seat;
import com.ticketbooking.bookticket.service.SeatService;

@RestController
public class PaymentController {
   
	private Logger logger=LoggerFactory.getLogger(AuthenticationController.class);
	
	//@Autowired
	//Seat s;
	
	@Autowired
	SeatService seatService;
	
	@RequestMapping(value = "/pay", method = RequestMethod.GET)
	public @ResponseBody ModelAndView payment(@Valid @ModelAttribute Seat l,ModelAndView modelAndView) {
		//ModelAndView modelAndView=new ModelAndView();
		//seatService.setchange(l);
		int num=3;
		int p=num*120;
		logger.info("in pay method");
		modelAndView.addObject("total",num);
		modelAndView.addObject("price",p);
		modelAndView.setViewName("pay");
		return modelAndView;
	}
}
