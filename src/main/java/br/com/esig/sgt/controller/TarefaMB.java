package br.com.esig.sgt.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.esig.service.TarefaService;
import br.com.esig.sgt.model.Tarefa;
import br.com.esig.util.Message;
import br.com.esig.util.NegocioException;
@Named("tbean")
@SessionScoped
public class TarefaMB implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Dependent
	private Tarefa tarefa = new Tarefa();
	
	@Inject
	private TarefaService service;
	
	private List<Tarefa> tarefas = new ArrayList<>();
	
	
	public void carregar() {
		 tarefas = service.todasAsTarefas();
		 
		
	}
	
	public void carrgarListagem() {
		System.out.println("carregar bean: " + tarefa.isConcluido());
		tarefas = service.filtro(tarefa.getNumero(), tarefa.getTituloDescricao(), tarefa.getResponsavel(), tarefa.isConcluido());
	}
	public void editConcluir() {
		tarefa.setConcluido(true);
		System.out.println("edit concluir: " + tarefa.isConcluido());
		adicionar();
	}
	public void  adicionar() {
		//tarefas.add(tarefa);
		//limpar();
		//return null;
		try {
			service.salvar(tarefa);
			tarefa = new Tarefa();
			carregar();
			
			Message.info("Salvo com sucesso");
			
		} catch (NegocioException e) {
			Message.erro(e.getMessage());
		}
		
	}
	
	public void excluir() {
		try {
			service.remover(tarefa);
			carregar();
			
			Message.info(tarefa.getTitulo() + " foi removida");
		} catch (NegocioException e) {
			
			Message.erro(e.getMessage());
		}
	}
	
	private void limpar() {
		tarefa = new Tarefa();
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}
	
	

}
