/*package EZListDatabase;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ListDataSource
{
	private SQLiteDatabase database;
	private DBHelper dbHelper;
	private String[] listColumns = { DBHelper.LIST_ID, DBHelper.LIST_NAME };
	
	public ListDataSource(Context context){
		dbHelper = new DBHelper(context);
	}
	
	public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	}
	
	public void close(){
		dbHelper.close();
	}
	
	public MyList createList(String list){
		ContentValues values = new ContentValues();
		values.put(DBHelper.LIST_NAME, list);
		long insertId = database.insert(DBHelper.TABLE_LISTS, null, values);
		Cursor cursor = database.query(DBHelper.TABLE_LISTS, 
				listColumns, DBHelper.LIST_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		MyList newList = cursorToList(cursor);
		cursor.close();
		return newList;
	}
	
	public void deleteList(MyList list){
		long id = list.getListId();
		System.out.println("MyList deleted with id: " +id);
		database.delete(DBHelper.TABLE_LISTS, DBHelper.LIST_ID
				+" = " +id, null);
	}
	
	public List<MyList> getAllLists(){
		List<MyList> lists = new ArrayList<MyList>();
		
		Cursor cursor = database.query(DBHelper.TABLE_LISTS, 
				listColumns, null, null, null, null, null);
		
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			MyList list = cursorToList(cursor);
			lists.add(list);
			cursor.moveToNext();
		}
		cursor.close();
		return lists;				
	}
	
	private MyList cursorToList(Cursor cursor){
		MyList list = new MyList(cursor.getString(1));
		
		return list;
	}

}
*/