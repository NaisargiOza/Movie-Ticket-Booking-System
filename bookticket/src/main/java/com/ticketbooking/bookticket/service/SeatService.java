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
	
	public void setchange(Seat l)
	{ l.setAvailable(false);
	}
	
	public void insert(String[] ids,int tid)
	{ for(String l:ids)
	  { Seat s=seatRepository.findByIdAndTid(l,tid);
	    s.setAvailable(false);
	    seatRepository.save(s);
	  }	
	}
}
