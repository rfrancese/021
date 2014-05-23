package com.example.dyc;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import com.example.dyc.Food.FoodListElement;

@Root(name="records")
public class Records {
	
	@ElementList(inline=true, entry="record")
	public List<FoodListElement> records;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Records [record=");
		builder.append(records);
		builder.append("]");
		return builder.toString();
	}

	public List<FoodListElement> getRecords() {
		return records;
	}

	public void setRecords(List<FoodListElement> records) {
		this.records = records;
	}

}
