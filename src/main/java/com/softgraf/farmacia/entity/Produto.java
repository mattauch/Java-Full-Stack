package com.softgraf.farmacia.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;



/*
 * Qualquer calsse publica que possua um costrutor padrão ou um construtor com argumentos injetados
 * Passa a ser um Bean CDI (CDI Managed Bean)
 * Então se faz necessário a anotação @Named do CDI em todos os casos
 */

@Entity
public class Produto implements Serializable {

	private static final long serialVersionUID = -309430294383912413L;
	
	private Integer id;
	private String descricao;
	private String marca;
	private String linha;
	private String quantidade;
	private String url;
	
	

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	@NotEmpty
	@Column(length = 60)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@NotEmpty
	@Column(length = 60)
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	@NotEmpty
	@Column(length = 60)
	public String getLinha() {
		return linha;
	}

	public void setLinha(String linha) {
		this.linha = linha;
	}

	
	@Column
	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	
	@Column
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
	
	
	
	
}