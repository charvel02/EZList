package com.example.EZList;

import java.util.ArrayList;

import EZListDatabase.EZListDatabaseAdapter;
import EZListDatabase.Item;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EditList extends Activity implements View.OnClickListener, View.OnLongClickListener
{
	private LinearLayout ll = null;
	private ArrayList<Item> itemList;
	private String listId;
	private EZListDatabaseAdapter dbAdapter;
	private AlertDialog alert;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		processIntent();
		dbAdapter = new EZListDatabaseAdapter(this);
		dbAdapter = dbAdapter.open();
		setContentView(R.layout.activity_edit_layout);
		findViewById(R.id.EditListAddItemButton).setOnClickListener(this);

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
		if(v.getId() == R.id.EditListAddItemButton)
		{			
			Intent addItemIntent = new Intent(this, AddItem.class);
			addItemIntent.putExtra("listId", "" +listId);
			addItemIntent.putExtra("itemId", "-1");
			addItemIntent.putExtra("itemText", "");
			startActivityForResult(addItemIntent, 1);

		}
		else
		{		
			/*Intent editItemIntent = new Intent(this, AddItem.class);
			editItemIntent.putExtra("itemId", "" +v.getId());
			editItemIntent.putExtra("listId", listId);
			editItemIntent.putExtra("itemText", dbAdapter.getItemById("" +v.getId()));
			startActivityForResult(editItemIntent, 2);*/
		}
	}


	/**
	 * process intent data
	 */
	private void processIntent()
	{
		Intent receivedIntent = getIntent();
		listId = receivedIntent.getStringExtra("listId");
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume()
	{
		super.onResume();
		TextView title = (TextView) findViewById(R.id.EditListTitle);
		title.setText(dbAdapter.getListById(listId));
		ll = (LinearLayout) findViewById(R.id.EditListListLinearLayout);
		itemList = new ArrayList<Item>();
		rebuildList();

		/*if(fl == null){
			fl = (FrameLayout) findViewById(R.layout.activity_blank_list);
		}
		else{
			fl.removeAllViews();
			fl = (FrameLayout) findViewById(R.layout.activity_blank_list);
		}
		setContentView(fl);

		if(ll == null){
			ll = (LinearLayout)findViewById(R.id.linearLayout2);
		}
		else{
			ll.removeAllViews();
			ll = (LinearLayout)findViewById(R.id.linearLayout2);
		}

		 */
	}


	/* (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause()
	{
		super.onPause();
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onStop()
	 */
	@Override
	protected void onStop()
	{
		super.onStop();
		ll.removeAllViews();
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

	// Call Back method  to get the result from AddItem activity
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);


		if(resultCode != RESULT_CANCELED)
		{
			String itemIdTemp = data.getExtras().getString("itemId");
			String listIdTemp = data.getExtras().getString("listId");
			String itemText = data.getExtras().getString("Item Text");

			if(requestCode == 1)
			{
				String itemId = dbAdapter.insertItem(listIdTemp, itemText);
			}
			else if(requestCode == 2)
			{
				dbAdapter.renameItem(itemIdTemp, itemText);
				//temp.setText(itemText);
			}
		}
	}

	@Override
	public boolean onLongClick(View v)
	{
		showListOptionsDialog("" +v.getId());
		return true;
	}
	/**
	 * pulls all items from database associated with listId passed from MainActivity.
	 * rebuildList() is called any time an item is checked or unchecked to resort the list.
	 */
	public void rebuildList()
	{
		ll.removeAllViews();
		Cursor listItems = dbAdapter.getAllItemsFromList("" +listId);
		for(int i = 0; i < listItems.getCount(); i++)
		{
			listItems.moveToNext();	
			String listIdTemp = listItems.getString(listItems.getColumnIndex("il_list_id"));
			String itemIdTemp = listItems.getString(listItems.getColumnIndex("item_id"));
			String itemText = listItems.getString(listItems.getColumnIndex("item_name"));
			String checkedTemp = listItems.getString(listItems.getColumnIndex("checked"));
			Item item = new Item(listIdTemp, itemIdTemp, itemText, checkedTemp);			
			item.setCheckBox(new CheckBox(this));
			if(checkedTemp.equals("1"))
			{
				item.getCheckBox().setChecked(true);
			}

			item.getCheckBox().setOnClickListener(new OnClickListener()
			{

				@Override
				public void onClick(View v)
				{
					dbAdapter.setCheckedField("" +v.getId());
					rebuildList();

				}
			});
			item.setTextView(new TextView(this));		
			item.getTextView().setOnLongClickListener(this);
			ll.addView(item.getViewLayout(this));

			//ll.addView(et);
		}	
	}
	public void showListOptionsDialog(String id)
	{
		final String itemId = id;
		alert = new AlertDialog.Builder(this).setItems(R.array.edit_list_long_click_options,
				new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				Intent i = null;
				if(which == 0)
				{
					i = new Intent(getApplicationContext(), AddItem.class);
					i.putExtra("itemId", itemId);
					i.putExtra("listId", listId);
					i.putExtra("itemText", dbAdapter.getItemById(itemId));
					startActivityForResult(i, 2);
				}
				if(which == 1)
				{
					dbAdapter.deleteItem(itemId);
					rebuildList();
				}
			}
		}).show();
	}
}

