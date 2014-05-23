package com.example.dyc.News;

import org.simpleframework.xml.Element;

@Element(name="record")
public class News {
	
	@Element(name="id", required=false, data=true)
	public int newsID;
	
	@Element(name="titolo", required=false, data=true)
	public String titolo;
	
	@Element(name="foto", required=false, data=true)
	public String photo;
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Element [id=");
		builder.append(newsID);
		builder.append(", title=");
		builder.append(titolo);
		builder.append(", photo=");
		builder.append(photo);
		builder.append("]");
		return builder.toString();
	}

	public int getNewsID() {
		return newsID;
	}

	public String getTitolo() {
		return titolo;
	}

	public String getPhoto() {
		return photo;
	}

	public void setNewsID(int newsID) {
		this.newsID = newsID;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
}
