package com.example.EZList;

import java.util.ArrayList;

import com.example.EZList.R;

import EZListDatabase.Item;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

public class BlankList extends Activity implements View.OnClickListener
{
	private Context context;
	private LinearLayout ll;
	private ArrayList<LinearLayout> items = new ArrayList<LinearLayout>();
	private static ArrayList<Item> itemList = new ArrayList<Item>();
	private LinearLayout il;
	private long listId;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		processIntent();
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
			Item item = new Item(listId);
			item.setEditText(new EditText(this));
			addToItemList(item);
			items.add(new LinearLayout(this));
			items.get(items.size()-1).setOrientation(LinearLayout.HORIZONTAL);

			items.get(items.size()-1).addView(new CheckBox(this));
			items.get(items.size()-1).addView(item.getEditText());
			items.get(items.size()-1).setId(item.getId());
			ll.addView(items.get(items.size()-1));


		}
		else if(v.getId() == R.id.saveListButton)
		{
			for(int i = 0; i < itemList.size(); i++)
			{
				if(itemList.get(i).getListId() == listId && itemList.get(i).getName()=="")
				{
					for(int j = 0; j < items.size(); j++)
					{
						if(itemList.get(i).getId() == items.get(j).getId())
						{
							itemList.get(i).setName(itemList.get(i).getEditText().getText().toString());
						}
					}

				}
			}
			TextView tv = new TextView(this);
			tv.setText(itemList.get(0).getName());
			ll.addView(tv);
		}

	}

	private void processIntent()
	{
		Intent receivedIntent = getIntent();
		listId = receivedIntent.getLongExtra("listId", 0);
	}
	public static void addToItemList(Item i){
		itemList.add(i);
	}
}
