package com.example.dyc.Photogallery;

import java.util.ArrayList;
import com.example.dyc.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.view.View;

public class PhotogalleryActivity extends Activity {
	
	private GridView gridView;
//	private GridViewAdapter gridViewAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photogallery);
		
		gridView = (GridView) findViewById(R.id.grid_view);
//		gridViewAdapter = new GridViewAdapter(this, R.layout.row_grid, getData());
//		gridView.setAdapter(gridViewAdapter);
		gridView.setAdapter(new ImageAdapter(this));
		gridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                    int position, long id) {
 
                // Sending image id to FullScreenActivity
                Intent i = new Intent(getApplicationContext(), FullImageActivity.class);
                // passing array index
                i.putExtra("id", position);
                startActivity(i);
            }
        });
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList getData() {
		final ArrayList imageItems = new ArrayList();
		int[] images = {R.drawable.back, R.drawable.logo, R.drawable.ic_launcher, R.drawable.nightsky};
		for (int i = 0; i < images.length; i++) {
			Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), images[i]);
			imageItems.add(new ImageItem(bitmap, "Image "+ i));
		}
		return imageItems;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.photogallery, menu);
		return true;
	}
	
}
