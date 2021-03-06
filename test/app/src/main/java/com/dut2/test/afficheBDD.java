package com.dut2.test;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLInput;
import java.util.ArrayList;
import java.util.List;

public class afficheBDD extends AppCompatActivity {

  SQLiteHelper dbHelper;
  SimpleCursorAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.affichebdd);

    dbHelper = new SQLiteHelper(this);
    displayListView();
  }

  private void displayListView(){
    Cursor cursor = dbHelper.affichePaletteBDD();

    String[] columns = new String[]{
      SQLiteHelper.CODE_ARTICLE,
      SQLiteHelper.DATE,
      SQLiteHelper.NUM_PALETTE,
      SQLiteHelper.NUM_LOT,
      SQLiteHelper.DEFAUT,
      SQLiteHelper.CHANTIER,
      SQLiteHelper.ORIGINE,
      SQLiteHelper.RESPONSABILITE
    };

    int[] to = new int[] {
      R.id.code_article,
      R.id.date,
      R.id.palette,
      R.id.lot,
      R.id.defaut,
      R.id.chantier,
      R.id.origine,
      R.id.responsabilite
    };

    adapter = new SimpleCursorAdapter(
      this, R.layout.color,
      cursor,
      columns,
      to,
      0
    );

    ListView listView = (ListView) findViewById(R.id.listView1);
    listView.setAdapter(adapter);

    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView listView, View view, int position, long id) {
        Cursor cursor = (Cursor) listView.getItemAtPosition(position);
        String afficheCode = cursor.getString(cursor.getColumnIndexOrThrow("code"));
        Toast.makeText(afficheBDD.this, afficheCode, Toast.LENGTH_SHORT).show();
      }
    });

    EditText myFilter = (EditText)findViewById(R.id.myFilter);
    myFilter.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        adapter.getFilter().filter(s.toString());
      }

      @Override
      public void afterTextChanged(Editable s) {

      }
    });

    adapter.setFilterQueryProvider(new FilterQueryProvider() {
      @Override
      public Cursor runQuery(CharSequence constraint) {
        return dbHelper.fetchAfficheByName(constraint.toString());
      }
    });
  }

}
