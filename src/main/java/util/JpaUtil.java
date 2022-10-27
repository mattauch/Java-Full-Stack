package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

	private static final EntityManagerFactory emf;

	//inicializador estático: os blocos estáticos são rodados automáticamente (conexão com o banco)
	//Carrega na memória o persistence.xml
	//se tiver mais de um bloco eles serão executados na ordem
	static {
		System.out.println("Rodou o inicializador estático do JpaUtil");
		emf = Persistence.createEntityManagerFactory("editora_pu");
	}
	
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
}
