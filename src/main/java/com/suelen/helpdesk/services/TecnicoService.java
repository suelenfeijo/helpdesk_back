package com.suelen.helpdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suelen.helpdesk.domain.Tecnico;
import com.suelen.helpdesk.repositories.TecnicoRepository;
import com.suelen.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	TecnicoRepository repository;
	
	
	public Tecnico findById(Integer id) {

		// opcional -> pode ou não encontrar o elemento pelo id
		Optional<Tecnico> obj = repository.findById(id);

		// ou se não encontrar, retorna nulo
		// return obj.orElse(null);

		// ou se não encontrar, retorna uma exceção
		return obj.orElseThrow(() -> new ObjectNotFoundException("objeto não encontrado Id: " + id));
	}
	
}
