package com.ticketbooking.bookticket.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "seat")
public class Seat {
   
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "t_id")
	private int tid;
	
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
	
	public int getTid() {
		return tid;
	}
	
	public void setTid(int tid) {
		this.tid = tid;
	}
	
	public Seat() {
		
	}

	public Seat(String id, int tid, Boolean available) {
		this.id = id;
		this.tid = tid;
		this.available = available;
	}
    
	
}