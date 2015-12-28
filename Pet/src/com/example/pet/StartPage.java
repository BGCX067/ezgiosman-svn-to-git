package com.example.pet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class StartPage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_page);
		Intent login = new Intent("com.example.pet.LOGIN");
		startActivity(login);
	}
}
