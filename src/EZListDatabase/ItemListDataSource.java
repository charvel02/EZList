package EZListDatabase;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ItemListDataSource
{
	/*private SQLiteDatabase database;
	private DBHelper dbHelper;
	private String[] itemListColumns = { DBHelper.IL_ITEM_ID, DBHelper.IL_LIST_ID };
	
	public ItemListDataSource(Context context){
		dbHelper = new DBHelper(context);
	}
	public void close(){
		dbHelper.close();
	}
	
	public ItemList createItemList(Item item, MyList myList){
		ContentValues values = new ContentValues();
		values.put(DBHelper.IL_ITEM_ID, item.getId());
		values.put(DBHelper.IL_LIST_ID, myList.getListId());
		long insertId = database.insert(DBHelper.TABLE_ITEM_LIST, null, values);
		
		Cursor cursor = database.query(DBHelper.TABLE_LISTS, 
				itemListColumns, DBHelper.LIST_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();		
		ItemList newItemList = cursorToList(cursor);
		cursor.close();
		return newItemList;
	}
	
	public void deleteList(ItemList itemList){
		long id = itemList.getListID();
		System.out.println("ItemList deleted with id: " +id);
		database.delete(DBHelper.TABLE_LISTS, DBHelper.LIST_ID
				+" = " +id, null);
	}
	
	public List<ItemList> getAllLists(){
		List<ItemList> itemLists = new ArrayList<ItemList>();
		
		Cursor cursor = database.query(DBHelper.TABLE_ITEM_LIST, 
				itemListColumns, null, null, null, null, null);
		
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			ItemList itemList = cursorToList(cursor);
			itemLists.add(itemList);
			cursor.moveToNext();
		}
		cursor.close();
		return itemLists;				
	}
	
	private ItemList cursorToList(Cursor cursor){
		ItemList itemList = new ItemList();
		itemList.setItemID(cursor.getLong(0));
		itemList.setListID(cursor.getLong(1));
		return itemList;
	}

	*/
}
