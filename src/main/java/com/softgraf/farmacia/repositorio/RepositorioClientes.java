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

import com.softgraf.farmacia.entity.Cliente;
import com.softgraf.farmacia.util.Transacional;

/*
 * Um repositório representa uma coleção de objetos de um tipo específico
 * É um mediador entre a camada de negócios e o acesso de dados
 * Deve fornecer métodos para adicionar, remover, atualizar, buscar, listar, etc.
 */

@RequestScoped // por requisição, informações atualizadas do banco -> menor tempo de vida que
				// atualiza mais rápido.
public class RepositorioClientes implements Serializable {

	private static final long serialVersionUID = -5841325902476402508L;
	
	// Problema: EntityManager não é um Bean CDI, portanto não posso usar @Inject no EntityManager
	private EntityManager em; // comunicação com o banco de dados

	public RepositorioClientes() { // construtor padrão
	}
	
	@Inject // Problema: EntityManager não é um Bean CDI, portanto não posso usar @Inject no EntityManager
	public RepositorioClientes(EntityManager em) {
	this.em = em;
	}
	
	@Transacional
	public void adicionar(Cliente cliente) {
	//	EntityTransaction tx = em.getTransaction();
	//	tx.begin();
		em.persist(cliente);
	//	tx.commit();
	}
	
	@Transacional
	public void removerPorId(Integer id) {
		Cliente cliente = em.find(Cliente.class, id); // traz o cliente da classe Cliente
		
		em.remove(cliente);
	}
	
	
	public Cliente buscarPorId(Integer id) {
		return em.find(Cliente.class, id);
	}
	
	@Transacional
	public void atualizar(Cliente cliente) {
		em.merge(cliente); // merge = juntar = atualizar
	}

	public List<Cliente> todos() {
		// JPQL (JPA Querry Language) -> busca na entidade, diferente do SQL que busca
		// no banco.
		TypedQuery<Cliente> query = em.createQuery("from Cliente ", Cliente.class); // Cliente.class -> tipo, devolve
																					// clientes
		return query.getResultList();

		/*
		 * Tipagem da consulta para devolver uma lista de clientes
		 */

	}

	public List<String> pesquisarCidades(String cidade) {
		// digitar e buscar no banco ao mesmo tempo, como se fosse um thread. (no google
		// quem faz isso é o ajax)
		// "select distinct(cidade) from cliente where cidade like '%p%'; "
		TypedQuery<String> query = em.createQuery( // coluna do banco parâmetro JPQL
				"select distinct cidade from Cliente where upper(cidade) like upper(:cidade)", String.class);
		query.setParameter("cidade", "%" + cidade + "%");
		return query.getResultList();
	}

}
