package entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Livro implements Serializable{

	private static final long serialVersionUID = -8027835745489513019L;

	private Long id;
	private String nomeLivro;
	private Editora editora;
	
	public Livro() {
		this.editora = new Editora();
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

	@Column(length = 100, name = "nome_livro")
	public String getNomeLivro() {
		return nomeLivro;
	}

	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}

	@ManyToOne 
	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	@Override
	public int hashCode() {
		return Objects.hash(editora, id, nomeLivro);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		return Objects.equals(editora, other.editora) && Objects.equals(id, other.id)
				&& Objects.equals(nomeLivro, other.nomeLivro);
	}
	
	
	
	
}
