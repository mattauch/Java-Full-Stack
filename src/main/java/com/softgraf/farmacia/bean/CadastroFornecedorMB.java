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

import com.softgraf.farmacia.controller.Modo;
import com.softgraf.farmacia.dominio.Estados;
import com.softgraf.farmacia.entity.Fornecedor;
import com.softgraf.farmacia.repositorio.RepositorioFornecedores;


//Managed Bean CDI
@Named("cadforMB")
@ViewScoped //javax.faces.view.ViewScoped
public class CadastroFornecedorMB implements Serializable {

	private static final long serialVersionUID = -9214970855865260209L;
	
	
	private Integer paramId;
	private Modo modo; // INCLUSAO, EDICAO, CONSULTA

	@Inject
	private Fornecedor fornecedor;
	
	@Inject 
	private RepositorioFornecedores repositorioFornecedores;

	// construtor padrão
	public CadastroFornecedorMB() {
		this.modo = Modo.INCLUSAO;
	}

	// inicializa os valores padrões
	private void inicializar() {
	//	this.fornecedor.setId(1);
		this.fornecedor.setRazaoSocial("");
		this.fornecedor.setCnpj("");
		this.fornecedor.setFone("");
		this.fornecedor.setEmail("");
		this.fornecedor.setEstado("Paraná");
		this.fornecedor.setCidade("");
		this.fornecedor.setBairro("");
		this.fornecedor.setRua("");
		this.fornecedor.setCep("");
	}

	public Integer getParamId() {
		return paramId;
	}

	public void setParamId(Integer paramId) {
		this.paramId = paramId;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<String> getEstados() {
		return Estados.listar();
	}

	// lista todas as cidades que tenham o nome parecido com o parâmetro passado
	public List<String> pesquisarCidades(String cidades) {
		return repositorioFornecedores.pesquisarCidades(cidades);
	}

	public void salvar() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		FacesMessage mensagem; // envia mensagens para a tela

		try {
			repositorioFornecedores.adicionar(fornecedor);
			mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Inclusão:", "Fornecedor cadastrado com sucesso!");

		} catch (Exception e) {
			mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de inclusão:",
					"Erro ao cadastrar fornecedor: " + e.getMessage());
		}
		// tela
		
		contexto.addMessage(null, mensagem);
	}

	// atualizar no banco
	public void atualizar() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		FacesMessage mensagem;

		try {
			repositorioFornecedores.atualizar(fornecedor);
			mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Edição:", "Fornecedor atualizado com sucesso!");

		} catch (Exception e) {
			mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao atualizar:",
					"Erro ao atualizar fornecedor: " + e.getMessage());
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
		return "/cadastraFornecedor?faces-redirect=true";
	}

	// Modo edição
	public void editar() {
		if (paramId != null) {
			FacesContext contexto = FacesContext.getCurrentInstance();
			FacesMessage mensagem;

			try {
				// recupera o fornecedor do banco através do ID
				Fornecedor f = repositorioFornecedores.buscarPorId(paramId);
				if (f == null) {
					mensagem = new FacesMessage(FacesMessage.SEVERITY_WARN, "Edição: ",
							"Fornecedor com ID = " + paramId + "não encontrado");
				} else {
					this.fornecedor = f; // carrega o fornecedor
					this.modo = Modo.EDICAO;
					mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modo Edição: ",
							"Atualização de cadastro.");
				}
			} catch (Exception e) {
				mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Edição: ",
						"Erro ao editar fornecedor: " + e.getMessage());
			}
			contexto.addMessage(null, mensagem);
		
		//novo cadastro, inicializa os campos
		} else {
			inicializar();
		}
	}

}
