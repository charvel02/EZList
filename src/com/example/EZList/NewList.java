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
	private String listId;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_list);
		processIntent();
	    dbAdapter = new EZListDatabaseAdapter(this);
		dbAdapter = dbAdapter.open();
		text = (EditText) findViewById(R.id.NewListEditText);
		
		findViewById(R.id.NewListOKButton).setOnClickListener(this);
		findViewById(R.id.NewListCancelButton).setOnClickListener(this);
	}
	/**
	 * process intent data
	 */
	public void processIntent()
	{
		Intent receivedIntent = getIntent();
		listId = receivedIntent.getStringExtra("listId");
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
		Intent i = new Intent();
		
		if(v.getId() == R.id.NewListOKButton)
		{	
			String name = text.getText().toString();
			

			if(!listId.equals("-1"))
			{
				i.putExtra("listName", name);
				i.putExtra("listId", listId);
				setResult(RESULT_OK, i);
			}
			else
			{
				String id = dbAdapter.insertList(name);
				i = new Intent(getApplicationContext(), EditList.class);
				i.putExtra("listId", id);
				startActivity(i);
			}
		}
		else if(v.getId() == R.id.NewListCancelButton)
		{
			if(!listId.equals("-1"))
			{
				setResult(RESULT_CANCELED, i);
			}
		}
		finish();
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onDestroy()
	 */
    @Override
    protected void onDestroy()
    {
	    super.onDestroy();
	    dbAdapter.close();
    }

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
    @Override
    protected void onResume()
    {
	    super.onResume();

		if(!listId.equals("-1"))
		{
			text.setText(dbAdapter.getListById(listId));
		}
    }
	
}
