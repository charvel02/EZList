package EZListDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper
{

	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version)
	{
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		//create database
		db.execSQL(EZListDatabaseAdapter.CREATE_LISTS_TABLE);
		db.execSQL(EZListDatabaseAdapter.CREATE_ITEM_LIST_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		// Log the version upgrade.
		Log.w("TaskDBAdapter", "Upgrading from version " 
				+oldVersion + " to " +newVersion 
				+ ", which will destroy all old data");
		
        // Upgrade the existing database to conform to the new version. Multiple
        // previous versions can be handled by comparing _oldVersion and _newVersion
        // values.
        // The simplest case is to drop the old table and create a new one.
        db.execSQL("DROP TABLE IF EXISTS " + "TABLE_LIST");
        db.execSQL("DROP TABLE IF EXISTS " + "ITEM_LIST");
        // Create a new one.
        onCreate(db);

	}

}
