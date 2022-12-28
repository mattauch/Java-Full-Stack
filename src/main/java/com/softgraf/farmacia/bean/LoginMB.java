package com.softgraf.farmacia.bean;

import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import com.softgraf.farmacia.controller.Usuario;

@Named  // CDI Bean
@RequestScoped  // pacote javax.enterprise.context
public class LoginMB implements Serializable {

	private static final long serialVersionUID = -1540940667687103283L;

	@Inject
	private Usuario usuario;
	
	private String nomeUsuario;
	private String senha;
	
	public String login(){
		FacesContext contexto = FacesContext.getCurrentInstance();
		
		if(this.nomeUsuario.equals("Usuario") && this.senha.equals("123")){
			this.usuario.setNome(this.nomeUsuario);
			this.usuario.setDataLogin(new Date());
			
			return "/consultaCliente?faces-redirect=true";
			
		} else {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuário ou senha inválidos!", ""));
			return null;
		}
	}
	
	public String logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/Login?faces-redirect=true";
	}
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
