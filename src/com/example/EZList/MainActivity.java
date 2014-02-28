package com.example.EZList;

import com.example.EZList.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.*;

public class MainActivity extends Activity implements View.OnClickListener
{
	private Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.newListButton).setOnClickListener(this);
	//	Database database = new Database(this);
	//	SQLiteDatabase qdb = database.getWritableDatabase();
		String list = "new list";
	//	qdb.rawQuery("INSERT INTO lists VALUES(new list)");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
    public void onClick(View v)
    {
	    if(v.getId() == R.id.newListButton){
	    	//start new list
	    	Intent i = new Intent(getApplicationContext(), NewList.class);
	    	startActivity(i);
	    }
	    
    }

}
