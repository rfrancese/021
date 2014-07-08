package com.example.dyc.Food;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.example.dyc.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;


public class FoodDetailActivity extends Activity {
	
	private String url = "http://www.wiilo.it/ws_app/schedaMangiare.php?id=";		
	private DefaultHttpClient client = new DefaultHttpClient();
	
	private ActionBar.Tab tab1, tab2, tab3;
		
	FragmentTab1 fragmentTab1 = new FragmentTab1();
	FragmentTab2 fragmentTab2 = new FragmentTab2();
	FragmentTab3 fragmentTab3 = new FragmentTab3();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_detail);
		
		Intent intent = getIntent();
		String elementIDString = intent.getStringExtra("elementID");
		
		Log.d("Element ID", elementIDString);
		
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		tab1 = actionBar.newTab().setText("Home");
		tab2 = actionBar.newTab().setText("Contatti");
		tab3 = actionBar.newTab().setText("Mappa");
		
		tab1.setTabListener(new myTabListener(fragmentTab1));
		tab2.setTabListener(new myTabListener(fragmentTab2));
		tab3.setTabListener(new myTabListener(fragmentTab3));
		
		actionBar.addTab(tab1);
		actionBar.addTab(tab2);
		actionBar.addTab(tab3);
		
		Bundle bundle = new Bundle();
		
		try {
			
			
			String updatedURLString = url + elementIDString;
			
			String xmlData = retrieve(updatedURLString);
			Serializer serializer = new Persister();        
			
			Reader reader = new StringReader(xmlData);
			Records rec = serializer.read(Records.class, reader, false);
			
			Log.d(FoodDetailActivity.class.getSimpleName(), rec.toString());
			
			// Temporary vars
			String description = "";
			String photoString ="";
			String indirizzo = "";
			String comune = "";
			String provincia = "";
			String regione = "";
			String www = "";
			String lat = "";
			String lon = "";
			
			for (FoodListElement fle: rec.getRecords()) {
				photoString = fle.getPhoto();
				description = fle.getDescription();
				indirizzo = fle.getIndirizzo();
				comune = fle.getComune();
				provincia = fle.getProvincia();
				regione = fle.getRegione();
				www = fle.getWww();
				lat = fle.getLatitudine();
				lon = fle.getLongitudine();
				
				Log.d("www", www);
				Log.d("lat", lat);
				Log.d("lon", lon);
			}
			
			//Arguments for first tab
			bundle.putString("description", description);
			bundle.putString("photo", photoString);
			
			this.fragmentTab1.setArguments(bundle);
			
			//Arguments for second tab
			bundle.putString("indirizzo", indirizzo);
			bundle.putString("comune", comune);
			bundle.putString("provincia", provincia);
			bundle.putString("regione", regione);
			bundle.putString("www", www);
			
			this.fragmentTab2.setArguments(bundle);
			
			bundle.putString("lat", lat);
			bundle.putString("lon", lon);
			this.fragmentTab3.setArguments(bundle);
			
		} catch (Exception e) {
			e.printStackTrace();
        	Toast.makeText(this, "Error Occured", Toast.LENGTH_LONG).show();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.food_detail, menu);
		return true;
	}
	
	/**
     * Get network response
     * @param url
     * @return
     */
	public String retrieve(String url) {

		HttpGet getRequest = new HttpGet(url);

		try {
			if (android.os.Build.VERSION.SDK_INT > 9) 
			{
			    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			    StrictMode.setThreadPolicy(policy);
			}
			HttpResponse getResponse = client.execute(getRequest);
			final int statusCode = getResponse.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			}
			HttpEntity getResponseEntity = getResponse.getEntity();
			if (getResponseEntity != null) {
				return EntityUtils.toString(getResponseEntity);
			}
		} 
		catch (IOException e) {
			getRequest.abort();
			Log.w(getClass().getSimpleName(), "Error for URL " + url, e);
		}
		return null;
	}

	public void dial(View view)
	{
		String toDial = "tel:3281583884";
		startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(toDial)));
	}
}
