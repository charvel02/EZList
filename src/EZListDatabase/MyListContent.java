package EZListDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.EZList.R;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyListContent
{	
	public static List<MyList> LISTS = new ArrayList<MyList>();
	public static Map<String, MyList> LIST_MAP = new HashMap<String, MyList>();

	private static EZListDatabaseAdapter db;

	public static void setContext(Context c)
	{
		if(db == null)
		{
			db = new EZListDatabaseAdapter(c);			
			db.open();
			Cursor cursor = db.getAllLists();
			for(int i = 0; i < cursor.getCount(); i++)
			{
				cursor.moveToNext();
				String listIdTemp = cursor.getString(cursor.getColumnIndex("list_id"));
				String listNameTemp = cursor.getString(cursor.getColumnIndex("list_name"));
				MyList ml = new MyList(listIdTemp, listNameTemp);
				System.out.println(ml.getListName());
				LISTS.add(ml);
				LIST_MAP.put(listIdTemp, ml);
			}
		}
	}
	public static class MyList
	{
		private String listId;
		private String listName;
		private TextView tv;
		private ArrayList<Item> items;

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
		 * @param listId list id
		 * @param listName list name
		 * @param items ArrayList of Items
		 */
		public MyList(String listId, String listName, ArrayList<Item> items)
		{
			this.listId = listId;
			this.listName = listName;
			this.items = items;
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
		 * @param listId the listId to set
		 */
		public void setListId(String listId)
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


		/**
		 * @return the items
		 */
		public ArrayList<Item> getItems()
		{
			return items;
		}

		/**
		 * @param items the items to set
		 */
		public void setItems(ArrayList<Item> items)
		{
			this.items = items;
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
}
