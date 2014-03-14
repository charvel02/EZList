package EZListDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author Jason
 *
 */
public class EZListDatabaseAdapter
{
	//database name & version
	private static final String DATABASE_NAME = "EZListDatabase.db";
	private static final int DATABASE_VERSION = 1;
	
	//TABLE_LIST info	
	public static final String TABLE_LISTS = "lists";
	public static final String LIST_ID = "list_id";
	public static final String LIST_NAME ="list_name";
	
	//ITEM_LIST info
	public static final String TABLE_ITEM_LIST = "itemsList";
	public static final String ITEM_ID ="item_id";
	public static final String IL_LIST_ID="il_list_id";
	public static final String ITEM_NAME = "item_name";
	public static final String ITEM_CB = "checked";
	
    // SQL Statement to create tables
	public static final String CREATE_LISTS_TABLE = 
		"CREATE TABLE " +TABLE_LISTS +"(" +
			LIST_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
			LIST_NAME +" TEXT);";
/*	public static final String CREATE_ITEM_LIST_TABLE =	
			"CREATE TABLE " +TABLE_ITEM_LIST +"(" +
					IL_LIST_ID +" INTEGER NOT NULL, " +
					ITEM_ID +" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
					ITEM_NAME +" TEXT);";*/
	public static final String CREATE_ITEM_LIST_TABLE = 
			"CREATE TABLE " +TABLE_ITEM_LIST +"(" +
				IL_LIST_ID +" INTEGER NOT NULL, " +
				ITEM_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
				ITEM_NAME +" TEXT COLLATE NOCASE, " +
				ITEM_CB +" INTEGER NOT NULL);";
			
	// Variable to hold the database instance
    public  SQLiteDatabase db;
    
 // Context of the application using the database.
    private final Context context;
    
 // Database open/upgrade helper
    private DatabaseHelper dbHelper;

	/**
	 * @param context - context of the Activity using this database
	 * 
	 * sets context and instantiates dbHelper;
	 */
    public EZListDatabaseAdapter(Context context)
    {
	    this.context = context;
	    dbHelper = new DatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
	    
	    
    }
    
    /**
     * 
     * @return database adapter
     * @throws SQLException
     * 
     * opens the database
     */
    public EZListDatabaseAdapter open() throws SQLException
    {
            db = dbHelper.getWritableDatabase();
            return this;
    }
    
	/**
	 * close the database       
	 */
    public void close()
    {
            db.close();
    }
    
    /**
     * @return instance of SQLiteDatabase
     */
    public  SQLiteDatabase getDatabaseInstance()
    {
            return db;
    }
    
    /**
     * @return list_id of new list is returned so it can 
     * be passed to the EditList Activity
     * which is launched upon creation of a new list.
     * @param name of new list
     * 
     * 
     */
    public String insertList(String name)
    {	
    	ContentValues values = new ContentValues();
    	
    	//assign value for each column
        values.put(LIST_NAME, name);
        
        //insert record
        db.insert(TABLE_LISTS, null, values);
        
        //return list_id for new list
        Cursor cursor = getAllLists();
        cursor.moveToLast();
        return cursor.getString(cursor.getColumnIndex("list_id"));
    }
    
    /**TODO: figure out what is being returned
     * 
     * @return number of entries deleted
     * @param id - id of list to be deleted
     */
    public int deleteList(String id)
    {
    	int numEntriesDeleted = db.delete(TABLE_LISTS, "list_id=?", new String[]{id});
    	return numEntriesDeleted;
    }
    
    /**
     * 
     * @return all rows from TABLE_LISTS
     */
    public Cursor getAllLists ()
    {
            return db.query(TABLE_LISTS, null, null, null, null, null, null);
    }
    /**
     * 
     * @param id of list to get
     * @return the name of the list
     */
    public String getListById(String id)
    {
    	String s = new String();
    	Cursor cursor = db.query(TABLE_LISTS, null, "list_id=?", 
    			new String[]{id}, null, null, null);
    	
    	 if(cursor.getCount()==0)
         {            
             return null;
         }
    	 
    	 cursor.moveToFirst();
    	 s = cursor.getString(cursor.getColumnIndex(LIST_NAME));
    	 return s;
    }
    /**TODO: figure out what is being returned
     * 
     * @param id of list to be renamed
     * @param newName of the list
     * @return number of entries renamed
     */
    public int renameList(String id, String newName)
    {
    	ContentValues values = new ContentValues();
    	values.put(LIST_NAME, newName);
    	int numEntriesRenamed = db.update("lists", values, "list_id=?", new String[]{id});
    	return numEntriesRenamed;
    }
    
