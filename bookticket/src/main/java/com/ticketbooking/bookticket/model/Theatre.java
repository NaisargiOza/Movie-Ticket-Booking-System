package com.ticketbooking.bookticket.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "theatre")
public class Theatre {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "theatre_id")
	private int theatre_id;
	
	@Column(name = "theatre_name")
    private String theatre_name;
	
	@Column(name = "city")
    private String city;
	
	@Column(name = "movie_name")
    private String movie_name;
	
	@Column(name = "seats")
    private int seats;
	
	@Column(name = "available")
    private int available;
	
	@Column(name = "show_tim")
	private String show_tim;

	public int getTheatre_id() {
		return theatre_id;
	}

	public void setTheatre_id(int theatre_id) {
		this.theatre_id = theatre_id;
	}

	public String getTheatre_name() {
		return theatre_name;
	}

	public void setTheatre_name(String theatre_name) {
		this.theatre_name = theatre_name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMovie_name() {
		return movie_name;
	}

	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public Theatre(int theatre_id, String theatre_name, String city, String movie_name, int seats, int available,
			String show_tim) {
		super();
		this.theatre_id = theatre_id;
		this.theatre_name = theatre_name;
		this.city = city;
		this.movie_name = movie_name;
		this.seats = seats;
		this.available = available;
		this.show_tim = show_tim;
	}
	
	public Theatre() {
		
	}

	public String getShow_tim() {
		return show_tim;
	}

	public void setShow_tim(String show_tim) {
		this.show_tim = show_tim;
	}
	
}
