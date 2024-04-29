package controller;

import java.util.List;

import exception.ControleVacinasException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.Vacina;
import model.seletor.VacinaSeletor;
import service.VacinaService;

@Path("/vacina")
public class VacinaController {
	
	private VacinaService service = new VacinaService();
	
	@POST
	@Path("/filtro")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Vacina> consultarComFiltros(VacinaSeletor seletor){
		 return service.consultarComFiltros(seletor);
	}
	
	@POST
	@Path("/inserir")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Vacina salvar(Vacina novaVacina){
		 return service.salvar(novaVacina);
	}
	
	@PUT
	@Path("/atualizar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean atualizar(Vacina vacinaEditada){
		 return service.atualizar(vacinaEditada);
	}
	
	@DELETE
	@Path("/excluir/{id}")
	public boolean excluir(@PathParam("id") int id) throws ControleVacinasException{
		 return service.excluir(id);
	}
	
	@GET
	@Path("/{id}")
	public Vacina consultarPorId(@PathParam("id") int id){
		 return service.consultarPorId(id);
	}
	
	@GET
	@Path("/todas")
	public List<Vacina> consultarTodas(){
		 return service.consultarTodas();
	}
}