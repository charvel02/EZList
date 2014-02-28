package database;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ItemDataSource
{
	private SQLiteDatabase database;
	private DBHelper dbHelper;
	private String[] itemColumns = { DBHelper.ITEM_ID, DBHelper.ITEM_NAME };
	
	public ItemDataSource(Context context){
		dbHelper = new DBHelper(context);
	}
	
	public void close(){
		dbHelper.close();
	}
	
	public Item createItem(String item){
		ContentValues values = new ContentValues();
		values.put(DBHelper.ITEM_NAME, item);
		long insertId = database.insert(DBHelper.TABLE_ITEMS, null, values);
		Cursor cursor = database.query(DBHelper.TABLE_ITEMS, 
				itemColumns, DBHelper.ITEM_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		Item newItem = cursorToItem(cursor);
		cursor.close();
		return newItem;
	}
	
	public void deleteItem(Item item){
		long id = item.getId();
		System.out.println("Item deleted with id: " +id);
		database.delete(DBHelper.TABLE_ITEMS, DBHelper.ITEM_ID
				+" = " +id, null);
	}
	
	public List<Item> getAllItems(){
		List<Item> items = new ArrayList<Item>();
		
		Cursor cursor = database.query(DBHelper.TABLE_ITEMS, 
				itemColumns, null, null, null, null, null);
		
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			Item item = cursorToItem(cursor);
			items.add(item);
			cursor.moveToNext();
		}
		cursor.close();
		return items;				
	}
	
	private Item cursorToItem(Cursor cursor){
		Item item = new Item();
		item.setId(cursor.getLong(0));
		item.setName(cursor.getString(1));
		return item;
	}
}


