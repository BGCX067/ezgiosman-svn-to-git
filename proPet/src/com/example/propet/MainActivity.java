package com.example.propet;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MainActivity extends ActionBarActivity {

	SharedPreferences sharedpreferences;
	public static final String MyPREFERENCES = "MyPrefs";
	public User user;

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final SharedPreferences sharedpreferences = getSharedPreferences(
				MyPREFERENCES, Context.MODE_PRIVATE);

		// String a = GetUser();
		// GetUserGson(a);

		Button btn = (Button) findViewById(R.id.btnLogin);

		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				EditText edtName = (EditText) findViewById(R.id.txtName);
				EditText edtPw = (EditText) findViewById(R.id.txtPw);
				String userName = edtName.getText().toString();
				String pw = edtPw.getText().toString();

				String a = GetUser(userName, pw);
				GetUserGson(a);

				if (user != null) {
					finish();
					// startActivity(new Intent(
					// "android.intent.action.mainview"));
					// Editor editor = sharedpreferences.edit();
					// editor.putString("UserName", userName);
					// editor.commit();
					Toast.makeText(getApplicationContext(), "Girdiii",
							Toast.LENGTH_LONG).show();

				} else {
					Toast.makeText(getApplicationContext(),
							"Kullanýcý adý veya Þifre hatalý",
							Toast.LENGTH_LONG).show();
				}

			}
		});

		// String a = GetPlayer();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public String GetUser(String name, String pw) {
		// þimdilik var olan player
		// deviceKey = "FGYUKS7J";
		// httpGetUrl =
		// "http://smartsign-api.azurewebsites.net/api/Values/?code="
		// + deviceKey;
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

	private void GetUserGson(String jsonText) {
		//user = new Gson().fromJson(jsonText, User.class);
		 user = new Gson().fromJson(jsonText, new TypeToken<List<User>>() {
		 }.getType());

	}

}
