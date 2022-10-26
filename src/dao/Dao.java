package dao;
//Data Access Object
//Interface CRUD genérica

import java.util.List;

public interface Dao<T> {
	
	boolean inserir (T tipo);
	
	boolean atualizar (T tipo);
	
	T buscar(long id);
	
	boolean apagar (long id);
	
	List<T> listar();
	
}
