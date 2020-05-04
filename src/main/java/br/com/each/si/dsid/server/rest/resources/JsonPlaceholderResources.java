package br.com.each.si.dsid.server.rest.resources;

import java.io.IOException;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.each.si.dsid.server.controller.ServerController;
import br.com.each.si.dsid.server.model.Comment;
import io.swagger.annotations.Api;

@Path("/dsid/jsonplaceholder")
@Api("API REST JSONPlaceholder")
public class JsonPlaceholderResources {

	private ServerController controller;
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonPlaceholderResources.class);
	
	public JsonPlaceholderResources(ServerController controller) {
		this.controller = controller;
	}

	@GET
	@Path("/comments")
	public Response get() {
		
		try {
			JSONArray comments = this.controller.getComments();
			return Response.ok(comments.toString()).build();
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			return Response.status(Status.NOT_IMPLEMENTED).build();
		}
	}
	
	@POST
	@Path("/comments")
	public Response post(Comment comment) {
		
		try {
			String responseContent = this.controller.postComment(comment);
			return Response.ok(responseContent).build();
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
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
