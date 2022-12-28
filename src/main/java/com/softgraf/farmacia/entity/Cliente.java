package com.softgraf.farmacia.entity;

import java.io.Serializable;

/*
 * Qualquer calsse publica que possua um costrutor padrão ou um construtor com argumentos injetados
 * Passa a ser um Bean CDI (CDI Managed Bean)
 * Então se faz necessário a anotação @Named do CDI em todos os casos
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Cliente implements Serializable {

	private static final long serialVersionUID = -2448713835019776339L;

	private Integer id;
	private Character pessoa; // física ou jurídica
	private String nome;
	private String cpf_cnpj;
	private String email;
	private String fone;
	private String rua;
	private String bairro;
	private String cidade;
	private String estado;
	private String cep;

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	// 'F' ou 'J'
	@Column(length = 1, nullable = false)
	public Character getPessoa() {
		return pessoa;
	}

	public void setPessoa(Character pessoa) {
		this.pessoa = pessoa;
	}

	@NotEmpty // hibernate validator --> não fica gravado na tabela, somente no momento de
				// excução
	@Size(min = 3, max = 60) // hibernate validator
	@Column(length = 60, nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(length = 18)
	public String getCpf_cnpj() {
		return cpf_cnpj;
	}

	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}

	@Size(max = 60) // hibernate validator
	@Column(length = 60)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(length = 14)
	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	@Column(length = 30)
	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	@Column(length = 20)
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@NotEmpty
	@Size(min = 3, max = 30)
	@Column(length = 30, nullable = false)
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@NotEmpty
	@Column(length = 20, nullable = false)
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Column(length = 9)
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

}
