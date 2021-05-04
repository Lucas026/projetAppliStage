package com.dut2.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Calendar;

public class manuel extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    TextView date;
    EditText codeArticle, article, numPalette;
    Spinner spinnerDefaut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manuel);

        date = findViewById(R.id.textView_Date);
        codeArticle = findViewById(R.id.editText_codeArticle);
        article = findViewById(R.id.editText_Article);
        numPalette = findViewById(R.id.editText_numPalette);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        date.setText(currentDate);

        codeArticle.getText();
        article.getText();
        numPalette.getText();

        spinnerDefaut = findViewById(R.id.spinner_defaut);
        ArrayAdapter<CharSequence>adapter = ArrayAdapter.createFromResource(this, R.array.liste_Defaut, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDefaut.setAdapter(adapter);
        spinnerDefaut.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
