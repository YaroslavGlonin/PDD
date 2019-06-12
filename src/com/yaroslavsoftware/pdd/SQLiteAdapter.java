package com.yaroslavsoftware.pdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class SQLiteAdapter {

 public static final String MYDATABASE_NAME = "MY_DATABASE";
 public static final String MYDATABASE_TABLE = "MY_TABLE";
 public static final int MYDATABASE_VERSION =1;
 public static final String KEY_ID = "_id";
 public static final String KEY_CONTENT1 = "Content1";
 public static final String KEY_CONTENT2 = "Content2";
 public static final String KEY_CONTENT3 = "Content3";
 public static final String KEY_CONTENT4 = "Content4";

 //create table MY_DATABASE (ID integer primary key, Content text not null);
 private static final String SCRIPT_CREATE_DATABASE =
		  "create table " + MYDATABASE_TABLE + " ("
		  + KEY_ID + " integer primary key autoincrement, "
		  + KEY_CONTENT1 + " text not null, "
		  + KEY_CONTENT2 + " text not null, "
		  + KEY_CONTENT3 + " text not null, "
		  + KEY_CONTENT4 + " text not null);";
public static final String R = null;
 
 private SQLiteHelper sqLiteHelper;
 private SQLiteDatabase sqLiteDatabase;

 private Context context;
 
 public SQLiteAdapter(Context c){
  context = c;
 }
 
 public SQLiteAdapter openToRead() throws android.database.SQLException {
  sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
  sqLiteDatabase = sqLiteHelper.getReadableDatabase();
  return this; 
 }
 
 public SQLiteAdapter openToWrite() throws android.database.SQLException {
  sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
  sqLiteDatabase = sqLiteHelper.getWritableDatabase();
  return this; 
 }
 
 public void close(){
  sqLiteHelper.close();
 }
 
 public long insert(String content1, String content2, String content3, String content4){
  
  ContentValues contentValues = new ContentValues();
  contentValues.put(KEY_CONTENT1, content1);
  contentValues.put(KEY_CONTENT2, content2);
  contentValues.put(KEY_CONTENT3, content3);
  contentValues.put(KEY_CONTENT4, content4);
  return sqLiteDatabase.insert(MYDATABASE_TABLE, null, contentValues);
 }
 public long update(int id, String content1, String content2){
	  
	  ContentValues contentValues = new ContentValues();
	  contentValues.put(KEY_CONTENT1, content1);
	  contentValues.put(KEY_CONTENT2, content2);
	  return sqLiteDatabase.update(MYDATABASE_TABLE,contentValues, KEY_ID+"="+id, null);
	 }
public long insert_data(int id, String content3, String content4){
	  
	  ContentValues contentValues = new ContentValues();
	  contentValues.put(KEY_CONTENT4, content3);
	  contentValues.put(KEY_CONTENT4, content4);
	  return sqLiteDatabase.update(MYDATABASE_TABLE,contentValues, KEY_ID+"="+id, null);
	 }
 
 public int deleteAll(){
  return sqLiteDatabase.delete(MYDATABASE_TABLE, null, null);
 }
 
 public void delete_byID(int id){
	  sqLiteDatabase.delete(MYDATABASE_TABLE, KEY_ID+"="+id, null);
	 }
 
 public Cursor queueAll(){
  String[] columns = new String[]{KEY_ID, KEY_CONTENT1, KEY_CONTENT2, KEY_CONTENT3, KEY_CONTENT4};
  Cursor cursor = sqLiteDatabase.query(MYDATABASE_TABLE, columns,
    null, null, null, null, null);
  
  return cursor;
 }
 public Cursor queueAll_SortBy_CONTENT4(){
	 String[] columns = new String[]{KEY_ID, KEY_CONTENT1, KEY_CONTENT2, KEY_CONTENT3, KEY_CONTENT4};
	  Cursor cursor = sqLiteDatabase.query(MYDATABASE_TABLE, columns,
	    null, null, null, null, KEY_CONTENT4);
	  
	  return cursor;
	 }
 public Cursor queueAll_SortBy_CONTENT3(){
	 String[] columns = new String[]{KEY_ID, KEY_CONTENT1, KEY_CONTENT2, KEY_CONTENT3, KEY_CONTENT4};
	  Cursor cursor = sqLiteDatabase.query(MYDATABASE_TABLE, columns,
	    null, null, null, null, KEY_CONTENT3);
	  
	  return cursor;
	 }
 public class SQLiteHelper extends SQLiteOpenHelper {

  public SQLiteHelper(Context context, String name,
    CursorFactory factory, int version) {
   super(context, name, factory, version);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
   // TODO Auto-generated method stub
   db.execSQL(SCRIPT_CREATE_DATABASE);
  }
  

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	 // Log.d("set db", "ok");
	 /* if (oldVersion >= 1 && newVersion <= 3) 
	  {
		    	db.execSQL("ALTER TABLE " + 
				  			MYDATABASE_TABLE + 
				  			" ADD COLUMN " + KEY_CONTENT3);
		    	db.execSQL("ALTER TABLE " + 
			  			MYDATABASE_TABLE + 
			  			" ADD COLUMN " + KEY_CONTENT4);
		    	
		    //	onCreate(db);
		    	//Log.d("set db", "ok");
	  }*/
	  
  }
 } 
}