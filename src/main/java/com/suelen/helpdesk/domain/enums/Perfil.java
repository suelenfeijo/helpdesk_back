package com.suelen.helpdesk.domain.enums;

public enum Perfil {


	//array --> posições 0,1,2
	ADMIN (0,"ROLE_ADMIN"), CLIENTE (1, "ROLE_CLIENTE") , TECNICO (2,"ROLE_TECNICO");
	
	private Integer codigo;
	private String descricao;
	
	
	
	private Perfil(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}



	public Integer getCodigo() {
		return codigo;
	}



	public String getDescricao() {
		return descricao;
	}

	public static Perfil toEnum(Integer cod) {
		if (cod == null) {
			return null;
			//o código retornado é nulo
		}
		for (Perfil x : Perfil.values()) {
			if(cod.equals(x.getCodigo())) {
				//se eu tenho o código , retorna o código solicitado
				return x;
			}
		
		}
		//se não, perfil inválido
		throw new IllegalArgumentException("Perfil Inválido");
	}
	
}
