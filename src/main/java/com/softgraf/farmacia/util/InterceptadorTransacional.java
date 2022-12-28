package com.softgraf.farmacia.util;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/*
 * A classe responsável pelas interceptações deve ser anotada com @Interceptor e @Transacional
 */


@Interceptor //especifica que uma classe é o interceptador
@Transacional
public class InterceptadorTransacional implements Serializable {

	private static final long serialVersionUID = -878489902366159151L;

	@Inject
	private EntityManager em;
	boolean begin = false; // nenhuma transação iniciada pelo método invocar

	/*
	 * O método que fará as Interceptações Transacionais precisa: - receber um
	 * objeto do tipo InvocationContext como parâmetro; - retorar um Object; - pode
	 * lançar uma Exception; - além de ser anotado com @AtoundInvoke; - deve chamar
	 * (executar) contexto.proceed(), que faz com que o método interceptado seja
	 * chamado
	 */

	@AroundInvoke
	public Object invocar(InvocationContext contexto) throws Exception {
		EntityTransaction tx = em.getTransaction();

		try {

			if (!tx.isActive()) { // se transação não ativa

				// truque para executar o rollback no que já passou, para que
				// um futuro commit não execute operações sem transações
				tx.begin();
				tx.rollback();

				// agora sim inicia a nova transação
				tx.begin();
				begin = true;
				System.out.println("\n===== Inicia a transação =====");
			}
			/*
			 * procede com a proxima etapa do processo de invocação. proceed(), faz com que
			 * outro interceptor seja invocado ou faz com que o objeto final seja invocado.
			 */
			return contexto.proceed();

		} catch (Exception e) {
			if (tx != null && begin) {
				tx.rollback();
				System.out.println("\n===== Ocorreu uma exceção durante a transação =====");
			}
			throw e;

		} finally {
			if (tx != null && tx.isActive() && begin)
				tx.commit();
			System.out.println("\n===== Finalizando a transação =====");
		}
	}
	
	/*
	 * Agora precisamos registrar o interceptador, podemos fazer isso no arquivo beans.xml
	 */

}
