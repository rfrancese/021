package com.example.dyc.Food;

import org.simpleframework.xml.Element;

@Element(name="record")
public class FoodListElement {
	
	@Element(name="id", required=false, data=true)
	public int id;
	
	@Element(name="titolo", required=false, data=true)
	public String titolo;
	
	@Element(name="foto", required=false, data=true)
	public String photo;
	
	@Element(name="descrizione", required=false, data=true)
	public String description;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Element [id=");
		builder.append(id);
		builder.append(", title=");
		builder.append(titolo);
		builder.append(", photo=");
		builder.append(photo);
		builder.append(", descr=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

	public int getId() {
		return id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
