package com.example.androiddemo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

	public class DBAdapter {
		
		public static final String KEY_ROWID = "_id";
		public static final String KEY_FIRST = "first_name";
		public static final String KEY_LAST = "last_name";
		public static final String KEY_DATE = "date";
		public static final String DATABASE_NAME = "BirthdayDB";
		private static final String DATABASE_TABLE = "birthdays";
		private static final int DATABASE_VERSION = 1;

		private DbHelper myHelper;
		private final Context myContext;
		private SQLiteDatabase myDB;
		
		public DBAdapter(Context c){
			myContext = c;
		}	
		
		private static class DbHelper extends SQLiteOpenHelper{

			public DbHelper(Context context) {
				super(context, DATABASE_NAME, null, DATABASE_VERSION);
				// TODO Auto-generated constructor stub
			}

			@Override
			public void onCreate(SQLiteDatabase arg0) {
				arg0.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
						KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
						KEY_FIRST + " TEXT NOT NULL, " +
						KEY_LAST + " TEXT NOT NULL, " +
						KEY_DATE + " TEXT NOT NULL);");
				
			}

			@Override
			public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
		}

		//---opens the database---
		public DBAdapter open(){
			myHelper = new DbHelper(myContext);
			myDB = myHelper.getWritableDatabase();
			return this;
		}
		
		//---close the database---
		public void close(){
			myHelper.close();
		}
		
		//---inserts a record into the database---
		public long createEntry(String first, String last, String date) {
			ContentValues cv = new ContentValues();
			cv.put(KEY_FIRST, first);
			cv.put(KEY_LAST, last);
			cv.put(KEY_DATE, date);
			return myDB.insert(DATABASE_TABLE, null, cv);
		}
		

		public ArrayList<String> dbToString() {
			String[] columns = new String []{KEY_ROWID, KEY_FIRST,KEY_LAST, KEY_DATE};
			String order = KEY_LAST + " ASC";
			Cursor c = myDB.query(DATABASE_TABLE, columns, null, null, null, null, order);
			ArrayList<String> result = new ArrayList<String>();
			
			int iFirst = c.getColumnIndex(KEY_FIRST);
			int iLast = c.getColumnIndex(KEY_LAST);
			
			for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
				result.add(c.getString(iLast) +", " +c.getString(iFirst));
			}
			return result;
		}
		

	}
