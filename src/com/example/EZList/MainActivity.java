package com.example.EZList;

import java.util.ArrayList;

import com.example.EZList.R;

import EZListDatabase.Item;
import EZListDatabase.MyList;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener
{
	private Context context;
	private ArrayList<LinearLayout> lists;
	private static ArrayList<MyList> ml = new ArrayList<MyList>();

	private LinearLayout ll;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.newListButton).setOnClickListener(this);
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
		for(int i = 0; i < ml.size(); i++){
			if(v.getId() == ml.get(i).getTextView().getId()){
				Intent j = new Intent(getApplicationContext(), EditList.class);
				j.putExtra("listId", ml.get(i).getListId());
				startActivity(j);
			}	
		}
		

	}
	/* (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */
    @Override
    protected void onPause()
    {
	    // TODO Auto-generated method stub
	    super.onPause();
	    
    }

	/* (non-Javadoc)
	 * @see android.app.Activity#onStop()
	 */
    @Override
    protected void onStop()
    {
	    // TODO Auto-generated method stub
	    super.onStop();
	    ll.removeAllViews();
    }

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
    @Override
    protected void onResume()
    {
	    // TODO Auto-generated method stub
	    super.onResume();
	    showList();
	   
    }

	public void showList(){
		ll = (LinearLayout) findViewById(R.id.linearLayout5);
		
	 	
		for(int i = 0; i < ml.size(); i++)
		{
			MyList list = ml.get(i);
			list.setTextView(new TextView(this));
			list.getTextView().setId(list.getListId());
			list.getTextView().setOnClickListener(this);
			ll.addView(list.getTextView());
		}
	}
	public static void addToArrayList(MyList al){
		ml.add(al);
	}
	
	
}
