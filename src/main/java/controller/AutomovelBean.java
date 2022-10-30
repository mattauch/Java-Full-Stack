package controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Automovel;
import model.JpaUtil;

@ManagedBean(name = "automovelBean") // nome opcional
@SessionScoped // padrão: RequestScoped
//Todo managed Bean deve ter um construtor padrão
public class AutomovelBean {

	private Automovel automovel;

	// Construtor padrão obrigatório
	public AutomovelBean() {
		this.automovel = new Automovel();
	}

	public Automovel getAutomovel() {
		return automovel;
	}

	public void setAutomovel(Automovel automovel) {
		this.automovel = automovel;
	}

	public String salvar(Automovel automovel) {
		EntityManager em = JpaUtil.getEntityManager();
		// Inicia a transação
		em.getTransaction().begin();

		em.persist(automovel);

		// Finaliza a transação
		em.getTransaction().commit();
		em.close();

		this.automovel = new Automovel();
		return "";
	}

	public String excluir(Automovel automovel) {
		EntityManager em = JpaUtil.getEntityManager();
		// Inicia a transação
		em.getTransaction().begin();

		// Anexa a entidade ao EntityManager
		Automovel auto = em.merge(automovel);
		em.remove(auto);

		// Finaliza a transação
		em.getTransaction().commit();
		em.close();
		
		return "";
	}
	
	public List<Automovel> getAutomoveis(){
		List<Automovel> lista;
		
		EntityManager em = JpaUtil.getEntityManager();
		//Cria uma consulta no EntityManager (banco de dados), usando linguagem JPQL
		//Java Persistence Query Language
		TypedQuery<Automovel> q = em.createQuery("SELECT a FROM Automovel a", Automovel.class);
		//Executa a consulta
		lista = q.getResultList();
		
		//Finaliza transação
		em.close();
		
		return lista;
	}

}
