package model.entity;

import java.time.LocalDate;
import java.util.ArrayList;

public class Pessoa {
	public static final int PESQUISADOR = 1;
	public static final int VOLUNTARIO = 2;
	public static final int PUBLICO_GERAL = 3;
	
	private int id;
	private String nome; 
	private String cpf;
	private char sexo;
	private LocalDate dataNascimento;
	private int tipo;
	private Pais paisOrigem;
	
	//Comentado para evitar confusões durante a prova
	//private ArrayList<Vacinacao> vacinacoes;
	
	public Pessoa() {
		
	}

	public Pessoa(int id, String nome, String cpf, char sexo, LocalDate dataNascimento, int tipo, Pais pais) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.tipo = tipo;
		this.paisOrigem = pais;
		//this.vacinacoes = vacinacoes;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public Pais getPaisOrigem() {
		return paisOrigem;
	}

	public void setPaisOrigem(Pais paisOrigem) {
		this.paisOrigem = paisOrigem;
	}

//	public ArrayList<Vacinacao> getVacinacoes() {
//		return vacinacoes;
//	}
//
//	public void setVacinacoes(ArrayList<Vacinacao> vacinacoes) {
//		this.vacinacoes = vacinacoes;
//	}
}