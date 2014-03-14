package EZListDatabase;

import android.graphics.Color;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyList
{
	private String listId;
	private String listName;
	private TextView tv;
	
	/**
	 * @param listId list id
	 * @param listName list name
	 */
    public MyList(String listId, String listName)
    {
	    this.listId = listId;
	    this.listName = listName;	    
    }
    /**
     * 
     * @return TextView for list object
     */
    public TextView getTextView()
    {    	
    	return tv;
    }
    
    /** 
     * @param tv passed from Activity
     * 
     */
    public void setTextView(TextView tv)
    {
    	this.tv = tv;
    	
    	//set tv text to listName
    	this.tv.setText(listName);
    	
    	this.tv.setTextSize(20);
    	this.tv.setTextColor(Color.parseColor("#FFFFFF"));
    	//set tv id to listId
    	this.tv.setId(Integer.parseInt(listId));
    }
    
	/**
	 * @return the listId
	 */
	public String getListId()
	{
		return listId;
	}
	
	/**
	 * @return the listName
	 */
	public String getListName()
	{
		return listName;
	}
	/**
	 * @param listName the listName to set
	 */
	public void setListName(String listName)
	{
		this.listName = listName;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString()
    {
	    return "ListID: " +listId + " ListName: " +listName;
    }
}
