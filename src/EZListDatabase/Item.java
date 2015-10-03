package EZListDatabase;

import com.example.EZList.R;
import android.content.Context;
import android.graphics.Color;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Item
{
	private String itemId;
	private String itemName = "";
	private String listId;
	private String checked = "0";
	private EditText et = null;
	private CheckBox cb = null;
	private TextView tv = null;
	private LinearLayout itemLayout;

	/**
	 * @param listId - list Id the item belongs to
	 * @param itemId - the item id
	 * @param itemName - the item name
	 */
	public Item(String listId, String itemId, String itemName, String checked)
	{
		this.listId = listId;
		this.itemId = itemId;
		this.itemName = itemName;
		this.checked = checked;
	}

	/**
	 * @return the checked
	 */
	public String getChecked()
	{
		return checked;
	}

	/**
	 * @param checked the checked to set
	 */
	public void setChecked(String checked)
	{
		this.checked = checked;
	}

	/**
	 * @return the itemId
	 */
	public String getItemId()
	{
		return itemId;
	}

	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(String itemId)
	{
		this.itemId = itemId;
	}

	/**
	 * @return the itemName
	 */
	public String getItemName()
	{
		return itemName;
	}

	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}
	
	/**
	 * @return the listId
	 */
	public String getListId()
	{
		return listId;
	}

	
	/**
	 * @param listId the listId to set
	 */
	public void setListId(String listId)
	{
		this.listId = listId;
	}

	/**
	 * @param et from calling activity
	 */
	public void setEditText(EditText et)
	{
		this.et = et;

		//set text to item name
		et.setText(itemName);

		//set et id to itemId
		et.setId(Integer.parseInt(itemId));
	}

	/**
	 * 
	 * @return item's edit text view
	 */
	public EditText getEditText()
	{
		return et;
	}

	/**
	 * 
	 * @param tv from calling Activity
	 */
	public void setTextView(TextView tv)
	{
		this.tv = tv;

		//set text to item name
		tv.setText(itemName);

		tv.setTextSize(30);
		tv.setPadding(40, 0, 0, 0);
		this.tv.setTextColor(Color.parseColor("#FFFFFF"));
		
		//set tv id to itemId
		tv.setId(Integer.parseInt(itemId));
	}
	
	public TextView getTextView()
	{
		return tv;
	}
	
	public void setCheckBox(CheckBox cb)
	{
		this.cb = cb;
		cb.setButtonDrawable(R.drawable.custom_checkbox);
		this.cb.setId(Integer.parseInt(itemId));		
	}
	
	public CheckBox getCheckBox()
	{
		return cb;
	}
	
	/**
	 * @return the name
	 */
	public String getName()
	{
		return itemName;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.itemName = name;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return itemName;
	}
	//TODO: Delete this method?
	public void saveItem()
	{
		setName(et.getText().toString());
		et.setText(getName());
		tv.setText(getName());
	}
	
	//Edit layout is unused currently.
	public LinearLayout getEditLayout(Context c)
	{
		itemLayout = new LinearLayout(c);
		itemLayout.setOrientation(LinearLayout.HORIZONTAL);
		if(cb == null)
		{
			setCheckBox(new CheckBox(c));
		}
		itemLayout.addView(cb);
		if(et == null)
		{
			setEditText(new EditText(c));
		}	    
		itemLayout.addView(getEditText());
		return itemLayout;
	}

	/**
	 * @param c - context of calling activity
	 * @return layout view of this item
	 */
	public LinearLayout getViewLayout(Context c)
	{
		itemLayout = new LinearLayout(c);
		itemLayout.setOrientation(LinearLayout.HORIZONTAL);
		if(cb == null)
		{
			setCheckBox(new CheckBox(c));
			cb.setButtonDrawable(R.drawable.custom_checkbox);
		}
		
		itemLayout.addView(cb);
		
		if(tv == null)
		{
			setTextView(new TextView(c));
		}
		itemLayout.addView(tv);
		return itemLayout;
	}
	
	//TODO: Delete this method?
	public void removeViews()
	{
		itemLayout.removeAllViews();
	}

}
