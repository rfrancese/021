package com.example.dyc;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.omg.CORBA.portable.InputStream;

import com.android.volley.*;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class VolleyTest extends Activity {
	
	private ImageView iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_volley_test);
		iv = (ImageView) findViewById(R.id.iv);
		
		RequestQueue rq = Volley.newRequestQueue(this);
		String url = "http://www.wiilo.it/_img_users_app/stuzzico-restaurant-interni.jpg";
		ImageRequest ir = new ImageRequest(url, new Response.Listener<Bitmap>() {
			 
		    @Override
		    public void onResponse(Bitmap response) {
		        iv.setImageBitmap(response);
		         
		    }
		}, 0, 0, null, null);
		rq.add(ir);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.volley_test, menu);
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
	
	public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = (InputStream) connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.d("Testing", "image loaded  "+myBitmap);
//          myBitmap = Bitmap.createBitmap(100, 50, Config.ARGB_8888);
            myBitmap = Bitmap.createScaledBitmap(myBitmap,(int)(myBitmap.getWidth()), (int)(myBitmap.getHeight()), true);
            return myBitmap;
        } catch (IOException e) {
            Log.d("Testing", "exception is getting the image "+e.toString());
            e.printStackTrace();
            return null;
        }
    }

}
