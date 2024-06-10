package controller;

import java.util.List;

import exception.ControleVacinasException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.Carro;
import model.seletor.CarroSeletor;
import service.CarroService;

@Path("/carro")
public class CarroController {

	CarroService service = new CarroService();

	@POST
	@Path("/filtro")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Carro> consultarComFiltros(CarroSeletor seletor) throws ControleVacinasException {
		return service.consultadorComFiltros(seletor);
	}
}
