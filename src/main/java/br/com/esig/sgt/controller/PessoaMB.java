package br.com.esig.sgt.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;

import br.com.esig.sgt.model.Pessoa;

@Named("bean")
@SessionScoped
public class PessoaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Dependent 
	private Pessoa pessoa = new Pessoa() ;
	
	private List<Pessoa> pessoas = new ArrayList<>();
	
	
	public String adicionar() {
		pessoas.add(pessoa);
		limpar();
		return null;
	}
	
	private void limpar() {
		pessoa = new Pessoa();
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
}