    /**
     * 
     * @param listId of that is inserting item
     * @param name of item to be inserted
     * @return item number of new item
     */
    public String insertItem(String listId, String name)
    {
    	ContentValues values = new ContentValues();
    	
    	//assign values for each column
    	values.put(IL_LIST_ID, listId);
    	values.put(ITEM_NAME, name);
    	values.put(ITEM_CB, "0");
    	
    	 //insert record
        db.insert(TABLE_ITEM_LIST, null, values);
    	
    	//return item_id for new item
        Cursor cursor = getAllItemsFromList(listId);
        cursor.moveToLast();
        return cursor.getString(cursor.getColumnIndex(ITEM_ID));    	
    }
    
    /**TODO: figure out what this returns
     * 
     * @param id of item to be deleted
     * @return numEntriesDeleted
     */
    public int deleteItem(String id)
    {
    	int numEntriesDeleted = db.delete(TABLE_ITEM_LIST, "item_id=?", new String[]{id});
    	return numEntriesDeleted;
    }
    
    /**
     * 
     * @param listId of list to get items from
     * @return TABLE_ITEM_LIST
     */
    public Cursor getAllItemsFromList(String id)
    {
    	return db.query(TABLE_ITEM_LIST, null, IL_LIST_ID +"=?", 
    			new String[]{id}, null, null, ITEM_CB +", " +ITEM_NAME);
    }
    
    /**
     * @param id of item to get
     * @return item name
     */
    public String getItemById(String id)
    {
    	String s = new String();
    	Cursor cursor = db.query(TABLE_ITEM_LIST, null, ITEM_ID +"=?", 
    			new String[]{id}, null, null, null);
    	
    	 if(cursor.getCount()==0)
         {            
             return null;
         }
    	 
    	 cursor.moveToFirst();
    	 s = cursor.getString(cursor.getColumnIndex(ITEM_NAME));
    	 return s;
    }
    
    /**TODO: figure out what this returns
     * 
     * @param id of item to be renamed
     * @param newName of item
     * @return numEntriesRenamed
     */
    public int renameItem(String id, String newName)
    {
    	ContentValues values = new ContentValues();
    	values.put(ITEM_NAME, newName);
    	int numEntriesRenamed = db.update(TABLE_ITEM_LIST, values, ITEM_ID +"=?", new String[]{id});
    	return numEntriesRenamed;
    }
    
    public String getCheckedField(String id)
    {
    	String s = new String();
    	Cursor cursor = db.query(TABLE_ITEM_LIST, null, ITEM_ID +"=?", 
    			new String[]{id}, null, null, null);
    	
    	 if(cursor.getCount()==0)
         {            
             return null;
         }
    	 
    	 cursor.moveToFirst();
    	 s = cursor.getString(cursor.getColumnIndex(ITEM_CB));
    	 return s;
    }
    
    public void setCheckedField(String id)
    {	
    	String s = new String();
    	Cursor cursor = db.query(TABLE_ITEM_LIST, null, ITEM_ID +"=?", 
    			new String[]{id}, null, null, null);
    	
    	 if(cursor.getCount()==0)
         {            
    		 
         }
    	 
    	 cursor.moveToFirst();
    	 s = cursor.getString(cursor.getColumnIndex(ITEM_CB));

    	ContentValues values = new ContentValues();
    	
    	if(s.equals("0"))
    	{
    		values.put(ITEM_CB, "1");
    	}
    	else
    	{
    		values.put(ITEM_CB, "0");
    	}
    	db.update(TABLE_ITEM_LIST, values, ITEM_ID +"=?", new String[]{id});
    }
}
