package com.dut2.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class accueil extends AppCompatActivity {

    Button man, auto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil);

        man = findViewById(R.id.btn_manuel);
        auto = findViewById(R.id.btn_auto);

        //Redirige vers la classe "manuel"
        man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(accueil.this, manuel.class);
                startActivity(intent);
            }
        });

        //Redirige vers la classe "automatique"
        auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(accueil.this, automatique.class);
                startActivity(intent);
            }
        });
    }
}