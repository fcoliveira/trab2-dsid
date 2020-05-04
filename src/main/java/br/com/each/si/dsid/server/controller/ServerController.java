package br.com.each.si.dsid.server.controller;

import java.io.IOException;

import org.json.JSONObject;

import br.com.each.si.dsid.server.http.handler.JsonPlaceholderHttpHandler;
import br.com.each.si.dsid.server.http.handler.OpenSkyHttpHandler;

public class ServerController {

	private OpenSkyHttpHandler openSkyHttpHandler;
	private JsonPlaceholderHttpHandler jsonPlaceholderHttpHandler;

	public ServerController(JsonPlaceholderHttpHandler jsonPlaceholderHttpHandler, OpenSkyHttpHandler openSkyHttpHandler) {

		this.jsonPlaceholderHttpHandler = jsonPlaceholderHttpHandler;
		this.openSkyHttpHandler = openSkyHttpHandler;
		
	}

	public JSONObject getOpenSkyStates() {

		try {
		
			return openSkyHttpHandler.getStates();

		} catch (IOException e) {
			
			System.out.println(e.getMessage());
			return null;
		}
	}

}
