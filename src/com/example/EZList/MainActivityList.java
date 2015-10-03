package com.example.EZList;

import java.util.ArrayList;

import EZListDatabase.EZListDatabaseAdapter;
//import EZListDatabase.MyListAdapter;
import EZListDatabase.MyListContent.MyList;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityList extends Activity implements OnClickListener
{
	private ArrayList<MyList> lists;
	private EZListDatabaseAdapter dbAdapter;
	private AlertDialog alert;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_activity_list);

		dbAdapter = new EZListDatabaseAdapter(this);
		dbAdapter = dbAdapter.open();

		findViewById(R.id.MainNewListButton2).setOnClickListener(this);
	}

	private void populateListView()
	{
		ArrayAdapter<MyList> adapter = new MyListAdapter();
		ListView list = (ListView) findViewById(R.id.listListView);
		list.setAdapter(adapter);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume()
	{
		super.onResume();
		getListsFromDB();
		populateListView();
		registerClick();

	}

	private void registerClick()
	{
		ListView list = (ListView) findViewById(R.id.listListView);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				MyList clicked = lists.get(position);
				Intent i = new Intent(getApplicationContext(), EditList.class);
				i.putExtra("listId", "" +clicked.getListId());
				startActivity(i);	            
			}

		});

		list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
		{

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				MyList clicked = lists.get(position);
				showListOptionsDialog("" +clicked.getListId());
				return true;
			}

		});
	}

	private void getListsFromDB()
	{
		lists = new ArrayList<MyList>();
		Cursor allLists = dbAdapter.getAllLists();

		for(int i = 0; i < allLists.getCount(); i++)
		{
			allLists.moveToNext();
			String listNameTemp = allLists.getString(allLists.getColumnIndex("list_name"));
			String listIdTemp = allLists.getString(allLists.getColumnIndex("list_id"));
			MyList ml = new MyList(listIdTemp, listNameTemp);
			lists.add(ml);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
/*		int id = item.getItemId();
		if(id == R.id.action_settings)
		{
			return true;
		}*/
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v)
	{
		Intent i = null;
		if(v.getId() == R.id.MainNewListButton2)
		{
			i = new Intent(getApplicationContext(), NewList.class);
			i.putExtra("listId", "-2");
		}
		else
		{
			i = new Intent(getApplicationContext(), EditList.class);
			i.putExtra("listId", "" +v.getId());
		}
		startActivity(i);
	}

	public void showListOptionsDialog(String id)
	{
		final String listId = id;
		alert = new AlertDialog.Builder(this).setItems(R.array.main_activity_long_click_options,
				new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				Intent i = null;
				if(which == 0)
				{
					i = new Intent(getApplicationContext(), EditList.class);
					i.putExtra("listId", listId);
					startActivity(i);
				}
				if(which == 1)
				{
					i = new Intent(getApplicationContext(), NewList.class);
					i.putExtra("listId", listId);
					startActivity(i);
				}
				if(which == 2)
				{
					dbAdapter.deleteList(listId);
					onResume();
				}
			}
		}).show();
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
	
	private class MyListAdapter extends ArrayAdapter<MyList>
	{
		public MyListAdapter()
		{
			super(MainActivityList.this, R.layout.list_item_layout, lists);
		}

		/* (non-Javadoc)
		 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			View listItemView = convertView;
			if(listItemView == null)
			{
				listItemView = getLayoutInflater().inflate(R.layout.list_item_layout, parent, false);
			}

			//Find current list
			MyList currentList = lists.get(position);

			//Fill the view
			TextView listTitle = (TextView) listItemView.findViewById(R.id.listItemTitle);
			listTitle.setText(currentList.getListName());

			//implement this later. Need to update Database to include timestamp.
			TextView lastUpdated = (TextView) listItemView.findViewById(R.id.lastUpdated);


			return listItemView;
		}
	}
}
