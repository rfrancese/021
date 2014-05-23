package com.example.dyc.customAdapter;

import java.util.List;
import com.example.dyc.R;
import com.example.dyc.RowItem.RowItem;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ImageView;

public class CustomAdapter extends ArrayAdapter<RowItem> {
	
	Context context;
	
	public CustomAdapter(Context context, int resourceID, List<RowItem> items) {
		super(context, resourceID, items);
		this.context = context;
	}
	
	
	private class ViewHolder {
		ImageView imageView;
		TextView titleTextView;
		TextView descriptionteTextView;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder viewHolder = null;
		RowItem rowItem = getItem(position);
		
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.row_layout, null);
			viewHolder = new ViewHolder();
			viewHolder.titleTextView = (TextView) convertView.findViewById(R.id.title);
			viewHolder.descriptionteTextView = (TextView) convertView.findViewById(R.id.secondLine);
			viewHolder.imageView = (ImageView) convertView.findViewById(R.id.icon);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.titleTextView.setText(rowItem.getTitleString());
		viewHolder.descriptionteTextView.setText(rowItem.getDescriptionsString());
		viewHolder.imageView.setImageBitmap(rowItem.getImage());
		
		return convertView;
	}

}
