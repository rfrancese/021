package com.example.dyc;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			
		}
		else {
			AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alert.setTitle("Warning");
			alert.setMessage("No Internet connection. Some content may be not available.");
			alert.setCancelable(true);
			AlertDialog alertDialog = alert.create();
			alertDialog.show();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void showMe(View v) {
		Uri uri = Uri.parse("geo:38.8891,-77.0492");
		startActivity(new Intent(Intent.ACTION_VIEW, uri));
	}
	
	public void openNewActivity(View v) {
		Intent intent = new Intent(this, SecondActivity.class);
		startActivity(intent);
	}

}
