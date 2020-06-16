package com.ticketbooking.bookticket.service;

import com.ticketbooking.bookticket.model.User;

public interface UserService {
	
	public void saveUser(User user);
		
	public boolean isUserAlreadyPresent(User user);
	
	//public String getCity();
	
}
