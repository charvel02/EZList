package com.example.EZList;

import com.example.EZList.R;

import database.DBHelper;
import database.ListDataSource;
import database.MyList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class NewList extends Activity implements View.OnClickListener
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_list);
		findViewById(R.id.editText1);
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
		if(v.getId() == R.id.OKButton){
			DBHelper database = new DBHelper(this);
			//ListDataSource
			//lds.createList(getString(R.id.editText1));
			Intent i = new Intent(getApplicationContext(), BlankList.class);
	    	startActivity(i);
		}
    }

}
