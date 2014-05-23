package com.example.dyc.Sleep;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.example.dyc.R;
import com.example.dyc.Records;
import com.example.dyc.Food.FoodListElement;
import com.example.dyc.RowItem.RowItem;
import com.example.dyc.customAdapter.CustomAdapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Html;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

public class SleepActivity extends Activity {
	
	private static final String url = "http://www.wiilo.it/ws_app/listaDormire.php";		
	private DefaultHttpClient client = new DefaultHttpClient();	
	
	private ArrayList<String> titlesList = new ArrayList<String>();
	private ArrayList<String> photoList = new ArrayList<String>();
	private ArrayList<String> descriptionList = new ArrayList<String>();
	private ListView listView;
	private List<RowItem> rowItems;
	private Bitmap thumb = null;
		
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	    	
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_second);
	        
	        try {
				String xmlData = retrieve(url);
				Serializer serializer = new Persister();        
				
				Reader reader = new StringReader(xmlData);
				Records rec = serializer.read(Records.class, reader, false);
//				Log.d(FoodActivity.class.getSimpleName(), rec.toString());
				for (FoodListElement fle: rec.getRecords()) {
					titlesList.add(fle.getTitolo());
					photoList.add(fle.getPhoto());
					descriptionList.add(fle.getDescription());
				}
				
				String[] titleTemp = titlesList.toArray(new String[titlesList.size()]);
				String[] descrTemp = descriptionList.toArray(new String[descriptionList.size()]);
				String[] photoTemp = photoList.toArray(new String[photoList.size()]);
				
				rowItems = new ArrayList<RowItem>();
				
				for (int i = 0; i < titleTemp.length; i++) {					
					thumb = getBitmapFromURL(photoTemp[i]);
					RowItem item = new RowItem(thumb, titleTemp[i], Html.fromHtml(descrTemp[i]).toString());
					rowItems.add(item);
				}
				
				listView = (ListView) findViewById(R.id.list);
				CustomAdapter customAdapter = new CustomAdapter(this, R.layout.row_layout, rowItems);
				listView.setAdapter(customAdapter);
			
	        }
	        catch (Exception e) {
	        	e.printStackTrace();
	        	Toast.makeText(this, "Error Occured", Toast.LENGTH_LONG).show();
			}
	        
	    }
	    
	    
	    
	    /**
	     * Get the network response
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
	    
		public static Bitmap getBitmapFromURL(String src) {
	        try {
	            URL url = new URL(src);
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setDoInput(true);
	            connection.connect();
	            java.io.InputStream input = connection.getInputStream();
	            Bitmap myBitmap = BitmapFactory.decodeStream(input);
	            Log.d("Testing", "image loaded  "+myBitmap);
//	          myBitmap = Bitmap.createBitmap(100, 50, Config.ARGB_8888);
	            myBitmap = Bitmap.createScaledBitmap(myBitmap,(int)(myBitmap.getWidth()), (int)(myBitmap.getHeight()), true);
	            return myBitmap;
	        } catch (IOException e) {
	            Log.d("Testing", "exception is getting the image "+e.toString());
	            e.printStackTrace();
	            return null;
	        }
	    }
}
