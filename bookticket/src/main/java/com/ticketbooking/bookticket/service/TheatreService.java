package com.ticketbooking.bookticket.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketbooking.bookticket.model.Theatre;
import com.ticketbooking.bookticket.repository.TheatreRepository;

@Service
public class TheatreService {
    
	@Autowired
	private TheatreRepository theatreRepository;
	
	public List<String> getMovies(String str) {
		List<Theatre> l=new ArrayList<Theatre>();
		l=theatreRepository.findByCity(str);
		List<String> names=new ArrayList<String>();
	    for(Theatre t:l)
	     names.add(t.getMovie_name());
	    return names;
	}
	
	public List<String> getTheatres(String m,String c) {
		List<Theatre> l=new ArrayList<Theatre>();
		l=theatreRepository.findByCity(c);
		List<String> names=new ArrayList<String>();
		for(Theatre t:l)
		{ if(t.getMovie_name().equals(m)==true)
		  { String str="Theater name: ";
		    str+=t.getTheatre_name();
		    str+="    Seats Available: ";
		    str+=t.getAvailable();
		    str+="    ";
		    str+="Show timings: ";
		    str+=t.getShow_tim();
			names.add(str);
		  }
		}
		return names;
	}
	
	public int getTID(String name) {
		Theatre t=theatreRepository.findByTheatrename(name);
		return t.getTheatre_id();
	}
}
