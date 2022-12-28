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
import com.softgraf.farmacia.entity.Cliente;
import com.softgraf.farmacia.repositorio.RepositorioClientes;
//import com.softgraf.farmacia.util.JpaUtil;

//MB JSF -> Faces
//@ManagedBean(name = "cadcliMB")
//escopo de vista, pacote javax.faces.bean
//@ViewScoped


//Managed Bean CDI
@Named("cadcliMB")
@ViewScoped //javax.faces.view.ViewScoped
public class CadastroClienteMB implements Serializable {

	private static final long serialVersionUID = 1728202463886685259L;

	private Integer paramId;
	private Modo modo; // INCLUSAO, EDICAO, CONSULTA

	@Inject
	private Cliente cliente;
	
	@Inject // substitui new RepositorioClientes(EntityManager em)
	private RepositorioClientes repositorioClientes;

	// construtor padrão
	public CadastroClienteMB() {
		this.modo = Modo.INCLUSAO;
	}

	// inicializa os valores padrões
	private void inicializar() {
	//	this.cliente.setId(1);
		this.cliente.setPessoa('F');
		this.cliente.setNome("");
		this.cliente.setCpf_cnpj("");
		this.cliente.setFone("");
		this.cliente.setEmail("");
		this.cliente.setEstado("Paraná");
		this.cliente.setCidade("");
		this.cliente.setBairro("");
		this.cliente.setRua("");
		this.cliente.setCep("");
	}

	public Integer getParamId() {
		return paramId;
	}

	public void setParamId(Integer paramId) {
		this.paramId = paramId;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<String> getEstados() {
		return Estados.listar();
	}

	// lista todas as cidades que tenham o nome parecido com o parâmetro passado
	public List<String> pesquisarCidades(String cidades) {
		return repositorioClientes.pesquisarCidades(cidades);
	}

	public void salvar() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		FacesMessage mensagem; // envia mensagens para a tela

		try {
			repositorioClientes.adicionar(cliente);
			mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Inclusão:", "Cliente cadastrado com sucesso!");

		} catch (Exception e) {
			mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de inclusão:",
					"Erro ao cadastrar cliente: " + e.getMessage());
		}
		// tela
		
		contexto.addMessage(null, mensagem);
	}

	// atualizar no banco
	public void atualizar() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		FacesMessage mensagem;

		try {
			repositorioClientes.atualizar(cliente);
			mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Edição:", "Cliente atualizado com sucesso!");

		} catch (Exception e) {
			mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao atualizar:",
					"Erro ao atualizar cliente: " + e.getMessage());
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
		return "/cadastraCliente?faces-redirect=true";
	}

	// Modo edição
	public void editar() {
		if (paramId != null) {
			FacesContext contexto = FacesContext.getCurrentInstance();
			FacesMessage mensagem;

			try {
				// recupera o cliente do banco através do ID
				Cliente c = repositorioClientes.buscarPorId(paramId);
				if (c == null) {
					mensagem = new FacesMessage(FacesMessage.SEVERITY_WARN, "Edição: ",
							"Cliente com ID = " + paramId + "não encontrado");
				} else {
					this.cliente = c; // carrega o cliente
					this.modo = Modo.EDICAO;
					mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modo Edição: ",
							"Atualização de cadastro.");
				}
			} catch (Exception e) {
				mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Edição: ",
						"Erro ao editar cliente: " + e.getMessage());
			}
			contexto.addMessage(null, mensagem);
		
		//novo cadastro, inicializa os campos
		} else {
			inicializar();
		}
	}

}
