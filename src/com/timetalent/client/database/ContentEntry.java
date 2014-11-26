package com.timetalent.client.database;

/**
 */
 public class ContentEntry {

		/**
		 */
		 public static final String TABLE_NAME = "t_user";
		 /**
			 */
		 public static final String COLUMN_ID = "_id";
		/**
		 */
		 public static final String USER_ID = "u_id";
		 /**
			 */
		 public static final String SESSION_ID = "_session_id";
			 /**
				 */
		 public static final String UPDATE_STATE = "update_state";
		/**
		 */
		 public static final String[] PROJECTION = {
			 COLUMN_ID,
			 USER_ID,
			 SESSION_ID,
			 UPDATE_STATE,
			};
		
		/**
		 */
		 public static final String DEFAULT_ORDER = USER_ID + " asc ";
		
		/**
		 */
		 public static final String SQL_CREATE_TABLE = "create table " + TABLE_NAME + " ( " + 
				 COLUMN_ID + " INTEGER PRIMARY KEY autoincrement, "
				 + USER_ID + " text,"
				 + SESSION_ID + " text,"
				 + UPDATE_STATE + " text"
				+ " );";
		
		/**
		 */
		 public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
	
	
}
