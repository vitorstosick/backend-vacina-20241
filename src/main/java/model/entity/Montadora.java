package model.entity;

import java.time.LocalDate;

public class Montadora {

	private int id;
	private String nome;
	private String pais;
	private String presidente;
	private LocalDate dataFundacao;

	public Montadora() {
		super();
	}

	public Montadora(int id, String nome, String pais, String presidente, LocalDate dataFundacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.pais = pais;
		this.presidente = presidente;
		this.dataFundacao = dataFundacao;
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

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getPresidente() {
		return presidente;
	}

	public void setPresidente(String presidente) {
		this.presidente = presidente;
	}

	public LocalDate getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(LocalDate dataFundacao) {
		this.dataFundacao = dataFundacao;
	}
}
