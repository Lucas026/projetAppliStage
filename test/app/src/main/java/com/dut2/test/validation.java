package com.dut2.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class validation extends AppCompatActivity {

  Button retour;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.validation);

    retour = findViewById(R.id.btn_Retour);

    retour.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(validation.this, accueil.class);
        startActivity(intent);
      }
    });

  }
}
