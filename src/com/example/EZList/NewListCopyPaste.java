package com.example.EZList;

import EZListDatabase.EZListDatabaseAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class NewListCopyPaste extends Activity implements OnClickListener
{

	private EditText et;
	private EZListDatabaseAdapter dbAdapter;
	private String listId;
	private ClipboardManager ClipMan;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_list_copy_paste);

		dbAdapter = new EZListDatabaseAdapter(this);
		dbAdapter.open();
		processIntent();
		//ClipMan = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
		//CharSequence pasteData="";
		//ClipData.Item item = ClipMan.getPrimaryClip().getItemAt(0);
		//pasteData = item.getText();
		et = (EditText) findViewById(R.id.NewListCopyPasteEditText);
		//et.setText(pasteData);
		findViewById(R.id.NewListCopyPasteOKButton).setOnClickListener(this);
		findViewById(R.id.NewListCopyPasteCancelButton).setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_list_copy_paste, menu);
		return true;
	}

	@Override
	public void onClick(View v)
	{
		if(v.getId() == R.id.NewListCopyPasteOKButton)
		{


			String[] items = et.getText().toString().split("\n");

			for(int i = 0; i < items.length; i++)
			{
				dbAdapter.insertItem(listId, items[i]);
			}	    
			finish();
		}
		else if(v.getId() == R.id.NewListCopyPasteCancelButton)
		{
			finish();
		}
		
	}
	public void processIntent()
	{
		Intent receivedIntent = getIntent();
		listId = receivedIntent.getStringExtra("listId");
	}

}
