package service;

import exception.ControleVacinasException;
import model.entity.Montadora;
import model.entity.Vacina;
import model.repository.MontadoraRepository;

public class MontadoraService {

	MontadoraRepository montadoraRepository = new MontadoraRepository();

	public Montadora inserir(Montadora novaMontadora) throws ControleVacinasException {
		validarCamposObrigatorios(novaMontadora);

		return montadoraRepository.inserir(novaMontadora);
	}

	private void validarCamposObrigatorios(Montadora m) throws ControleVacinasException {
		String mensagemValidacao = "";
		if (m.getNome() == null || m.getNome().isEmpty()) {
			mensagemValidacao += " - informe o nome \n";
		}
		if (m.getPais() == null || m.getPais().isEmpty()) {
			mensagemValidacao += " - informe o País \n";
		}
		if (m.getPresidente() == null || m.getPresidente().isEmpty()) {
			mensagemValidacao += " - informe o Presidente da Montadora";
		}

		if (m.getDataFundacao() == null) {
			mensagemValidacao += " - informe a data de fundação";
		}
		
		if (!mensagemValidacao.isEmpty()) {
			throw new ControleVacinasException("Preencha o(s) seguinte(s) campo(s) \n " + mensagemValidacao);
		}

	}
	
	public Montadora consultarPorId(int id) {
		return montadoraRepository.consultarPorId(id);
	}
}
