package com.example.dyc.Photogallery;

import java.util.ArrayList;
import com.example.dyc.R;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressWarnings("rawtypes")
public class GridViewAdapter extends ArrayAdapter {

	private Context context;
	private int layoutResID;
	private ArrayList data = new ArrayList();
	
	@SuppressWarnings("unchecked")
	public GridViewAdapter(Context context, int layoutResID, ArrayList data) {
		super(context,  layoutResID, data);
		this.context = context;
		this.layoutResID = layoutResID;
		this.data = data;
		
	}
	
	@Override
	public View getView(int position, View converterView, ViewGroup parent) {
		View row = converterView;
		ViewHolder viewHolder = null;
		
		if (row == null) {
			LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
			row = layoutInflater.inflate(layoutResID, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.imageTitle = (TextView) row.findViewById(R.id.text);
			viewHolder.image = (ImageView) row.findViewById(R.id.image);
			row.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) row.getTag();
		}
		
		ImageItem item = (ImageItem) data.get(position);
		viewHolder.image.setImageBitmap(item.getImage());
		viewHolder.imageTitle.setText(item.getTitle());
		
		return row;
	}

	static class ViewHolder {
		ImageView image;
		TextView imageTitle;
	}

	public ArrayList getData() {
		return data;
	}

	public void setData(ArrayList data) {
		this.data = data;
	}
	
	
}
