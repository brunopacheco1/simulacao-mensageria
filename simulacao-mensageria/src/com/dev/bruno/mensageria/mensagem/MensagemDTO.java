package com.dev.bruno.mensageria.mensagem;

import java.io.Serializable;

public class MensagemDTO implements Serializable {

	private static final long serialVersionUID = 6980200948424281279L;

	private Long id;
	
	private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}