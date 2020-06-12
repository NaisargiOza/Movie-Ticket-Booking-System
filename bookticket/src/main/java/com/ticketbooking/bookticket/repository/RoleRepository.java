package com.ticketbooking.bookticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticketbooking.bookticket.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
   public Role findByRole(String role);
}
