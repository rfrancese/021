package com.example.dyc.News;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name="records")
public class NewsRecords {

	@ElementList(inline=true, entry="record")
	public List<News> records;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Records [record=");
		builder.append(records);
		builder.append("]");
		return builder.toString();
	}

	public List<News> getRecords() {
		return records;
	}

	public void setRecords(List<News> records) {
		this.records = records;
	}

}
