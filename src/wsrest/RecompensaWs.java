package wsrest;

import dao.RecompensaDao;
import model.Recompensa;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/recompensa")
public class RecompensaWs {

	@POST
	@Path("/cadastrarform")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response cadastrar(@FormParam("titulo") String titulo,
							  @FormParam("descricao") String descricao,
							  @FormParam("ponto") String ponto,
							  @FormParam("dtvencimento") String dtvencimento){
		Recompensa recompensa = new Recompensa();
		recompensa.setTitulo(titulo);
		recompensa.setDescricao(descricao);
		recompensa.setPonto(ponto);
		recompensa.setDtvencimento(dtvencimento);
		RecompensaDao dao = new RecompensaDao();
		return Response.status(200).entity(dao.salvar(recompensa)).build();
	}

	@POST
	@Path("/cadastrar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response cadastrar(Recompensa recompensa) {
		RecompensaDao dao = new RecompensaDao();
		return Response.status(200).entity(dao.salvar(recompensa)).build();
	}

	@GET
	@Path("/listartodos")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarTodos() {
		RecompensaDao dao = new RecompensaDao();
		return Response.status(200).entity(dao.listarTodos()).build();
	}

	@GET	
	@Path("/getporcodigo")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPorCodigo(@QueryParam("codigo") int codigo) {
		RecompensaDao dao = new RecompensaDao();
		return Response.status(200).entity(dao.getPorCodigo(codigo)).build();
	}

	@PUT
	@Path("/alterar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response alterar(Recompensa recompensa) {
		RecompensaDao dao = new RecompensaDao();
		return Response.status(200).entity(dao.salvar(recompensa)).build();
	}

	@DELETE
	@Path("/excluir/{codigo}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response excluir(@PathParam("codigo") int codigo) {
		RecompensaDao dao = new RecompensaDao() ;
		return Response.status(200).entity(dao.excluir(codigo)).build();
	}

}
