package com.example.dyc;

import java.util.ArrayList;
import java.util.List;

import android.R.array;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.ClipData.Item;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SecondActivity extends Activity {
	
	private ListView myListView;
	private ArrayList<Item> itemsList = new ArrayList<Item>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);		
		FetchList fl = new FetchList();
		fl.execute();
		
		fillListView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);		
		return true;
	}
	
	public class FetchList extends AsyncTask<Void, Void, Byte> {
		@Override
		protected Byte doInBackground(Void... params) {
			Response response = new Response("http://www.wiilo.it/ws_app/listaMangiare.php");
			String responsesString = response.getResponse();
			XMLParser xmlParser = new XMLParser(responsesString);
			itemsList = xmlParser.getItemList();
			xmlParser.parse();			
			
			String [] content = itemsList.toArray(new String[itemsList.size()]);
			for (int i = 0; i < content.length; i++) {
				//System.out.println(content[i]);
			}
			
			return null;
		}
	}
	
	public void fillListView() {
//		String [] content = itemsList.toArray(new String[itemsList.size()]);
		String [] content = {"uno","due","tre"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, content);
		myListView = (ListView) findViewById(R.id.listViewID);
		myListView.setAdapter(adapter);
	}
	
}
