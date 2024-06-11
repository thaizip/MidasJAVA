package wsrest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.CupomDao;
import model.Cupom;

@Path("/cupom")
public class CupomWs {

	@POST
	@Path("/cadastrarform")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response cadastrar(@FormParam("titulo") String titulo,
							  @FormParam("descricao") String descricao,
							  @FormParam("ponto") String ponto,
							  @FormParam("dtvencimento") String dtvencimento){
		Cupom cupom = new Cupom();
		cupom.setTitulo(titulo);
		cupom.setDescricao(descricao);
		cupom.setPonto(ponto);
		cupom.setDtvencimento(dtvencimento);
		CupomDao dao = new CupomDao();
		return Response.status(200).entity(dao.salvar(cupom)).build();
	}

	@POST
	@Path("/cadastrar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response cadastrar(Cupom cupom) {
		CupomDao dao = new CupomDao();
		return Response.status(200).entity(dao.salvar(cupom)).build();
	}

	@GET
	@Path("/listartodos")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarTodos() {
		CupomDao dao = new CupomDao();
		return Response.status(200).entity(dao.listarTodos()).build();
	}

	@GET
	@Path("/getporcodigo")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPorCodigo(@QueryParam("codigo") int codigo) {
		CupomDao dao = new CupomDao();
		return Response.status(200).entity(dao.getPorCodigo(codigo)).build();
	}

	@PUT
	@Path("/alterar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response alterar(Cupom cupom) {
		CupomDao dao = new CupomDao();
		return Response.status(200).entity(dao.salvar(cupom)).build();
	}

	@DELETE
	@Path("/excluir/{codigo}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response excluir(@PathParam("codigo") int codigo) {
		CupomDao dao = new CupomDao() ;
		return Response.status(200).entity(dao.excluir(codigo)).build();
	}

}
