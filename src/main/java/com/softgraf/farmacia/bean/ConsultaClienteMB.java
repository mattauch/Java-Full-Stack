package com.softgraf.farmacia.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.softgraf.farmacia.entity.Cliente;
import com.softgraf.farmacia.repositorio.RepositorioClientes;
//import com.softgraf.farmacia.util.JpaUtil;

//Managed Bean JSF
//@ManagedBean
//@ViewScoped

//Managed Bean CDI
@Named("consultaClienteMB")
@ViewScoped
public class ConsultaClienteMB implements Serializable {

	private static final long serialVersionUID = -7838925002174827018L;

	private List<Cliente> clientes;
	private Cliente clienteSelecionado;

	@Inject
	private RepositorioClientes repositorioClientes;

	// lista todos os clientes do banco
	public void consultar() {
		this.clientes = repositorioClientes.todos();
	}

	public List<Cliente> getClientes() {
		return this.clientes;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	// se for voltar para a própria página, o retorno é void
	// se for voltar para outra página o retorno é String com um return
	public void excluir() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		FacesMessage mensagem;

		try {
			repositorioClientes.removerPorId(clienteSelecionado.getId());
			consultar();
			mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exclusão: ", "Cliente removido com sucesso!");
			
		} catch (Exception e) {
			mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Exclusão: ", "Erro ao excluir o cliente: " + e.getMessage());
		}
		contexto.addMessage(null, mensagem);
	}
}
