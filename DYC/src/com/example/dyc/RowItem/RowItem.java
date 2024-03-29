package com.example.dyc.RowItem;

import android.graphics.Bitmap;

public class RowItem {
	
	private String itemID;
	private Bitmap image;
	
	private String titleString;
	private String descriptionsString;

	public RowItem(String itemID, Bitmap img, String title, String descr) {
		this.itemID = itemID;
		this.image = img;
		this.titleString = title;
		this.descriptionsString = descr;
	}

	public String getItemId() {
		return itemID;
	}

	public Bitmap getImage() {
		return image;
	}

	public String getTitleString() {
		return titleString;
	}

	public String getDescriptionsString() {
		return descriptionsString;
	}

	public void setImage(Bitmap image) {
		this.image = image;
	}

	public void setTitleString(String titleString) {
		this.titleString = titleString;
	}

	public void setDescriptionsString(String descriptionsString) {
		this.descriptionsString = descriptionsString;
	}
	
	@Override
	public String toString() {
		return titleString + "\n" + descriptionsString;
	}

}
