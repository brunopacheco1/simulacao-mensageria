package com.dev.bruno.mensageria.mensagem;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("mensagem")
@Produces(MediaType.APPLICATION_JSON)
public class MensagemResource {

	@Inject
	private MensagemService mensagemService;
	
	@GET
	public List<MensagemDTO> list(@QueryParam("query") String queryStr, @QueryParam("start") Integer start, @QueryParam("limit") Integer limit) throws Exception {
		return mensagemService.list(queryStr, start, limit);
	}
	
	@GET
	@Path("/{id}") 
	public MensagemDTO get(@PathParam("id") Long id) throws Exception {
		return mensagemService.get(id);
	}
}
