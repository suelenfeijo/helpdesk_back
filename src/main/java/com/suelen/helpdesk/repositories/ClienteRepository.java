package com.suelen.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suelen.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
}
