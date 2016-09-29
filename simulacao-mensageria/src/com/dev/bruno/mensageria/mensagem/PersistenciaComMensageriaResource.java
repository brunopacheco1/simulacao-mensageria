package com.dev.bruno.mensageria.mensagem;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
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
@Path("persistencia-com-mensageria")
@Produces(MediaType.APPLICATION_JSON)
public class PersistenciaComMensageriaResource {

	@Resource(lookup = "java:/JmsXA")
	private ConnectionFactory connectionFactory;

	@Resource(lookup = "java:jboss/queue/mensageria/inclusao")
	private Destination inclusao;
	
	@Resource(lookup = "java:jboss/queue/mensageria/atualizacao")
	private Destination atualizacao;
	
	@Resource(lookup = "java:jboss/queue/mensageria/remocao")
	private Destination remocao;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public GenericResponse add(MensagemDTO dto) throws Exception {
		try (JMSContext context = connectionFactory.createContext();) {
			context.createProducer().send(inclusao, dto);
		}

		return new GenericResponse(true, "Incluído na fila");
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public GenericResponse update(@PathParam("id") Long id, MensagemDTO dto) throws Exception {
		dto.setId(id);
		
		try (JMSContext context = connectionFactory.createContext();) {
			context.createProducer().send(atualizacao, dto);
		}

		return new GenericResponse(true, "Incluído na fila");
	}

	@DELETE
	@Path("/{id}")
	public GenericResponse remove(@PathParam("id") Long id) throws Exception {
		try (JMSContext context = connectionFactory.createContext();) {
			context.createProducer().send(remocao, id);
		}

		return new GenericResponse(true, "Incluído na fila");
	}
}