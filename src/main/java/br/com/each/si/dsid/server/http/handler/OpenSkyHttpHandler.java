package br.com.each.si.dsid.server.http.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;

public class OpenSkyHttpHandler {

	// lat lon bounding box obtained from: https://gist.github.com/graydon/11198540
	private static final String SERVICE_URL = "https://opensky-network.org/api/states/all?lamin=-34.7299934555&lomin=-73.9872354804&lamax=5.24448639569&lomax=-33.7683777809";
	
	private URL url;
	public OpenSkyHttpHandler() throws MalformedURLException {
	
		this.url = new URL(SERVICE_URL);
	}
	
	public JSONObject getStates() throws IOException {

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		
		if(conn.getResponseCode() == 200) {
		
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			StringBuilder sb = new StringBuilder();
			
			String output;
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}
			
			conn.disconnect();
			
			return new JSONObject(sb.toString());
		
		} else {
			
			throw new IOException(conn.getResponseMessage());
		}
	}
}
