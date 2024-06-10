package controller;

import exception.ControleVacinasException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.Montadora;
import service.MontadoraService;

@Path("/montadora")
public class MontadoraController {

	private MontadoraService service = new MontadoraService();

	@POST
	@Path("/inserir")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Montadora salvar(Montadora novaMontadora) throws ControleVacinasException {
		return service.inserir(novaMontadora);
	}
	
	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Montadora consultadorPorId(@PathParam("id") int id) throws ControleVacinasException {
		return service.consultarPorId(id);
	}
}
