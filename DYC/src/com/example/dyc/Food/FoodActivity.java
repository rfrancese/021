package com.example.dyc.Food;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dyc.R;
import com.example.dyc.customAdapter.CustomAdapter;
import com.example.dyc.RowItem.RowItem;;

public class FoodActivity extends Activity {
	
	private static final String url = "http://www.wiilo.it/ws_app/listaMangiare.php";		
	private DefaultHttpClient client = new DefaultHttpClient();	
	
	private ArrayList<String> idsList = new ArrayList<String>();
	private ArrayList<String> titlesList = new ArrayList<String>();
	private ArrayList<String> photoList = new ArrayList<String>();
	private ArrayList<String> descriptionList = new ArrayList<String>();
	private ListView listView;
	private ArrayList<RowItem> rowItems;
//	private ArrayList<RowItem2> rowItems;
	private Bitmap thumb = null;
		
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	    	
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_second);
	        
	        try {
				String xmlData = retrieve(url);
				Serializer serializer = new Persister();        
				
				Reader reader = new StringReader(xmlData);
				Records rec = serializer.read(Records.class, reader, false);
				Log.d(FoodActivity.class.getSimpleName(), rec.toString());
				for (FoodListElement fle: rec.getRecords()) {
					idsList.add(Integer.toString(fle.getId()));
					titlesList.add(fle.getTitolo());
					photoList.add(fle.getPhoto());
					descriptionList.add(fle.getDescription());
				}
				
				String[] idsTemp = idsList.toArray(new String[idsList.size()]);
				String[] titleTemp = titlesList.toArray(new String[titlesList.size()]);
				String[] descrTemp = descriptionList.toArray(new String[descriptionList.size()]);
				String[] photoTemp = photoList.toArray(new String[photoList.size()]);
//				
				rowItems = new ArrayList<RowItem>();
////				rowItems = new ArrayList<RowItem2>();
				for (int i = 0; i < idsTemp.length; i++) {
					thumb = getBitmapFromURL(photoTemp[i]);
										
//					RowItem item = new RowItem(idsTemp[i], thumb, titleTemp[i], Html.fromHtml(descrTemp[i]).toString());
					RowItem item = new RowItem(idsTemp[i], thumb, titleTemp[i], descrTemp[i]);
//					RowItem2 item = new RowItem2(idsTemp[i], photoTemp[i], titleTemp[i], Html.fromHtml(descrTemp[i]).toString());
					rowItems.add(item);
					
					//Speeding up the loading
					if (i == 2) {
						break;
					}
				}
				
				listView = (ListView) findViewById(R.id.list);
				CustomAdapter customAdapter = new CustomAdapter(this, R.layout.row_layout, rowItems);
//				AlternativeCustomAdapter customAdapter = new AlternativeCustomAdapter(this, rowItems);
				listView.setAdapter(customAdapter);
				listView.setOnItemClickListener(new OnItemClickListener() {
					
					@Override
			        public void onItemClick(AdapterView<?> parent, View view,
			            int position, long id) {

			            // selected item
			           String selected = ((TextView) view.findViewById(R.id.itemID)).getText().toString();
			           Log.i("selected", selected);
			           Intent intent = new Intent(getApplicationContext(), FoodDetailActivity.class);
			           intent.putExtra("elementID", selected);
			           startActivity(intent);
			        }
			      });
			
	        }
	        catch (Exception e) {
	        	e.printStackTrace();
	        	Toast.makeText(this, "Error Occured", Toast.LENGTH_LONG).show();
			}
	        
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