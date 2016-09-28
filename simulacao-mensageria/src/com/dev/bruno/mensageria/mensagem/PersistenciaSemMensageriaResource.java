package com.dev.bruno.mensageria.mensagem;

import java.util.concurrent.ThreadLocalRandom;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dev.bruno.mensageria.app.GenericResponse;

@Stateless
@Path("persistencia-sem-mensageria")
@Produces(MediaType.APPLICATION_JSON)
public class PersistenciaSemMensageriaResource {

	@Inject
	private MensagemService mensagemService;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public GenericResponse add(MensagemDTO dto) throws Exception {
		mensagemService.add(dto);
		
		Long timeToSleep = ThreadLocalRandom.current().nextLong(1000, 60000);
		
		System.out.println("Processando " + timeToSleep + " ms");
		
		Thread.sleep(timeToSleep);
		
		return new GenericResponse(true);
	}
	
	@PUT
	@Path("/{id}") 
	@Consumes(MediaType.APPLICATION_JSON)
	public GenericResponse update(@PathParam("id") Long id, MensagemDTO dto) throws Exception {
		mensagemService.update(id, dto);
		
		Long timeToSleep = ThreadLocalRandom.current().nextLong(1000, 60000);
		
		System.out.println("Processando " + timeToSleep + " ms");
		
		Thread.sleep(timeToSleep);
		
		return new GenericResponse(true);
	}
	
	@DELETE
	@Path("/{id}")
	public GenericResponse remove(@PathParam("id") Long id) throws Exception {
		mensagemService.remove(id);
		
		Long timeToSleep = ThreadLocalRandom.current().nextLong(1000, 60000);
		
		System.out.println("Processando " + timeToSleep + " ms");
		
		Thread.sleep(timeToSleep);
		
		return new GenericResponse(true);
	}
	
	public Long count(String queryStr) {
		return mensagemService.count(queryStr);
	}
}