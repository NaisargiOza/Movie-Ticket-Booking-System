package com.ticketbooking.bookticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketbooking.bookticket.model.Seat;
import com.ticketbooking.bookticket.repository.SeatRepository;

@Service
public class SeatService {
   
	@Autowired
	private SeatRepository seatRepository;
	
	public void setValue(List<Seat> l)
	{ for(Seat s:l)
	  { s.setAvailable(false);
	    seatRepository.save(s);
	  }
	}
}
