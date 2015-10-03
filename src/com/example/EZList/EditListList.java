/*package com.example.EZList;

import java.util.ArrayList;

import EZListDatabase.EZListDatabaseAdapter;
//import EZListDatabase.MyItemAdapter;
import EZListDatabase.MyListContent.MyList;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Relation;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.TextView;

public class EditListList extends Activity implements OnClickListener, OnCheckedChangeListener
{
	private String listId;
	private ListView lv;
	ArrayList<Item> itemList;
	MyListAdapter itemAdapter;
	private EZListDatabaseAdapter dbAdapter;
	private AlertDialog alert;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_layout_list);

		lv = (ListView) findViewById(R.id.itemListView);

		//Receive data from another Activity
		processIntent();

		dbAdapter = new EZListDatabaseAdapter(this);
		dbAdapter = dbAdapter.open();

		//setup view
		findViewById(R.id.EditListAddItemButton).setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_list_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if(id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	 (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 
	@Override
	protected void onResume()
	{
		super.onResume();
		populateListView();
		registerClick();
	}

	private void registerClick()
	{
		lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
		{

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				Item clicked = itemList.get(position);
				showListOptionsDialog(clicked.getItemId());
				return true;
			}

		});

	}

	private void populateListView()
	{
		itemList = new ArrayList<Item>();

		Cursor listItems = dbAdapter.getAllItemsFromList("" +listId);
		for(int i = 0; i < listItems.getCount(); i++)
		{
			listItems.moveToNext();	
			String listIdTemp = listItems.getString(listItems.getColumnIndex("il_list_id"));
			String itemIdTemp = listItems.getString(listItems.getColumnIndex("item_id"));
			String itemText = listItems.getString(listItems.getColumnIndex("item_name"));
			String checkedTemp = listItems.getString(listItems.getColumnIndex("checked"));

			Item item = new Item(listIdTemp, itemIdTemp, itemText, checkedTemp);		
			itemList.add(item);
		}	
		
		itemAdapter = new MyListAdapter();
		lv.setAdapter(itemAdapter);
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
					onResume();
				}
			}
		}).show();
	}

	*//**
	 * process intent data
	 *//*
	private void processIntent()
	{
		Intent receivedIntent = getIntent();
		listId = receivedIntent.getStringExtra("listId");
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
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
	{
		int pos = lv.getPositionForView(buttonView);
		if(pos != ListView.INVALID_POSITION)
		{
			Item currentItem = itemList.get(pos);
			dbAdapter.setCheckedField(currentItem.getItemId());
		}
	}

		private class MyListAdapter extends ArrayAdapter<Item>
		{
			public MyListAdapter()
			{
				super(EditListList.this, R.layout.item_layout, itemList);
			}
	
			 (non-Javadoc)
			 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
			 
			@Override
			public View getView(int position, View convertView, ViewGroup parent)
			{
				View itemListView = convertView;
				if(itemListView == null)
				{
					itemListView = getLayoutInflater().inflate(R.layout.item_layout, parent, false);
				}
				
				//Get current item
				Item currentItem = itemList.get(position);
				
				final ViewHolder viewHolder = new ViewHolder();
				
				//TextView for Item title
				viewHolder.text = (TextView) itemListView.findViewById(R.id.itemTitle);
				viewHolder.text.setText(currentItem.getItemName()); 
				
				//CheckBox for Item checkbox
				viewHolder.checkBox = (CheckBox) itemListView.findViewById(R.id.itemCheckBox);
				
				if(currentItem.getChecked().equals("1"))
				{
					viewHolder.checkBox.setChecked(true);
				}
	
				viewHolder.checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener()
				{
	
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
					{
						Item checkChanged = (Item) viewHolder.checkBox.getTag();
						Toast.makeText(EditListList.this, checkChanged.getItemId(), Toast.LENGTH_LONG);
						//dbAdapter.setCheckedField(checkChanged.getItemId());
						onResume();
	
					}
				});
	
	
	
	
	
				return itemListView;
			}


}
}
*/