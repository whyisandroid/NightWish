package com.timetalent.client.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

 public class SQLiteHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "userinfo";
	private static final int DATABASE_VERSION = 2;
	private SQLiteDatabase db = null;

	public SQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(ContentEntry.SQL_CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		db.execSQL(ContentEntry.SQL_DELETE_TABLE);
		onCreate(db);
	}
	
	/**
	 */
	public long insert(String table_name, ContentValues cv){
		this.db = this.getWritableDatabase();
		long rowid = db.insert(table_name, null, cv);
		db.close(); db = null;
		return rowid;
	}
	
	/**
	 */
	public Cursor query(String table_name, String[] projection, String selection, String[] selectionArgs, String orderBy){
		this.db = this.getReadableDatabase();
		
		Cursor c = db.query(table_name, projection, selection, selectionArgs, null, null, orderBy);
		return c;
	}
	
	/**
	 */
	public void delete(String table_name, String where, String[] whereArgs){
		this.db = this.getWritableDatabase();
		db.delete(table_name, where, whereArgs);
		db.close();	db = null;
	}
	
	/**
	 */
	public boolean update(String table_name, String where, String[] whereArgs, ContentValues cv){
		this.db = this.getWritableDatabase();
		int rows = db.update(table_name, cv, where, whereArgs);
		db.close(); db = null;
		if(rows > 0)
			return true;
		else
			return false;
	}
	
	/**
	 * @param sql
	 */
	public void execSQL(String sql) throws SQLException{
		this.db = this.getWritableDatabase();
		db.execSQL(sql);
		db.close(); db = null;
	}
	
	public void closeDatabase(){
		if(this.db != null){
			this.db.close();
			this.db = null;
		}
	}

}
