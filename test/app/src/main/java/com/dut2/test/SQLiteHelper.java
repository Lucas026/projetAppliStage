package com.dut2.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

  public static String BDD_NAME="PaletteNCBDD", TABLE_NAME="PaletteNC", ID="_id";
  public static String CODE_ARTICLE="code_article", DATE="date", DEFAUT="DEFAUT", CHANTIER="CHANTIER", ORIGINE="ORIGINE", RESPONSABILITE="RESPONSABILITE", PHOTO="PHOTO";

  public SQLiteHelper(@Nullable Context context) {
    super(context, BDD_NAME, null, 1);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    /*
    String sql =
            "CREATE TABLE " + TABLE_NAME +" (" +
                    CODE_ARTICLE + " INTEGER PRIMARY KEY," +
                    DATE + " DATE," +
                    DEFAUT + " TEXT," +
                    CHANTIER + " TEXT," +
                    ORIGINE + " TEXT," +
                    RESPONSABILITE + " TEXT," +
                    PHOTO + " BLOB)";
    */
    String sql =
      "CREATE TABLE " + TABLE_NAME +" (" +
        ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
        CODE_ARTICLE + " INTEGER," +
        DATE + " DATE," +
        DEFAUT + " TEXT," +
        CHANTIER + " TEXT," +
        ORIGINE + " TEXT," +
        RESPONSABILITE + " TEXT," +
        PHOTO + " BLOB)";
    db.execSQL(sql);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    String sql= "DROP TABLE IF EXISTS " + TABLE_NAME;

    db.execSQL(sql);
    onCreate(db);
  }

  boolean addBDD(String codeArticle, String date, String defaut, String chantier, String origine, String responsabilite, byte[] photo){

    SQLiteDatabase db = this.getWritableDatabase();

    ContentValues data = new ContentValues();
    data.put(CODE_ARTICLE, codeArticle);
    data.put(DATE, date);
    data.put(DEFAUT, defaut);
    data.put(CHANTIER, chantier);
    data.put(ORIGINE, origine);
    data.put(RESPONSABILITE, responsabilite);
    data.put(PHOTO, photo);

    long result = db.insert(TABLE_NAME, null, data);
    if(result != -1){
      return true;
    }
    else
    {
      return false;
    }
  }

  public Cursor affichePaletteBDD(){
    /*
    SQLiteDatabase db = this.getReadableDatabase();

    String query = "SELECT * FROM " + TABLE_NAME;
    Cursor cursor = db.rawQuery(query, null);

    return cursor;
    */
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor mCursor = db.query(TABLE_NAME, new String[] {ID, CODE_ARTICLE, DATE, DEFAUT, CHANTIER, ORIGINE, RESPONSABILITE}, null, null, null, null, null);

    if(mCursor != null){
      mCursor.moveToFirst();
    }
    return  mCursor;
  }



  public Cursor fetchAfficheByName(String inputText) throws SQLException{
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor mCursor = null;
    if(inputText == null || inputText.length() == 0){
      mCursor = db.query(TABLE_NAME, new String[] {ID, CODE_ARTICLE, DATE, DEFAUT, CHANTIER, ORIGINE, RESPONSABILITE}, null, null, null, null, null);
    }
    else{
      mCursor = db.query(TABLE_NAME, new String[] {ID, CODE_ARTICLE, DATE, DEFAUT, CHANTIER, ORIGINE, RESPONSABILITE}, CODE_ARTICLE + " like '%" + inputText + "%'", null, null, null, null, null);
    }
    if(mCursor != null){
      mCursor.moveToFirst();
    }
    return  mCursor;
  }
}
