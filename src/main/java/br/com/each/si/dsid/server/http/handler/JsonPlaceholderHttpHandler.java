package br.com.each.si.dsid.server.http.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.each.si.dsid.server.model.Comment;

public class JsonPlaceholderHttpHandler {

	private static final String SERVICE_URL = "https://jsonplaceholder.typicode.com/comments";
	private URL restServiceUrl;

	public JsonPlaceholderHttpHandler() throws MalformedURLException {
		this.restServiceUrl = new URL(SERVICE_URL);
	}

	public JSONArray getComments() throws IOException {

		HttpURLConnection conn = (HttpURLConnection) restServiceUrl.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() == 200) {

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			StringBuilder sb = new StringBuilder();

			String output;
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}

			conn.disconnect();

			return new JSONArray(sb.toString());

		} else {

			throw new IOException(conn.getResponseMessage());
		}
	}

	public String postComment(Comment newComment) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		String commentAsString = mapper.writeValueAsString(newComment);

		HttpURLConnection conn = (HttpURLConnection) restServiceUrl.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");

		OutputStream os = conn.getOutputStream();
		os.write(commentAsString.getBytes());
		os.flush();

		if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
			throw new IOException("Connection failed. HTTP code : " + conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

		String output;
		StringBuilder sb = new StringBuilder();
		while ((output = br.readLine()) != null) {
			sb.append(output);
		}

		conn.disconnect();

		return sb.toString();
	}

	public boolean updateComment(String commentId) {

		return false;
	}

	public boolean deleteComment(String commentId) {

		return false;
	}

}
