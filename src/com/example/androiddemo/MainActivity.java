package com.example.androiddemo;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
	EditText txtFirst;
	EditText txtLast;
	EditText txtDate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		txtFirst = (EditText)findViewById(R.id.etxtFirst);
		txtLast = (EditText)findViewById(R.id.etxtLast);
		txtDate = (EditText)findViewById(R.id.etxtDate);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void dbUI (View view){
		Intent i=new Intent(MainActivity.this, DatabaseView.class);
        startActivity(i);
	}
	
	public void updateDB (View view){
		
    	DBAdapter myDB = new DBAdapter(this);
		myDB.open();
		myDB.createEntry(
				txtFirst.getText().toString(), 
				txtLast.getText().toString(), 
				txtDate.getText().toString());
   		myDB.close();
   		Toast.makeText(this, "Database Updated", Toast.LENGTH_LONG).show();
	}

}