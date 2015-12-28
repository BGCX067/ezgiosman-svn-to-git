package com.example.pet;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pet.Classes.Service;
import com.example.pet.Models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Login extends Activity {

	SharedPreferences sharedpreferences;
	public static final String MyPREFERENCES = "MyPrefs";
	public User user;
	Service tempService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		final SharedPreferences sharedpreferences = getSharedPreferences(
				MyPREFERENCES, Context.MODE_PRIVATE);

		Button btn = (Button) findViewById(R.id.btnLogin);

		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				EditText edtName = (EditText) findViewById(R.id.txtName);
				EditText edtPw = (EditText) findViewById(R.id.txtPw);
				String userName = edtName.getText().toString();
				String pw = edtPw.getText().toString();

				String a = tempService.GetUser(userName, pw);
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
							"Kullanici adi veya sifre hatal˝",
							Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	private void GetUserGson(String jsonText) {
		// user = new Gson().fromJson(jsonText, User.class);
		user = new Gson().fromJson(jsonText, new TypeToken<List<User>>() {
		}.getType());
	}
}
