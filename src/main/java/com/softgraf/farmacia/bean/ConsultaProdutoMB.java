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

import com.softgraf.farmacia.entity.Produto;
import com.softgraf.farmacia.repositorio.RepositorioProdutos;



//Managed Bean JSF
//@ManagedBean
//@ViewScoped

//Managed Bean CDI
@Named("consultaPMB")
@ViewScoped
public class ConsultaProdutoMB implements Serializable {


	private static final long serialVersionUID = -6870616475146117603L;
	
	
	private List<Produto> produto;
	private Produto produtoSelecionado;

	@Inject
	private RepositorioProdutos repositorioProdutos;

	// lista todos os produtos do banco
	public void consultar() {
		this.produto = repositorioProdutos.todos();
	}

	public List<Produto> getProduto() {
		return this.produto;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	// se for voltar para a própria página, o retorno é void
	// se for voltar para outra página o retorno é String com um return
	public void excluir() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		FacesMessage mensagem;

		try {
			repositorioProdutos.removerPorId(produtoSelecionado.getId());
			consultar();
			mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exclusão: ", "Produto removido com sucesso!");
			
		} catch (Exception e) {
			mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Exclusão: ", "Erro ao excluir o produto: " + e.getMessage());
		}
		contexto.addMessage(null, mensagem);
	}
}
