package br.com.esig.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.esig.dao.DAO;
import br.com.esig.sgt.model.Tarefa;
import br.com.esig.util.NegocioException;

public class TarefaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private DAO<Tarefa> dao ;
	
	public void salvar( Tarefa t) throws NegocioException {
		// TODO Auto-generated method stub
		dao.salvar(t);
	}
	
	public void remover(Tarefa t) throws NegocioException {
		dao.remover(Tarefa.class, t.getid());
	}
	
	public List<Tarefa> todasAsTarefas(){
		return dao.buscarTodos("select m from Tarefa m order by m.titulo");
	}
	
	public List<Tarefa> filtro(Long id, String titulo, String responsavel, Boolean situacao){
		System.out.println("filtro service : "+ situacao);
		return dao.filtrarPorIdTituloESituacao(Tarefa.class, id, titulo, responsavel, situacao);
	}
 	
	
}
