package com.ticketbooking.bookticket.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.MailException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.ticketbooking.bookticket.model.Seat;
import com.ticketbooking.bookticket.model.User;
import com.ticketbooking.bookticket.service.NotificationService;
import com.ticketbooking.bookticket.service.SeatService;
import com.ticketbooking.bookticket.service.TheatreService;
import com.ticketbooking.bookticket.service.UserService;

@RestController
public class AuthenticationController {
    
	private Logger logger=LoggerFactory.getLogger(AuthenticationController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired 
	TheatreService theatreService;
	
	@Autowired
	NotificationService notificationService;
	
	@Autowired
	SeatService seatService;
	
	String city;
	String m_name;
	String theatre_name;
	int num;
	String email;
	List<Seat> ids;
	
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
	   logger.info("in outp method");	
	   modelAndView.addObject("num",num);
	   modelAndView.setViewName("outp");
	   return modelAndView;	
	}
	
	@RequestMapping(value = "/pay", method = RequestMethod.GET)
	public ModelAndView payment(@RequestParam(value="t",required=false) String t,ModelAndView modelAndView) {
		//ModelAndView modelAndView=new ModelAndView();
		logger.info("in payment method");
		int p=num*120;
		modelAndView.addObject("total",num);
		modelAndView.addObject("price",p);
		modelAndView.setViewName("pay");
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
	
	@RequestMapping(value = "/gate", method =  RequestMethod.GET)
	public ModelAndView gateway() {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("gate");
        return modelAndView;
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
			notificationService.sendNotification(email,num,m_name,theatre_name);
		}catch(MailException g) {
		  logger.info("Error sending mail: "+g.getMessage());	
		}
		modelAndView.setViewName("notif"); // resources/template/home.html
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