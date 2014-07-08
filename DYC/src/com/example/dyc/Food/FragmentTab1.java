package com.example.dyc.Food;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.example.dyc.R;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentTab1 extends Fragment {
	
	private ImageView imageView;
	private String descripString = "";
	private String photoString = "";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.home_tab, container, false);
		
		descripString = getArguments().getString("description");
		photoString = getArguments().getString("photo");
		imageView = (ImageView) view.findViewById(R.id.imageView11);
		
		Bitmap photoBitmap = getBitmapFromURL(photoString);
		imageView.setImageBitmap(photoBitmap);
		
//		RequestQueue rq = Volley.newRequestQueue(getActivity().getApplicationContext());
//		String url = photoString;
//		ImageRequest ir = new ImageRequest(url, new Response.Listener<Bitmap>() {
//			 
//		    @Override
//		    public void onResponse(Bitmap response) {
//		        imageView.setImageBitmap(response);
//		         
//		    }
//		}, 0, 0, null, null);
//		rq.add(ir);
		
		TextView textview = (TextView) view.findViewById(R.id.textView);
		textview.setMovementMethod(new ScrollingMovementMethod());
		textview.setText(Html.fromHtml(descripString));
		return view;
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
