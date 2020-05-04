package br.com.each.si.dsid.server.rest.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.each.si.dsid.server.controller.ServerController;
import io.swagger.annotations.Api;

@Path("/dsid/jsonplaceholder")
@Api("API REST JSONPlaceholder")
public class JsonPlaceholderResources {

	private ServerController controller;
	public JsonPlaceholderResources(ServerController controller) {
		this.controller = controller;
	}

	@GET
	@Path("/comments")
	public Response get() {
		
		return Response.status(Status.NOT_IMPLEMENTED).build();
	}
	
	@POST
	@Path("/comments")
	public Response post() {
		
		return Response.status(Status.NOT_IMPLEMENTED).build();
	}
	
	@PUT
	@Path("/comments")
	public Response put() {
		
		return Response.status(Status.NOT_IMPLEMENTED).build();
	}
	
	@DELETE
	@Path("/comments")
	public Response delete() {
		
		return Response.status(Status.NOT_IMPLEMENTED).build();
	}
}
