package com.ticketbooking.bookticket.service;


import java.util.Arrays;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ticketbooking.bookticket.model.Role;
import com.ticketbooking.bookticket.model.User;
import com.ticketbooking.bookticket.repository.RoleRepository;
import com.ticketbooking.bookticket.repository.UserRepository;

@Service("userService")
public class UserServiceImp implements UserService {
	
	@Autowired
	BCryptPasswordEncoder encoder;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;
	//@Autowired
	//User user;

	@Override
	public void saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setStatus("VERIFIED");
		Role userRole = roleRepository.findByRole("SITE_USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public boolean isUserAlreadyPresent(User user) {
		boolean isUserAlreadyExists = false;
		 User existingUser = userRepository.findByEmail(user.getEmail());
		 if(existingUser != null){
		 isUserAlreadyExists = true; 
		 }
		 return isUserAlreadyExists;
	}
    
	@Override
	public String getEmail(User user) {
		return user.getEmail();
	}
}