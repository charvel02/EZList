package com.example.EZList;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

public class ListItem extends View
{
	private String s;
	private LinearLayout ll;
	private Context context;
	
	public ListItem(String s, Context context)
    {
	    super(context);
	    s = this.s;
	    generateListItemView();
    }
	
	public void generateListItemView()
	{
		ll = new LinearLayout(context);
		CheckBox cb = new CheckBox(context);
		ll.addView(cb);
		EditText et = new EditText(context);
		ll.addView(et);
	}
}
