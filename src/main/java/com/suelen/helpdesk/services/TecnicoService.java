package com.suelen.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suelen.helpdesk.domain.Pessoa;
import com.suelen.helpdesk.domain.Tecnico;
import com.suelen.helpdesk.domain.dtos.TecnicoDTO;
import com.suelen.helpdesk.repositories.PessoaRepository;
import com.suelen.helpdesk.repositories.TecnicoRepository;
import com.suelen.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.suelen.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	TecnicoRepository repository;
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	
	public Tecnico create(TecnicoDTO objDTO) {
		objDTO.setId(null);
	//	objDTO.setSenha(encoder.encode(objDTO.getSenha()));
		//validaPorCpfEEmail(objDTO);
		Tecnico newObj = new Tecnico(objDTO);
		return repository.save(newObj);
	}
	
	public Tecnico findById(Integer id) {

		// opcional -> pode ou não encontrar o elemento pelo id
		Optional<Tecnico> obj = repository.findById(id);

		// ou se não encontrar, retorna nulo
		// return obj.orElse(null);

		// ou se não encontrar, retorna uma exceção
		return obj.orElseThrow(() -> new ObjectNotFoundException("objeto não encontrado Id: " + id));
	}
	
	
	public List<Tecnico> findAll() {
		return repository.findAll();
	}
	
	
	/*private void validaPorCpfEEmail(TecnicoDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}

		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
		}
	}*/

}
