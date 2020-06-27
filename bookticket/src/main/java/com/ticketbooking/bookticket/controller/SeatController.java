package com.ticketbooking.bookticket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.ticketbooking.bookticket.service.SeatService;
import com.ticketbooking.bookticket.service.TheatreService;

@Controller
public class SeatController {
    
	private Logger logger=LoggerFactory.getLogger(AuthenticationController.class);
	
	@Autowired 
	TheatreService theatreService;
	
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
	public synchronized ModelAndView seats(@RequestParam(value="num",required=false) Integer n,ModelAndView modelAndView) {
		try {
		num=n;
		logger.info("in seats method");	
		modelAndView.setViewName("seats");
		}catch(Exception e) { 
			 logger.info("Error in seat method: "+e.getMessage());		
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/outp", method = RequestMethod.POST)
	public ModelAndView outp(@RequestParam("theater") String t,ModelAndView modelAndView) {
	   theatre_name=t;
	   modelAndView.addObject("num",num);
	   modelAndView.setViewName("outp");
	   return modelAndView;	
	}
	
	@RequestMapping(value = "/confirm", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody int confirm(@RequestBody String[] ids) {
		logger.info("in confirm method 1");
		int tid=theatreService.getTID(theatre_name);
		seatService.insert(ids,tid);
		logger.info("in confirm method 2");
	    return ids.length;
	}
}

