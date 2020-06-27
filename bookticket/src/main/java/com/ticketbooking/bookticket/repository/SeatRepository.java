package com.ticketbooking.bookticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticketbooking.bookticket.model.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat,String>{

	public Seat findByIdAndTid(String id,int tid);
    
}
