package model.seletor;

public class CarroSeletor {

	private String modelo;
	private String placa;
	private String nomeMontadora;
	private Integer anoInicial;
	private Integer anoFinal;
	private Double valorInicial;
	private Double valorFinal;

	public CarroSeletor() {
		super();
	}

	public CarroSeletor(String modelo, String placa, String nomeMontadora, Integer anoInicial, Integer anoFinal,
			Double valorInicial, Double valorFinal) {
		super();
		this.modelo = modelo;
		this.placa = placa;
		this.nomeMontadora = nomeMontadora;
		this.anoInicial = anoInicial;
		this.anoFinal = anoFinal;
		this.valorInicial = valorInicial;
		this.valorFinal = valorFinal;
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

	public String getNomeMontadora() {
		return nomeMontadora;
	}

	public void setNomeMontadora(String nomeMontadora) {
		this.nomeMontadora = nomeMontadora;
	}

	public Integer getAnoInicial() {
		return anoInicial;
	}

	public void setAnoInicial(Integer anoInicial) {
		this.anoInicial = anoInicial;
	}

	public Integer getAnoFinal() {
		return anoFinal;
	}

	public void setAnoFinal(Integer anoFinal) {
		this.anoFinal = anoFinal;
	}

	public Double getValorInicial() {
		return valorInicial;
	}

	public void setValorInicial(Double valorInicial) {
		this.valorInicial = valorInicial;
	}

	public Double getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(Double valorFinal) {
		this.valorFinal = valorFinal;
	}

}
