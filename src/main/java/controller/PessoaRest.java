package controller;

import java.util.List;

import exception.PessoaException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entities.Pessoa;
import service.PessoaService;

@Path("/pessoa")
public class PessoaRest {

	private PessoaService service = new PessoaService();
	
	@POST
	@Path("/cadastrar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Pessoa salvar(Pessoa novaPessoa) throws PessoaException {
		return service.salvar(novaPessoa);
	}
	
	@DELETE
	@Path("/{id}")
	public boolean excluir(@PathParam("id") int id) {
		return service.excluir(id);
	}
	
	@GET
	@Path("/todas")
	public List<Pessoa> consultarTodas(){
		return service.listarTodos();
	}
	
	
}
