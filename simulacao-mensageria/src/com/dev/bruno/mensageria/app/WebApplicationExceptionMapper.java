package com.dev.bruno.mensageria.app;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {

	@Override
	public Response toResponse(WebApplicationException t) {
		GenericResponse response = new GenericResponse(t.getMessage());
		
		return Response.status(t.getResponse().getStatus()).entity(response).type(MediaType.APPLICATION_JSON).build();
	}
}