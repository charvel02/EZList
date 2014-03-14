package com.example.EZList;

import EZListDatabase.EZListDatabaseAdapter;
import EZListDatabase.MyList;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener, OnLongClickListener
{
	private LinearLayout ll;
	private EZListDatabaseAdapter dbAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dbAdapter = new EZListDatabaseAdapter(this);
		dbAdapter = dbAdapter.open();
		ll = (LinearLayout)findViewById(R.id.MainListsLinearLayout);  
        findViewById(R.id.MainNewListButton).setOnClickListener(this);
        findViewById(R.id.MainNewListButton2).setOnClickListener(this);
        
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
		Intent i = null;
	    if(v.getId() == R.id.MainNewListButton)
	    {
	    	//start new list
			i = new Intent(getApplicationContext(), NewList.class);
			i.putExtra("listId", "-1");	    	
	    }
	    else if(v.getId() == R.id.MainNewListButton2)
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
	
	// Call Back method  to get the result from NewList activity
		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data)
		{
			super.onActivityResult(requestCode, resultCode, data);
						
			if(resultCode != RESULT_CANCELED)
	        {
		        String listIdTemp = data.getExtras().getString("listId");
		        String listNameTemp = data.getExtras().getString("listName");
		        if(requestCode == 1)
		        {
			        dbAdapter.renameList(listIdTemp, listNameTemp);
		        }
	        }
		}
	
	/* (non-Javadoc)
	 * @see android.view.View.OnLongClickListener#onLongClick(android.view.View)
	 */
    @Override
    public boolean onLongClick(View v)
    {
    	Intent editListName = new Intent(this, NewList.class);
		editListName.putExtra("listId", "" +v.getId());
		startActivityForResult(editListName, 1);
	    return true;
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
	 * @see android.app.Activity#onDestroy()
	 */
    @Override
    protected void onDestroy()
    {
	    super.onDestroy();
	    dbAdapter.close();
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
	 * @see android.app.Activity#onResume()
	 */
    @Override
    protected void onResume()
    {
	    super.onResume();
	    
	    showList();
	   
    }
    /**
     * This method adds the lists to the layout
     */
	public void showList()
	{
		ll = (LinearLayout) findViewById(R.id.MainListsLinearLayout);
			
	 	Cursor allLists = dbAdapter.getAllLists();
		for(int i = 0; i < allLists.getCount(); i++)
		{
			allLists.moveToNext();
			String listNameTemp = allLists.getString(allLists.getColumnIndex("list_name"));
			String listIdTemp = allLists.getString(allLists.getColumnIndex("list_id"));
			MyList ml = new MyList(listIdTemp, listNameTemp);
			ml.setTextView(new TextView(this));
			ml.getTextView().setOnClickListener(this);
			ml.getTextView().setOnLongClickListener(this);
			ll.addView(ml.getTextView());
		}
	}
}
