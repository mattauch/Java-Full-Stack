package com.softgraf.farmacia.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.softgraf.farmacia.controller.Modo;
import com.softgraf.farmacia.entity.Produto;
import com.softgraf.farmacia.repositorio.RepositorioProdutos;


//Managed Bean CDI
@Named("cadpMB")
@ViewScoped //javax.faces.view.ViewScoped
public class CadastroProdutoMB implements Serializable {



	private static final long serialVersionUID = -8144693904048558101L;
	
	
	private Integer paramId;
	private Modo modo; // INCLUSAO, EDICAO, CONSULTA

	@Inject
	private Produto produto;
	
	@Inject 
	private RepositorioProdutos repositorioProdutos;

	// construtor padrão
	public CadastroProdutoMB() {
		this.modo = Modo.INCLUSAO;
	}

	// inicializa os valores padrões
	private void inicializar() {
		this.produto.setDescricao("");
		this.produto.setMarca("");
		this.produto.setLinha("");
		this.produto.setQuantidade("");
		this.produto.setUrl("");
	}

	public Integer getParamId() {
		return paramId;
	}

	public void setParamId(Integer paramId) {
		this.paramId = paramId;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	public void salvar() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		FacesMessage mensagem; // envia mensagens para a tela

		try {
			repositorioProdutos.adicionar(produto);
			mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Inclusão:", "Produto cadastrado com sucesso!");

		} catch (Exception e) {
			mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de inclusão:",
					"Erro ao cadastrar produto: " + e.getMessage());
		}
		// tela
		
		contexto.addMessage(null, mensagem);
	}

	// atualizar no banco
	public void atualizar() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		FacesMessage mensagem;

		try {
			repositorioProdutos.atualizar(produto);
			mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Edição:", "Produto atualizado com sucesso!");

		} catch (Exception e) {
			mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao atualizar:",
					"Erro ao atualizar produto: " + e.getMessage());
		}

		contexto.addMessage(null, mensagem);
	}

	public String guardar() {
		if (modo == Modo.EDICAO)
			atualizar();
		else
			salvar();

		// armazena mensagens
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		return "/cadastraProduto?faces-redirect=true";
	}

	// Modo edição
	public void editar() {
		if (paramId != null) {
			FacesContext contexto = FacesContext.getCurrentInstance();
			FacesMessage mensagem;

			try {
				// recupera o produto do banco através do ID
				Produto p = repositorioProdutos.buscarPorId(paramId);
				if (p == null) {
					mensagem = new FacesMessage(FacesMessage.SEVERITY_WARN, "Edição: ",
							"Produto com ID = " + paramId + "não encontrado");
				} else {
					this.produto = p; 
					this.modo = Modo.EDICAO;
					mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modo Edição: ",
							"Atualização de cadastro.");
				}
			} catch (Exception e) {
				mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Edição: ",
						"Erro ao editar produto: " + e.getMessage());
			}
			contexto.addMessage(null, mensagem);
		
		//novo cadastro, inicializa os campos
		} else {
			inicializar();
		}
	} 

}
