package com.softgraf.farmacia.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
//Este objeto existirá enquanto a aplicação estiver em execução (Application)
public class EntityManagerProducer {
	
	private EntityManagerFactory factory;
	
	public EntityManagerProducer() {
		this.factory = Persistence.createEntityManagerFactory("cadastroPU");
	}
	
	
	//cria o Entity Manager
	@Produces // método produtor gera um objeto que pode ser injetado
	@RequestScoped
	public EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	
	//Fecha o Entity Manager
	//@Disposes - método finalizador, executado automaticamente quando o escopo onde o objeto foi produzido terminar(request nesse caso)
	public void closeEntityManager(@Disposes EntityManager em) {
		em.close();
	}

}
