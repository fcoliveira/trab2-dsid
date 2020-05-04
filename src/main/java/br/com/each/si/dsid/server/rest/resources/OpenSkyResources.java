package br.com.each.si.dsid.server.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

import br.com.each.si.dsid.server.controller.ServerController;
import io.swagger.annotations.Api;

@Path("/dsid/opensky")
@Api("API REST OpenSky")
public class OpenSkyResources {

	private ServerController controller;
	public OpenSkyResources(ServerController controller) {
		this.controller = controller;
	}

	@GET
	@Path("/states")
	public Response getOpenSky() {

		JSONObject response = controller.getOpenSkyStates();
		
		if(response != null) {
			return Response.ok(response.toString()).build();
					
		}
		
		return Response.status(Status.NOT_IMPLEMENTED).build();
	}
	
}
