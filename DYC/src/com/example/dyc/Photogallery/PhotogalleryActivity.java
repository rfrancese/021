package com.example.dyc.Photogallery;

import java.util.ArrayList;
import com.example.dyc.R;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class PhotogalleryActivity extends Activity {
	
	private GridView gridView;
	private GridViewAdapter gridViewAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photogallery);
		
		gridView = (GridView) findViewById(R.id.gridView);
		gridViewAdapter = new GridViewAdapter(this, R.layout.row_grid, getData());
		gridView.setAdapter(gridViewAdapter);
		
		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Toast.makeText(PhotogalleryActivity.this, position + "#Selected",
						Toast.LENGTH_SHORT).show();
			}

		});
	}
	
	
	private ArrayList getData() {
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
