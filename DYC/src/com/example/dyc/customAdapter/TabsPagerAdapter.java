package com.example.dyc.customAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(android.support.v4.app.FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {
//		switch (index) {
//		case 0:
//			return new HomeFragment();
//		case 1:
//			return new ContactsFragment();
//		case 2:
//			return new DescriptionFragment();
//		}
		return null;
	}

	@Override
	public int getCount() {
		return 3;
	}
	

}
