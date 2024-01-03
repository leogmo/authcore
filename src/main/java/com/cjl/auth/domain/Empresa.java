package com.cjl.auth.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@EqualsAndHashCode(callSuper = false)
public class Empresa {

	private Long id;
	private String razaoSocial;
	private String cnpj;
	private String inscMunicipal;
	private String endereco;
	private String bairro;
	private String cidade;
	private String estado;
	private String cep;
	
	public Empresa(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public Empresa(Long id, String razaoSocial, String cnpj, String inscMunicipal, String endereco, String bairro,
			String cidade, String estado, String cep) {
		super();
		this.id = id;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.inscMunicipal = inscMunicipal;
		this.endereco = endereco;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
	}
	
}
