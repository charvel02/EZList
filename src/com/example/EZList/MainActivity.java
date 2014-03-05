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
			TextView t1 = new TextView(this);	
			t1.setText("" +ml.get(i).getListId());
			
			
			TextView t2 = new TextView(this);
			t2.setText(ml.get(i).getListName());
			
			lists =  new ArrayList<LinearLayout>();
				
			lists.add(new LinearLayout(this));
			lists.get(lists.size()-1).setOrientation(LinearLayout.HORIZONTAL);
			lists.get(lists.size()-1).addView(t1);
			lists.get(lists.size()-1).addView(t2);
			ll.addView(lists.get(lists.size()-1));
		}
	}
	public static void addToArrayList(MyList al){
		ml.add(al);
	}
	
}
