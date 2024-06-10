package model.entity;

public class Carro {

	private Integer id;
	private int idMontadora;
	private String modelo;
	private String placa;
	private Montadora montadora;
	private Integer ano;
	private Double valor;

	public Carro() {

	}

	public Carro(Integer id, int idMontadora, String modelo, String placa, Montadora montadora, Integer ano,
			Double valor) {
		super();
		this.id = id;
		this.idMontadora = idMontadora;
		this.modelo = modelo;
		this.placa = placa;
		this.montadora = montadora;
		this.ano = ano;
		this.valor = valor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getIdMontadora() {
		return idMontadora;
	}

	public void setIdMontadora(int idMontadora) {
		this.idMontadora = idMontadora;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Montadora getMontadora() {
		return montadora;
	}

	public void setMontadora(Montadora montadora) {
		this.montadora = montadora;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
}
