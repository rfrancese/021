package com.example.dyc.customAdapter;

import java.util.ArrayList;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.dyc.R;
import com.example.dyc.Food.ImageDownloader;
import com.example.dyc.Food.ImageDownloaderTask;
import com.example.dyc.RowItem.RowItem2;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class AlternativeCustomAdapter extends BaseAdapter {

	private ArrayList<RowItem2> listData;
	private LayoutInflater layoutInflater;
	private final ImageDownloader imageDownloader = new ImageDownloader();
	private Context context;
	private RequestQueue rq;
	
	public AlternativeCustomAdapter(Context context, ArrayList<RowItem2> listData) {
		this.context = context;
		this.listData = listData;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return listData.size();
	}

	@Override
	public RowItem2 getItem(int position) {
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.row_layout, null);
			holder = new ViewHolder();
			holder.idtTextView = (TextView) convertView.findViewById(R.id.itemID);
			holder.titleTextView = (TextView) convertView.findViewById(R.id.title);
			holder.descriptionteTextView = (TextView) convertView.findViewById(R.id.secondLine);
			holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		RowItem2 rowItem = getItem(position);

		holder.idtTextView.setText(rowItem.getItemId());
		holder.titleTextView.setText(rowItem.getTitleString());
		holder.descriptionteTextView.setText(rowItem.getDescriptionsString());
		
		rq = Volley.newRequestQueue(context);
		
		if (holder.imageView != null) {
//			imageDownloader.download(rowItem.getImage(), holder.imageView);
//			new ImageDownloaderTask(holder.imageView).execute(rowItem.getImage());
			
			
			String url = rowItem.getImage();
			ImageRequest ir = new ImageRequest(url, new Response.Listener<Bitmap>() {
				 
			    @Override
			    public void onResponse(Bitmap response) {
			        holder.imageView.setImageBitmap(response);
			         
			    }
			}, 0, 0, null, null);
			rq.add(ir);
		}
		
		
		return convertView;
	}

	static class ViewHolder {
		TextView idtTextView;
		ImageView imageView;
		TextView titleTextView;
		TextView descriptionteTextView;
	}
}
