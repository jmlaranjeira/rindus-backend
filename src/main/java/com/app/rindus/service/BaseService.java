package com.app.rindus.service;

import com.app.rindus.utils.JSONReader;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public abstract class BaseService<D> {

	private String url = "https://my-json-server.typicode.com/jmlaranjeira/typicode/";

	String baseUrL() {
		return this.url;
	}

	public String save(D postDTO) {
		JSONObject postJSON = new JSONObject(postDTO);
		return this.send("POST", postJSON);
	}

	public String update(D putDTO) {
		JSONObject postJSON = new JSONObject(putDTO);
		return this.send("PUT", postJSON);
	}

	public String findAll() {
		try {
			JSONArray resp = JSONReader.readJsonFromUrl(this.baseUrL());
			if (resp.length() < 1) return "No data available";
			return resp.toString();
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
			return "Endpoint not found";
		}
	}

	private String send(String method, JSONObject jsonObject) {
		byte[] out = jsonObject.toString().getBytes(StandardCharsets.UTF_8);
		int length = out.length;

		try {
			URL url = new URL(this.baseUrL());
			URLConnection con = url.openConnection();
			HttpURLConnection http = (HttpURLConnection) con;
			http.setRequestMethod(method); // PUT is another valid option
			http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			http.setDoOutput(true);
			http.setFixedLengthStreamingMode(length);
			try (OutputStream os = http.getOutputStream()) {
				os.write(out);
			}

			int responseCode = http.getResponseCode();
			switch (responseCode) {
				case 200:
				case 201:
					BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()));
					StringBuilder sb = new StringBuilder();
					String line;
					while ((line = br.readLine()) != null) {
						sb.append(line + "\n");
					}
					br.close();
					return sb.toString();
			}

			return "POST Response Code :: " + responseCode;
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
			return "Endpoint not found";
		}
	}


}
