package com.sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;

public class JavaHttpRequests {
	public static void main(String[] args) {
		try {
			getExample();
			postExample();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getExample() {
		System.out.println("GET EXAMPLE");
		String url = "http://api.ipinfodb.com/v3/ip-city/?key=cf621756269809fb1d75e4910f75f2590a3caaf829201f257c21f2a102fe3e80&ip=74.125.45.100&format=json";
		URL obj;
		try {
			obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			// optional default is GET
			con.setRequestMethod("GET");
			// add request header
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			// print in String
			System.out.println(response.toString());
			// Read JSON response and print
			JSONObject myResponse = new JSONObject(response.toString());
			System.out.println("result after Reading JSON Response");
			System.out.println("statusCode- " + myResponse.getString("statusCode"));
			System.out.println("statusMessage- " + myResponse.getString("statusMessage"));
			System.out.println("ipAddress- " + myResponse.getString("ipAddress"));
			System.out.println("countryCode- " + myResponse.getString("countryCode"));
			System.out.println("countryName- " + myResponse.getString("countryName"));
			System.out.println("regionName- " + myResponse.getString("regionName"));
			System.out.println("cityName- " + myResponse.getString("cityName"));
			System.out.println("zipCode- " + myResponse.getString("zipCode"));
			System.out.println("latitude- " + myResponse.getString("latitude"));
			System.out.println("longitude- " + myResponse.getString("longitude"));
			System.out.println("timeZone- " + myResponse.getString("timeZone"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// TEST SITE = https://ptsv2.com/s/whatisthis.html

	public static void postExample() throws Exception {
		System.out.println("POST EXAMPLE");
		URL url = new URL("https://ptsv2.com/t/i8t52-1593647190/post");
		Map<String, String> params = new LinkedHashMap<>();
		params.put("name", "Gera");
		params.put("email", "gerardo.diaz@gmail.com");
		params.put("CODE", "1234");
		params.put("message", "Testing post success");
		StringBuilder postData = new StringBuilder();
		for (Object param : params.keySet()) {
			if (postData.length() != 0)
				postData.append('&');
			postData.append(URLEncoder.encode(param.toString(), "UTF-8"));
			postData.append('=');
			postData.append(URLEncoder.encode(String.valueOf(params.get(param).toString()), "UTF-8"));
		}
		byte[] postDataBytes = postData.toString().getBytes("UTF-8");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
		conn.setDoOutput(true);
		conn.getOutputStream().write(postDataBytes);
		Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		StringBuilder sb = new StringBuilder();
		for (int c; (c = in.read()) >= 0;)
			sb.append((char) c);
		String response = sb.toString();
		System.out.println(response);
		// JSONObject myResponse = new JSONObject(response.toString());
		// System.out.println("result after Reading JSON Response");
		// System.out.println("origin- "+myResponse.getString("origin"));
		// System.out.println("url- "+myResponse.getString("url"));
		// JSONObject form_data = myResponse.getJSONObject("form");
		// System.out.println("CODE- "+form_data.getString("CODE"));
		// System.out.println("email- "+form_data.getString("email"));
		// System.out.println("message- "+form_data.getString("message"));
		// System.out.println("name"+form_data.getString("name"));
	}
}