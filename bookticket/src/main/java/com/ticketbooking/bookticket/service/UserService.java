package com.ticketbooking.bookticket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketbooking.bookticket.model.User;
import com.ticketbooking.bookticket.repository.UserRepository;

@Service
public interface UserService {
	
	public void saveUser(User user);
		
	public boolean isUserAlreadyPresent(User user);
	
	public String getEmail(User user);
	
}
