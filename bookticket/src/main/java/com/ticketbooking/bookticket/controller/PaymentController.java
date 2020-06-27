package com.ticketbooking.bookticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PaymentController {
    
	@Autowired
	SeatController seatController;
	
	@RequestMapping(value = "/pay", method = RequestMethod.GET)
	public ModelAndView payment(@RequestParam(value="t",required=false) String t,ModelAndView modelAndView) {
		//ModelAndView modelAndView=new ModelAndView();
		int p=seatController.getNum()*120;
		modelAndView.addObject("total",seatController.getNum());
		modelAndView.addObject("price",p);
		modelAndView.setViewName("pay");
		return modelAndView;
	}
	
	@RequestMapping(value = "/gate", method =  RequestMethod.GET)
	public ModelAndView gateway() {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("gate");
        return modelAndView;
	}
}