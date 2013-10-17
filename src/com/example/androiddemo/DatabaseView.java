package com.example.androiddemo;

import java.util.ArrayList;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DatabaseView extends Activity {
	Button btnRefresh;
	ListView lv;
	DBAdapter myDB;
	String item;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.database_viewer);
		myDB = new DBAdapter(this);
		
		Button btnCancel = (Button)findViewById(R.id.btnCancel);
		btnRefresh = (Button)findViewById(R.id.btnRefresh);
        lv = (ListView)findViewById(R.id.listView1);
        
		//cancel button
        btnCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
        
        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item = ((TextView)view).getText().toString();
            	clickedItem();
            }
        });
        
	}
	
	//---updates the UI with content from the database---
	public void refreshUI(View view){
    	ArrayList<String> data =  new ArrayList<String>();
    			
		myDB.open();
		data.addAll(myDB.dbToString());
   		myDB.close();

   		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
	    lv.setAdapter(adapter);
	}
	

	
	private void clickedItem(){
		new AlertDialog.Builder(this)
	    .setTitle("Delete entry")
	    .setMessage("Are you sure you want to delete this entry?")
	    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	        }
	     })
	    .setNegativeButton("No", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // do nothing
	        }
	     })
	     .show();
	}

}
