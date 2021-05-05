package com.dut2.test;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class automatique extends AppCompatActivity {

  Button Scan;
  TextView afficheScan;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.automatique);

    Scan = findViewById(R.id.bt_scan);
    afficheScan = findViewById(R.id.textView_afficheCode);

    Scan.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        IntentIntegrator intentIntegrator = new IntentIntegrator(automatique.this);
        intentIntegrator.setPrompt("Pour le flash utiliser le bouton haut du son");
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.setCaptureActivity(Capture.class);
        intentIntegrator.initiateScan();
      }
    });

  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

    if(intentResult.getContents() != null){
      afficheScan.setText(intentResult.getContents());
    }else{
      Toast.makeText(getApplicationContext(), "Vous n'avez rien scannez", Toast.LENGTH_SHORT).show();
    }
  }
}
