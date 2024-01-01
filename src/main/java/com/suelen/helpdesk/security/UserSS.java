package com.suelen.helpdesk.security;


import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.suelen.helpdesk.domain.enums.Perfil;

public class UserSS implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String email;
	private String senha;
	/*
	 * quando der um retorno na lista de authorities, no caso um get
	 */
	private Collection<? extends GrantedAuthority> authorities;

	
	/*
	 * construtor de userSS
	 */
	public UserSS(Integer id, String email, String senha, Set<Perfil> perfis) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		/*
		 * cada perfil x , será mapeado, e criado um novo SimpleGrantedAuthority -> , que vai dar a permissão para a descrição de cada perfil do usuário,
		 * coleta tudo e transforma em uma lista set com collect(Collectors.toSet()
		 */
		this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toSet());
	}

	public Integer getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		/*
		 * a lista de authorities vai retornar authorities
		 */
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	/*
	 * a conta não está expirada? true = pq não está
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/*
	 * a conta não está bloqueada? true = pq não está
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/*
	 * o credencial não está expirado? true = pq não está
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/*
	 * a conta está aberta? true = pq está
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

}
