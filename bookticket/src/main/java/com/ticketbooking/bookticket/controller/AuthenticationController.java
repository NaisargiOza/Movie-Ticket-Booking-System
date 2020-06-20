package com.ticketbooking.bookticket.controller;

import java.util.List;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ticketbooking.bookticket.model.Seat;
import com.ticketbooking.bookticket.model.User;
import com.ticketbooking.bookticket.service.SeatService;
import com.ticketbooking.bookticket.service.TheatreService;
import com.ticketbooking.bookticket.service.UserService;

@Controller
public class AuthenticationController {
    
	@Autowired
	UserService userService;
	
	@Autowired 
	TheatreService theatreService;
	
	@Autowired
	SeatService seatService;
	
	String city;
	String m_name;
	String theatre_name;
	int num;
	List<Seat> ids;
	Seat s;
	
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login"); // resources/template/login.html
		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user); 
		modelAndView.setViewName("register"); // resources/template/register.html
		return modelAndView;
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
		modelAndView.setViewName("movies"); // resources/template/home.html
		return modelAndView;
	}
	
	@RequestMapping(value = "/theatres", method = RequestMethod.POST)
	public ModelAndView theatres(@RequestParam("m_name") String m,ModelAndView modelAndView) {
	   m_name=m;
	   modelAndView.addObject("m_name",theatreService.getTheatres(m_name, city));
	   modelAndView.setViewName("theatres");
	   return modelAndView;	
	}
	
	@RequestMapping(value="/seats",method=RequestMethod.POST)
	public synchronized ModelAndView seats(@RequestParam("seat") Seat seat,ModelAndView modelAndView) {
		s=seat;
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
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView registerUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		if(bindingResult.hasErrors()) {
			modelAndView.addObject("successMessage", "Please correct the errors in form!");
			modelMap.addAttribute("bindingResult", bindingResult);
		}
		else if(userService.isUserAlreadyPresent(user)){
			modelAndView.addObject("successMessage", "user already exists!");			
		}
		else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User is registered successfully!Login to proceed");
		}
		modelAndView.addObject("user", new User());
		modelAndView.setViewName("register");
		return modelAndView;
	}
}
