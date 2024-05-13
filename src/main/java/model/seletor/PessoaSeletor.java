package model.seletor;

import java.time.LocalDate;

public class PessoaSeletor {

	private String nomePessoa;
	private String cpf;
	private String nomePais;
	private LocalDate dataNascimento;
	private char sexoPessoa;
	private int tipoPessoa;

	public PessoaSeletor() {
		super();

	}

	public PessoaSeletor(String nomePessoa, String cpf, String nomePais, LocalDate dataNascimento, char sexoPessoa,
			int tipoPessoa) {
		super();
		this.nomePessoa = nomePessoa;
		this.cpf = cpf;
		this.nomePais = nomePais;
		this.dataNascimento = dataNascimento;
		this.sexoPessoa = sexoPessoa;
		this.tipoPessoa = tipoPessoa;
	}

	public char getSexoPessoa() {
		return sexoPessoa;
	}

	public void setSexoPessoa(char sexoPessoa) {
		this.sexoPessoa = sexoPessoa;
	}

	public int getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(int tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNomePais() {
		return nomePais;
	}

	public void setNomePais(String nomePais) {
		this.nomePais = nomePais;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}