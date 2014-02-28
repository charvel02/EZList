package database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper{
	public static final String TABLE_ITEMS="items";
	public static final String ITEM_ID = "_id";
	public static final String ITEM_NAME = "item name";
	
	public static final String TABLE_LISTS = "lists";
	public static final String LIST_ID = "_id";
	public static final String LIST_NAME ="list name";

	public static final String TABLE_ITEM_LIST = "item_list";
	public static final String IL_ITEM_ID ="_id";
	public static final String IL_LIST_ID="_id";
		
	private static final String DATABASE_NAME = "EZListDB.db";
	private static final int DATABASE_VERSION = 1;
	
	private static final String DATABASE_CREATE = "create table "
		      + TABLE_ITEMS + "(" + ITEM_ID
		      + " integer primary key autoincrement, " + ITEM_NAME
		      + " text not null);" +
		      "create table "
		      + TABLE_LISTS + "(" + LIST_ID
		      + " integer primary key autoincrement, " + LIST_NAME
		      + " text not null);" +
		      "create table "
		      + TABLE_ITEM_LIST + "(" + IL_ITEM_ID
		      + " integer not null, " + IL_LIST_ID
		      + " integer not null" +
		      "foreign key(IL_ITEM_ID references TABLE_ITEMS(ITEM_ID)" +
		      "foreign key(IL_LIST_ID references TABLE_LISTS(LIST_ID)" +
		      "primary key(IL_ITEM_ID, IL_LIST_ID);";

	/**
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 * @param errorHandler
	 */
    public DBHelper(Context context)
    {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	    // TODO Auto-generated constructor stub
    }

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
    @Override
    public void onCreate(SQLiteDatabase db)
    {
	    db.execSQL(DATABASE_CREATE);
	    
    }

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
	    Log.w(DBHelper.class.getName(), 
	    		"Upgrading database from version " + oldVersion + " to "
	    		+newVersion +", which will destroy all old data");
	    db.execSQL("DROP TABLE IF EXISTS " +TABLE_ITEMS);
	    db.execSQL("DROP TABLE IF EXISTS " +TABLE_LISTS);
	    db.execSQL("DROP TABLE IF EXISTS " +TABLE_ITEM_LIST);
	    onCreate(db);
    }
}
