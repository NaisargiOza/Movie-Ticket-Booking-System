package com.ticketbooking.bookticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.ticketbooking.bookticket.service.SeatService;

@Controller
public class SeatController {
    
	@Autowired
	SeatService seatService;
	
	private String theatre_name;
	private int num;
	
	public String getTheatre_name() {
		return theatre_name;
	}

	public int getNum() {
		return num;
	}

	@RequestMapping(value="/seats",method=RequestMethod.POST)
	public synchronized ModelAndView seats(@RequestParam("num") int n,ModelAndView modelAndView) {
		num=n;
		modelAndView.setViewName("seats");
		return modelAndView;
	}
	
	@RequestMapping(value = "/outp", method = RequestMethod.POST)
	public ModelAndView outp(@RequestParam("theater") String t,ModelAndView modelAndView) {
	   theatre_name=t;
	   modelAndView.addObject("num",num);
	   modelAndView.setViewName("outp");
	   return modelAndView;	
	}
}
