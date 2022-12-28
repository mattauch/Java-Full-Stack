package com.softgraf.farmacia.controller;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped // pacote import javax.enterprise.context.SessionScoped;
public class Usuario implements Serializable {
	//interface de marcação -> hash = código baseado em algo.
	private static final long serialVersionUID = -3795722130415151590L;

	private String nome;
	private Date dataLogin;
	
	public boolean isLogado() {
		return nome != null;
	}
	
	public String getNome() {
		return nome;
	}

	public Date getDataLogin() {
		return dataLogin;
	}

	public void setDataLogin(Date dataLogin) {
		this.dataLogin = dataLogin;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
