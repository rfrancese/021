package com.example.dyc.Food;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.example.dyc.R;

public class FragmentTab3 extends Fragment {

	MapView mapView;
	GoogleMap map;
	String lat,lon;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.tab, container, false);
		
		lat = getArguments().getString("lat");
		lon = getArguments().getString("lon");
		
		MapsInitializer.initialize(getActivity());

		switch (GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity()) )
		{
		case ConnectionResult.SUCCESS:
		  Toast.makeText(getActivity(), "SUCCESS", Toast.LENGTH_SHORT).show();
		  mapView = (MapView) view.findViewById(R.id.map);
		  mapView.onCreate(savedInstanceState);
		  // Gets to GoogleMap from the MapView and does initialization stuff
		  if(mapView!=null)
		  {
		  map = mapView.getMap();
		  map.getUiSettings().setMyLocationButtonEnabled(false);
		  map.setMyLocationEnabled(true);
		  LatLng latlon = new LatLng(Double.parseDouble(lat), Double.parseDouble(lon));
		  CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latlon, 10);
		  map.animateCamera(cameraUpdate);
		  map.addMarker(new MarkerOptions()
		  		.position(latlon)
		  		.title("Ciao")
		  		.alpha(0.7f));
		  }
		  break;
		case ConnectionResult.SERVICE_MISSING: 
		  Toast.makeText(getActivity(), "SERVICE MISSING", Toast.LENGTH_SHORT).show();
		  break;
		case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED: 
		  Toast.makeText(getActivity(), "UPDATE REQUIRED", Toast.LENGTH_SHORT).show();
		  break;
		default: Toast.makeText(getActivity(), GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity()), Toast.LENGTH_SHORT).show();
		}

		return view;
	}
	
	@Override
	public void onResume() {
		mapView.onResume();
		super.onResume();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}
	
	@Override
	public void onLowMemory() {
		super.onLowMemory();
		mapView.onLowMemory();
	}
	
}
