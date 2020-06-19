package com.ticketbooking.bookticket.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "bookseat")
public class Seat {
   
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "t_id")
	private int t_id;
	
	@Column(name = "available")
	private Boolean available;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Boolean getAvailable() {
		return available;
	}
	
	public void setAvailable(Boolean available) {
		this.available = available;
	}
	
	public int getT_id() {
		return t_id;
	}
	
	public void setT_id(int t_id) {
		this.t_id = t_id;
	}
	
	public Seat() {
		
	}

	public Seat(String id, int t_id, Boolean available) {
		super();
		this.id = id;
		this.t_id = t_id;
		this.available = available;
	}
    
	
}