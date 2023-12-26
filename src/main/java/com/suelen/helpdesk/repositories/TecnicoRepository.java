package com.suelen.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suelen.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
	
}
