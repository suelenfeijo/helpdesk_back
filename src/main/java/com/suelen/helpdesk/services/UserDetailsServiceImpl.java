package com.suelen.helpdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.suelen.helpdesk.domain.Pessoa;
import com.suelen.helpdesk.repositories.PessoaRepository;
import com.suelen.helpdesk.security.UserSS;

/*
 * 
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private PessoaRepository repository;

	/*
	 * ele carrega o usuario pelo username, mas nós recebemos um email, pois é logado com email e senha
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	
		/*
		 * procurar o user pelo email, verifica se o email existe
		 */
		Optional<Pessoa> user = repository.findByEmail(email);
		/*
		 * se for presente na tabela de pessoa o email, cria um novo UserSS com os dados da pessoa buscada pelo email
		 *  - retorna os dados: id, o email, senha, e o perfil
		 *  
		 * se não UsernameNotFoundException , com o email que não foi encontrado
		 */
		if(user.isPresent()) {
			return new UserSS(user.get().getId(), user.get().getEmail(), user.get().getSenha(), user.get().getPerfis());
		}
		throw new UsernameNotFoundException(email);
	}

}
