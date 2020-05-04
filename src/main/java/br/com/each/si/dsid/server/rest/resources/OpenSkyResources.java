package br.com.each.si.dsid.server.rest.resources;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.each.si.dsid.server.controller.ServerController;
import io.swagger.annotations.Api;

@Path("/dsid/opensky")
@Api("API REST OpenSky")
public class OpenSkyResources {

	private static final Logger LOGGER = LoggerFactory.getLogger(OpenSkyResources.class);
	private ServerController controller;

	public OpenSkyResources(ServerController controller) {
		this.controller = controller;
	}

	@GET
	@Path("/states")
	public Response getOpenSky() {

		try {
			JSONObject response = controller.getOpenSkyStates();
			return Response.ok(response.toString()).build();
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			return Response.status(Status.NOT_IMPLEMENTED).build();
		}

	}

}
