package com.example.dyc.RowItem;

public class RowItem2 {
	
	private String itemID;
	private String image;
	
	private String titleString;
	private String descriptionsString;

	public RowItem2(String itemID, String img, String title, String descr) {
		this.itemID = itemID;
		this.image = img;
		this.titleString = title;
		this.descriptionsString = descr;
	}

	public String getItemId() {
		return itemID;
	}

	public String getImage() {
		return image;
	}

	public String getTitleString() {
		return titleString;
	}

	public String getDescriptionsString() {
		return descriptionsString;
	}

	public void setImage(String image) {
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
