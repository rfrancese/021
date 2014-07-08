package com.example.dyc.Food;

import com.example.dyc.R;

import android.app.ActionBar.Tab;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;

public class myTabListener implements ActionBar.TabListener {
	
	android.app.Fragment fragment;
	
	public myTabListener(android.app.Fragment fragment) {
		this.fragment = fragment;
	}
	
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		ft.replace(R.id.fragment_container, fragment);
	}
	
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		ft.remove(fragment);
	}
	
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// nothing done here
	}
	
	
}
