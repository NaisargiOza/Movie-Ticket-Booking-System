package com.ticketbooking.bookticket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticketbooking.bookticket.model.Theatre;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre,Integer>{
   public List<Theatre> findByCity(String city);
}
