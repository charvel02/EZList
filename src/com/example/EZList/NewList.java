package com.example.EZList;

import java.util.ArrayList;
import java.util.List;

import com.example.EZList.R;


import EZListDatabase.DBHelper;
import EZListDatabase.DatabaseHelper;
import EZListDatabase.EZListDatabaseAdapter;
import EZListDatabase.MyList;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.ViewManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class NewList extends Activity implements View.OnClickListener
{
	private EditText text;
	private EZListDatabaseAdapter dbAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_list);
		dbAdapter = new EZListDatabaseAdapter(this);
		dbAdapter = dbAdapter.open();
		
				    
		text = (EditText) findViewById(R.id.editText1);
		findViewById(R.id.OKButton).setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_list, menu);
		return true;
	}

	@Override
    public void onClick(View v)
    {
		
		if(v.getId() == R.id.OKButton)
		{
			String name = text.getText().toString();
			String id = dbAdapter.insertList(name);
			Intent i = new Intent(getApplicationContext(), EditList.class);
			i.putExtra("listId", id);
	    	startActivity(i);
	    	finish();
		}
    }
}
