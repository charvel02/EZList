package com.example.EZList;

import java.util.ArrayList;

import com.example.EZList.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class BlankList extends Activity implements View.OnClickListener
{
	private Context context;
	private LinearLayout ll;
	private ArrayList<LinearLayout> items = new ArrayList<LinearLayout>();;
	private LinearLayout il;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_blank_list);
		ll = (LinearLayout)findViewById(R.id.linearLayout2);
		

		findViewById(R.id.addItemButton).setOnClickListener(this);
		findViewById(R.id.saveListButton).setOnClickListener(this);

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
		if(v.getId() == R.id.addItemButton){
			//ADD LIST ITEM
			items.add(new LinearLayout(this));
			items.get(items.size()-1).setOrientation(LinearLayout.HORIZONTAL);
			
			items.get(items.size()-1).addView(new CheckBox(this));
			items.get(items.size()-1).addView(new EditText(this));
			ll.addView(items.get(items.size()-1));
			
			
		}
		else if(v.getId() == R.id.saveListButton){
			//SAVE LIST
			
		}

	}


}
