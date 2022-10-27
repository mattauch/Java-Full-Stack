package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Editora implements Serializable {

	private static final long serialVersionUID = -5439557620945625108L;

	private Long id;
	private String nomeEditora;
	private List<Livro> livros;

	// Construtor padrão
	public Editora() {
		this.livros = new ArrayList();
	}
	
	@Id
	@GeneratedValue
	@Column(name="codigo")
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(length = 100, name = "nome_editora", unique = true)
	public String getNomeEditora() {
		return nomeEditora;
	}
	
	public void setNomeEditora(String nomeEditora) {
		this.nomeEditora = nomeEditora;
	}
	
	@OneToMany (mappedBy = "editora", cascade = CascadeType.ALL, fetch = FetchType.EAGER) //é o nome do atributo que está lá na classe livro, todos os métodos cascade e modo ansioso
	public List<Livro> getLivros() {
		return livros;
	}
	
	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
	
	@Transient
	public Integer getTotalLivros() {
		return livros.size();
	}
	
	public void setTotalLivros(Integer totalLivros) {
		//Esse método não vai ser utilizado
	}
	
	//gera um código único para cada objeto
	@Override
	public int hashCode() {
		return Objects.hash(id, nomeEditora);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Editora other = (Editora) obj;
		return Objects.equals(id, other.id) && Objects.equals(nomeEditora, other.nomeEditora);
	}

	@Override
	public String toString() {
		return "Editora [id=" + id + ", nome=" + nomeEditora + "]";
	}

}
