package database;

import android.widget.LinearLayout;
import java.util.ArrayList;

public class MyList
{
	private long listId;
	private String listName;
	/**
	 * @return the listId
	 */
	public long getListId()
	{
		return listId;
	}
	/**
	 * @param listId the listId to set
	 */
	public void setListId(long listId)
	{
		this.listId = listId;
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
	    return listName;
    }
	
    public void saveList(){
    	
    }
}
