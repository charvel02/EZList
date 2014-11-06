//TODO: make this into a fragment instead of an activity
package com.example.EZList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class AddItem extends Activity implements OnClickListener
{
	private EditText et;
	
	//set to -1 for new items since there is no itemId yet.
	private String itemId = "-1";
	private String listId;
	private String itemText;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_item);
		et = (EditText) findViewById(R.id.AddItemEditText);
		findViewById(R.id.AddItemOKButton).setOnClickListener(this);
		findViewById(R.id.AddItemCancelButton).setOnClickListener(this);
		processIntent();
		
		//if item is not new, set et to item text
		if(!itemId.equals("-1"))
		{
			et.setText(itemText);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_item, menu);
		return true;
	}

	@Override
	public void onClick(View v)
	{
		Intent itemIntent = new Intent();

		if(v.getId() == R.id.AddItemOKButton && !et.getText().toString().trim().isEmpty())
		{	
			String text = et.getText().toString();

			itemIntent.putExtra("Item Text", text);
			itemIntent.putExtra("listId", listId);
			itemIntent.putExtra("itemId", itemId);

			setResult(RESULT_OK, itemIntent);

		}
		else if(v.getId() == R.id.AddItemCancelButton)
		{
			setResult(RESULT_CANCELED, itemIntent);
		}
		finish();
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#finish()
	 */
	@Override
	public void finish()
	{
		// TODO Auto-generated method stub
		super.finish();
	}

	/**
	 * process intent data
	 */
	private void processIntent()
	{
		Intent receivedIntent = getIntent();
		listId = receivedIntent.getStringExtra("listId");
		itemId = receivedIntent.getStringExtra("itemId");
		itemText = receivedIntent.getStringExtra("itemText");
	}

}
