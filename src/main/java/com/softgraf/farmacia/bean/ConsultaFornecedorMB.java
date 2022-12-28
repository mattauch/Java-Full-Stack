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

import com.softgraf.farmacia.entity.Fornecedor;
import com.softgraf.farmacia.repositorio.RepositorioFornecedores;



//import com.softgraf.farmacia.util.JpaUtil;

//Managed Bean JSF
//@ManagedBean
//@ViewScoped

//Managed Bean CDI
@Named("consultaFornecedorMB")
@ViewScoped
public class ConsultaFornecedorMB implements Serializable {
	
	private static final long serialVersionUID = -5178601670656193756L;
	
	private List<Fornecedor> fornecedores;
	private Fornecedor fornecedorSelecionado;

	@Inject
	private RepositorioFornecedores repositorioFornecedores;

	// lista todos os fornecedores do banco
	public void consultar() {
		this.fornecedores = repositorioFornecedores.todos();
	}

	public List<Fornecedor> getFornecedores() {
		return this.fornecedores;
	}

	public Fornecedor getFornecedorSelecionado() {
		return fornecedorSelecionado;
	}

	public void setFornecedorSelecionado(Fornecedor fornecedorSelecionado) {
		this.fornecedorSelecionado = fornecedorSelecionado;
	}

	// se for voltar para a própria página, o retorno é void
	// se for voltar para outra página o retorno é String com um return
	public void excluir() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		FacesMessage mensagem;

		try {
			repositorioFornecedores.removerPorId(fornecedorSelecionado.getId());
			consultar();
			mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exclusão: ", "Fornecedor removido com sucesso!");
			
		} catch (Exception e) {
			mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Exclusão: ", "Erro ao excluir o fornecedor: " + e.getMessage());
		}
		contexto.addMessage(null, mensagem);
	}
}
