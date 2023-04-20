package br.com.esig.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.esig.sgt.model.base;

public class DAO<T extends base> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private static EntityManager manager = ConectionFactory.getEntityManager();
	
	public T buscarPorId(Class<T> clazz, Long id) {
		return manager.find(clazz, id);
	}
	
	public void salvar(T t) {
		try {
			manager.getTransaction().begin();
			
			if(t.getid()== null) {
				manager.persist(t);
			}else {
				manager.merge(t);
			}
			
			
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
		
	}
	
	
	public void remover(Class<T>clazz, Long id) {
		T t = buscarPorId(clazz, id);
		
		try {
			manager.getTransaction().begin();
			
			manager.remove(t);
			
			
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
		

	}
	@SuppressWarnings("unchecked")
	public List<T> buscarTodos(String jpql){
		System.out.println("buscar todos" + jpql);
		Query query = manager.createQuery(jpql);
		return query.getResultList();
		
		
	}
	//select m from Tarefa m order by m.titulo
	//SELECT m FROM Tarefa m WHERE 1 = 1 AND m.id = :id AND m.titulo LIKE :titulo
	//select m from Tarefa m order by m.titulo
	public List<T> filtrarPorIdTituloESituacao(Class<T> clazz,Long id, String titulo, String responsavel, Boolean situacao ) {
		System.out.println("Dao:  " + situacao);
	    String jpql = "SELECT m FROM Tarefa m WHERE";
	    boolean estado = false;
	    
	    if (id != null ) {
	        jpql += " m.id = :id";
	        System.out.println("id");
	        estado = true;
	    } 
	    
	    if (titulo != null && !titulo.isEmpty()) {
	    	
	    	if(jpql.equals("SELECT m FROM Tarefa m WHERE")) {
	    		jpql += " m.titulo LIKE :titulo";
	    	}else {
	    		jpql += " and  m.titulo LIKE :titulo";
	    		
	    	}
	        System.out.println("olá");
	        estado = true;
	    }
	    if (situacao != null) {
	    	if(jpql.equals("SELECT m FROM Tarefa m WHERE")) {
	    		jpql += " m.concluido = :concluido";
	    	}else {
	    		jpql += " AND m.concluido = :concluido";
	    		
	    	}
	    	System.out.println("situacao");
	    	estado = true;
	    }
	    
	    if (responsavel != null && !responsavel.isEmpty()) {
	    	
	    	if(jpql.equals("SELECT m FROM Tarefa m WHERE")) {
	    		 jpql += " m.responsavel LIKE :responsavel";
	    	}else {
	    		 jpql += " AND m.responsavel LIKE :responsavel";
	    		
	    	}
	        
	        System.out.println("Responsavel");
	        estado = true;
	    }
	    
	    
	    if(estado == false) {
	    	jpql = "select m from Tarefa m order by m.titulo";
	    }
	    

	    TypedQuery<T> query = manager.createQuery(jpql.toString(), clazz);
	   
	    if (id != null) {
	        query.setParameter("id", id);
	    }
	    if (titulo != null && !titulo.isEmpty()) {
	        query.setParameter("titulo", "%" + titulo + "%");
	    }
	    if (situacao != null) {
	        
	        query.setParameter("concluido", situacao);
	    }
	    
	    if (responsavel != null && !responsavel.isEmpty()) {
	        query.setParameter("responsavel", "%" + responsavel + "%");
	    }
	    
	    System.out.println("query: " + jpql);
	    

	    return query.getResultList();
	}

	
	
	
}
