package com.example.dyc.Food;

import org.simpleframework.xml.Element;

@Element(name="record", required=false)
public class FoodListElement {
	
	@Element(name="id", required=false, data=true)
	public int id;
	
	@Element(name="titolo", required=false, data=true)
	public String titolo;
	
	@Element(name="foto", required=false, data=true)
	public String photo;
	
	@Element(name="descrizione", required=false, data=true)
	public String description;
	
	@Element(name="indirizzo", required=false, data=true)
	public String indirizzo;
	
	@Element(name="comune", required=false, data=true)
	public String comune;
	
	@Element(name="provincia", required=false, data=true)
	public String provincia;
	
	@Element(name="regione", required=false, data=true)
	public String regione;
	
	@Element(name="sitoweb", required=false, data=true)
	public String www;
	
	@Element(name="latitudine", required=false, data=true)
	public String latitudine;
	
	@Element(name="longitudine", required=false, data=true)
	public String longitudine;
	
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
		builder.append(", www=");
		builder.append(www);
		builder.append(", lat=");
		builder.append(latitudine);
		builder.append(", lon=");
		builder.append(longitudine);
		builder.append("]");
		return builder.toString();
	}

	public int getId() {
		return id;
	}

	public String getTitolo() {
		return titolo;
	}


	public String getPhoto() {
		return photo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public String getComune() {
		return comune;
	}

	public String getProvincia() {
		return provincia;
	}

	public String getRegione() {
		return regione;
	}

	public String getWww() {
		return www;
	}

	public String getLatitudine() {
		return latitudine;
	}

	public String getLongitudine() {
		return longitudine;
	}

}
