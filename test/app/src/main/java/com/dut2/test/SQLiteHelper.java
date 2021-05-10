package com.dut2.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

  public static String BDD_NAME="PaletteNCBDD", TABLE_NAME="PaletteNC", CODE_ARTICLE="code_article";
  public static String DATE="date", DEFAUT="DEFAUT", CHANTIER="CHANTIER", ORIGINE="ORIGINE", RESPONSABILITE="RESPONSABILITE", PHOTO="PHOTO";

  public SQLiteHelper(@Nullable Context context) {
    super(context, BDD_NAME, null, 1);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    String sql =
            "CREATE TABLE " + TABLE_NAME +" (" +
                    CODE_ARTICLE + " INTEGER PRIMARY KEY," +
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

}
