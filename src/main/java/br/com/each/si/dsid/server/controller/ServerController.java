package br.com.each.si.dsid.server.controller;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.each.si.dsid.server.http.handler.JsonPlaceholderHttpHandler;
import br.com.each.si.dsid.server.http.handler.OpenSkyHttpHandler;
import br.com.each.si.dsid.server.model.Comment;

public class ServerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServerController.class);
	private OpenSkyHttpHandler openSkyHttpHandler;
	private JsonPlaceholderHttpHandler jsonPlaceholderHttpHandler;

	public ServerController(JsonPlaceholderHttpHandler jsonPlaceholderHttpHandler, OpenSkyHttpHandler openSkyHttpHandler) {

		LOGGER.debug("Built new instance");
		this.jsonPlaceholderHttpHandler = jsonPlaceholderHttpHandler;
		this.openSkyHttpHandler = openSkyHttpHandler;
		
	}

	public JSONObject getOpenSkyStates() throws IOException {

		return openSkyHttpHandler.getStates();

	}

	public String deleteComment(int commentId) throws IOException {
		
		return jsonPlaceholderHttpHandler.deleteComment(commentId);
	}
	
	public String postComment(Comment comment) throws IOException {

		return jsonPlaceholderHttpHandler.postComment(comment);
	}

	public String updateComment(Comment comment) throws IOException {
		
		return jsonPlaceholderHttpHandler.updateComment(comment);
	}
	
	public JSONArray getComments() throws IOException {

		return jsonPlaceholderHttpHandler.getComments();
	}

}
