package com.example.pet.Classes;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class Service {
	String httpGetUrl, line, deviceKey, tempMediaPath;
	StringBuilder builder;
	HttpResponse response;
	StatusLine statusLine;
	HttpEntity httpEntity;
	InputStream tempInputStream;
	FileOutputStream tempFileOutputStream;
	BufferedReader reader;
	HttpClient client;
	HttpGet httpGet;
	HttpURLConnection urlConnection;
	ByteArrayOutputStream tempByteArrayOutputStream;
	URL tempUrl;

	public String GetUser(String name, String pw) {
		httpGetUrl = "http://ezgialabicak.com/Api/Values/?userName=" + name
				+ "&&userPw=" + pw;
		builder = new StringBuilder();
		client = new DefaultHttpClient();
		httpGet = new HttpGet(httpGetUrl);
		try {
			response = client.execute(httpGet);
			statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				httpEntity = response.getEntity();
				tempInputStream = httpEntity.getContent();
				reader = new BufferedReader(new InputStreamReader(
						tempInputStream));
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
}
