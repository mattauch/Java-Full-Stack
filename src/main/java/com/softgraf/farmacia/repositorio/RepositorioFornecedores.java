package com.softgraf.farmacia.repositorio;

import java.io.Serializable;
/*
 * Pacotes para Scopos:
 * javax.faces.bean -> bean JSF
 * javax.faces.view -> bean JSF (PrimeFaces)
 * javax.enterprise.context -> bean CDI (pacote Weld)
 */
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
//import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.softgraf.farmacia.entity.Fornecedor;
import com.softgraf.farmacia.util.Transacional;

@RequestScoped
public class RepositorioFornecedores implements Serializable {
	
	private static final long serialVersionUID = 8178528316969573953L;
	
	
	
	private EntityManager em; 
	public RepositorioFornecedores() { // construtor padrão
	}
	
	@Inject // Problema: EntityManager não é um Bean CDI, portanto não posso usar @Inject no EntityManager
	public RepositorioFornecedores(EntityManager em) {
	this.em = em;
	}
	
	@Transacional
	public void adicionar(Fornecedor fornecedor) {
	//	EntityTransaction tx = em.getTransaction();
	//	tx.begin();
		em.persist(fornecedor);
	//	tx.commit();
	}
	
	@Transacional
	public void removerPorId(Integer id) {
		Fornecedor fornecedor = em.find(Fornecedor.class, id);
		
		em.remove(fornecedor);
	}
	
	
	public Fornecedor buscarPorId(Integer id) {
		return em.find(Fornecedor.class, id);
	}
	
	@Transacional
	public void atualizar(Fornecedor fornecedor) {
		em.merge(fornecedor); // merge = juntar = atualizar
	}

	public List<Fornecedor> todos() {
		TypedQuery<Fornecedor> query = em.createQuery("from Fornecedor ", Fornecedor.class);
		
		
		
		return query.getResultList();

		/*
		 * Tipagem da consulta para devolver uma lista
		 */

	}

	public List<String> pesquisarCidades(String cidade) {
		TypedQuery<String> query = em.createQuery( // coluna do banco parâmetro JPQL
				"select distinct cidade from Fornecedor where upper(cidade) like upper(:cidade)", String.class);
		query.setParameter("cidade", "%" + cidade + "%");
		return query.getResultList();
	}

}
