package model.entities;

import java.time.LocalDate;

public class Pessoa {

	private int id;
	private String nome;
	private LocalDate dataNascimento;
	private String sexo;
	private String cpf;
	private int tipo;
	private Pais paisOrigem;

	// Comentado para evitar confusões durante a prova
	// private ArrayList<Vacinacao> vacinacoes;

	public Pessoa(int id, String nome, LocalDate dataNascimento, String sexo, String cpf, int tipo, Pais paisOrigem) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.cpf = cpf;
		this.tipo = tipo;
		this.paisOrigem = paisOrigem;
		//this.vacinacoes = vacinacoes;
	}

	public Pessoa() {
		super();
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

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

}
