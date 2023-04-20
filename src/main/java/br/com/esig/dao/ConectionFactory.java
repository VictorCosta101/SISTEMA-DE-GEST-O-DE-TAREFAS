package br.com.esig.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConectionFactory {
	
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("gestaoTarefas");
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

}
