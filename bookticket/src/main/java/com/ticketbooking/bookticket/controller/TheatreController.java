package com.ticketbooking.bookticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ticketbooking.bookticket.service.TheatreService;

@Controller
public class TheatreController {
    
	@Autowired 
	TheatreService theatreService;
	
	private String city;
	private String m_name;
	
	
	public String getCity() {
		return city;
	}

	public String getM_name() {
		return m_name;
	}

	@RequestMapping(value = "/place" , method=RequestMethod.GET)
	public ModelAndView home() {
	  ModelAndView modelAndView=new ModelAndView();
	  //city=c;
	  modelAndView.addObject("city",city);
	  modelAndView.setViewName("place");
	  return modelAndView;
	}
	
	@RequestMapping(value = "/movies", method = RequestMethod.POST)
	public ModelAndView movies(@RequestParam("city") String c,ModelAndView modelAndView) {
		//ModelAndView modelAndView=new ModelAndView();
		city=c;
		modelAndView.addObject("movies",theatreService.getMovies(city));
		modelAndView.setViewName("movies");
		return modelAndView;
	}
	
	@RequestMapping(value = "/theatres", method = RequestMethod.POST)
	public ModelAndView theatres(@RequestParam("m_name") String m,ModelAndView modelAndView) {
	   m_name=m;
	   modelAndView.addObject("m_name",theatreService.getTheatres(m_name, city));
	   modelAndView.setViewName("theatres");
	   return modelAndView;	
	}
}
