package com.suelen.helpdesk.domain.dtos;


/*
 * Essa classe serve para fazer a conversão do usuário e senha da requisição de login
 */
public class CredenciaisDTO {

	private String email;
	private String senha;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
