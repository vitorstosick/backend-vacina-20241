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
import model.entity.Vacinacao;
import model.seletor.VacinacaoSeletor;
import service.VacinacaoService;

@Path("/vacinacao")
public class VacinacaoController {
	
	private VacinacaoService service = new VacinacaoService();
	
	@POST
	@Path("/inserir")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Vacinacao salvar(Vacinacao novaVacinacao) throws ControleVacinasException{
		 return service.salvar(novaVacinacao);
	}
	
	@PUT
	@Path("/atualizar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean atualizar(Vacinacao vacinacaoEditada) throws ControleVacinasException{
		 return service.atualizar(vacinacaoEditada);
	}
	
	@DELETE
	@Path("/excluir/{id}")
	public boolean excluir(@PathParam("id") int id){
		 return service.excluir(id);
	}
	
	@GET
	@Path("/{id}")
	public Vacinacao consultarPorId(@PathParam("id") int id){
		 return service.consultarPorId(id);
	}
	
	@GET
	@Path("/todas")
	public List<Vacinacao> consultarTodas(){
		 return service.consultarTodas();
	}
	
	@GET
	@Path("/pessoa/{idPessoa}")
	public List<Vacinacao> consultarPorIdPessoa(@PathParam("idPessoa") int idPessoa){
		 return service.consultarPorIdPessoa(idPessoa);
	}
	
	@POST
	@Path("/filtro")
	public List<Vacinacao> consultarComSeletor(VacinacaoSeletor seletor){
		return service.consultarComSeletor(seletor);
	}
}