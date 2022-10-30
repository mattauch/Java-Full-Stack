package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/*
 * Projeto Maven -> POM: esse arquivo baixa todas as bibliotecas após a configuração correta.
 * 
 * 
 *EntityManager: é um serviço responsável por gerenciar as entidades (tabelas), através dele é possível gerenciar 
 *o ciclo de vida das entidades, operação de sincronização com a base de dados (inserir, atualizar ou remover), consultar entidades e outros. 
 *
 *Cria o relacionamento das classes (objetos) com o banco de dados. A comunicação se da pelo id.
 *
 *Você trabalha com o EntityManager, não com o banco em si.
 *
 *EntityManagerFactory-> classe para gerar objetos para não utilizar o new (padronização dos objetos)
 *
 */

public class JpaUtil {
		
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("automoveis_pu");
	
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
		
	
	}

}

