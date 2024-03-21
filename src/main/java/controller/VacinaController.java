package controller;

import java.util.List;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entities.Vacina;
import service.VacinaService;

@Path("/vacina")
public class VacinaController {
	
	private VacinaService service = new VacinaService();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Vacina salvar(Vacina novaVacina) {
		return service.salvar(novaVacina);
	}
	
	@GET
	@Path("/{id}")
	public Vacina consultarPorId(@PathParam("id") int id) {
		return service.consultarPorId(id);
	}
	
	@GET
	@Path("/todas")
	public List<Vacina> consultarTodas(){
		return service.consultarTodas();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean atualizar(Vacina vacinaEditada) {
		return service.atualizar(vacinaEditada);
	}
	
	
	
	
}
