package com.example.dyc.Food;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dyc.R;

public class FragmentTab2 extends Fragment {
	
	private String indirizzo, comune, provincia, regione, www;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.contacts_layout, container, false);
		
		String addresString = "";
		
		comune = getArguments().getString("comune");
		indirizzo = getArguments().getString("indirizzo");
		provincia = getArguments().getString("provincia");
		regione = getArguments().getString("regione");
		
		www = getArguments().getString("www");
		
		addresString = indirizzo + ", " + comune + " \n" + provincia + ", \n" + regione;
		
		TextView textview = (TextView) view.findViewById(R.id.tabtextview);
		TextView textView2 = (TextView) view.findViewById(R.id.tabtextview2);
		textview.setText(addresString);
		textView2.setText(www);
		
		return view;
	}
}
