package com.dut2.test;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class automatique extends AppCompatActivity {

  Button Scan, btnPhoto;
  TextView afficheScan, date;
  Spinner spinnerDefaut, spinnerChantier, spinnerResponsabilite, spinnerOrigine, spinnerCommande;
  ImageView affichePhoto;
  String photoPaths;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.automatique);

    Scan = findViewById(R.id.bt_scan);
    afficheScan = findViewById(R.id.textView_afficheCode);
    date = findViewById(R.id.textView_Date);
    spinnerDefaut = findViewById(R.id.spinner_defautAuto);
    spinnerChantier = findViewById(R.id.spinner_chantierAuto);
    spinnerResponsabilite = findViewById(R.id.spinner_reponsabiliteAuto);
    spinnerOrigine = findViewById(R.id.spinner_origineAuto);

    Scan.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        IntentIntegrator intentIntegrator = new IntentIntegrator(automatique.this);
        //Intent intent = intentIntegrator.createScanIntent();
        intentIntegrator.setPrompt("Pour le flash utiliser le bouton haut du son");
        intentIntegrator.setDesiredBarcodeFormats(intentIntegrator.ALL_CODE_TYPES);
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.setCaptureActivity(Capture.class);
        intentIntegrator.setBarcodeImageEnabled(false);
        intentIntegrator.setRequestCode(1);
        intentIntegrator.initiateScan();
        //startActivityForResult(intent, REQUEST_CODE);
      }
    });

    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String formattedDate = df.format(calendar.getTime());
    date.setText(formattedDate);

    //Spinner des defauts
    List<String> listeDefaut = new ArrayList<>();
    listeDefaut.add("Absence tapis de sol");
    listeDefaut.add("Bac mal collé");
    listeDefaut.add("Chapeau décalé / mal mis");
    listeDefaut.add("Chocs manutention");
    listeDefaut.add("Cols cassés");
    listeDefaut.add("Cols manquant");
    listeDefaut.add("Condensation");
    listeDefaut.add("Corps étranger dans palette (insectes, etc...)");
    listeDefaut.add("Coup de fourche");
    listeDefaut.add("Demi lune");
    listeDefaut.add("Double plaque / plaque manquante");
    listeDefaut.add("Etiquette tombée / Mal positionnée / Colle sur bouteille");
    listeDefaut.add("Housse déchiré");
    listeDefaut.add("Housse mal rétracté");
    listeDefaut.add("Lit en boule");
    listeDefaut.add("Manque liens / détendus / mal positionnés");
    listeDefaut.add("Mauvaise soudure de toit");
    listeDefaut.add("Mauvais étiquetage");
    listeDefaut.add("Mauvais soudure tapis au sol");
    listeDefaut.add("Palettes bois KC");
    listeDefaut.add("Petit trou (2-3mm) origine brûlure");
    listeDefaut.add("Pli chapeau");
    listeDefaut.add("Pli craquelé");
    listeDefaut.add("Plaque décalée");
    listeDefaut.add("Sale(Poussière, fientes, oiseaux, etc...)");
    listeDefaut.add("Sans étiquette");
    listeDefaut.add("Trou angle de coiffe");
    listeDefaut.add("Trou de chauffe");
    listeDefaut.add("Trou angle de bac");
    listeDefaut.add("Trou angle PPA");
    listeDefaut.add("Trou jonction join");
    listeDefaut.add("Trou sur coiffe à la manipulation");
    listeDefaut.add("Verticalité palette");
    ArrayAdapter<String> adapter_defaut = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listeDefaut);
    adapter_defaut.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinnerDefaut.setAdapter(adapter_defaut);
    spinnerDefaut.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text_defaut = parent.getItemAtPosition(position).toString();
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
    });

    //Spinner des chantier à faire
    List<String> listeChantier = new ArrayList<>();
    listeChantier.add("Balayette");
    listeChantier.add("Casser");
    listeChantier.add("DH/RH");
    listeChantier.add("Essuyage");
    ArrayAdapter<String> adapter_chantier = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listeChantier);
    adapter_chantier.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinnerChantier.setAdapter(adapter_chantier);
    spinnerChantier.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text_chantier = parent.getItemAtPosition(position).toString();
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
    });

    //Spinner de la responsabilité
    List<String> listeResponsable = new ArrayList<>();
    listeResponsable.add("Derichebourg");
    listeResponsable.add("Verallia");
    ArrayAdapter<String> adapter_responsable = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listeResponsable);
    adapter_chantier.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinnerResponsabilite.setAdapter(adapter_responsable);
    spinnerResponsabilite.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text_responsable = parent.getItemAtPosition(position).toString();
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
    });

    //Spinner de l'origine des palettes'
    List<String> listeOrigine = new ArrayList<>();
    listeOrigine.add("Atelier retri");
    listeOrigine.add("Parc");
    listeOrigine.add("Production");
    ArrayAdapter<String> adapter_origine = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listeOrigine);
    adapter_origine.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinnerOrigine.setAdapter(adapter_origine);
    spinnerOrigine.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text_origine = parent.getItemAtPosition(position).toString();
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
    });

    //Pour les photos
   initActivity();
  }

  private void initActivity(){
    btnPhoto = (Button) findViewById(R.id.button_photoAuto);
    affichePhoto = (ImageView) findViewById(R.id.imageView_affichePhotoAuto);
    createOnClickBtnPhoto();
  }

  private void createOnClickBtnPhoto(){
    btnPhoto.setOnClickListener(new Button.OnClickListener(){
      @Override
      public void onClick(View v) {
        prendreUnePhoto();
      }
    });
  }

  private void prendreUnePhoto(){
    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    if(intent.resolveActivity(getPackageManager()) != null){
      String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
      File photoDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
      try {
        File photoFile = File.createTempFile("photo"+time,".jpg", photoDir);
        photoPaths = photoFile.getAbsolutePath();
        Uri photoUri = FileProvider.getUriForFile(automatique.this, automatique.this.getApplicationContext().getPackageName() + ".provider", photoFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        startActivityForResult(intent, 2);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

    super.onActivityResult(requestCode, resultCode, data);
    IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

    switch (requestCode){
      case(2):
        Bitmap image = BitmapFactory.decodeFile(photoPaths);
        affichePhoto.setImageBitmap(image);
        break;
      //ne marche pas pour l'instant
      case (1):
        if(intentResult.getContents() != null){
          afficheScan.setText(intentResult.getContents());
        }else{
          Toast.makeText(getApplicationContext(), "Vous n'avez rien scannez", Toast.LENGTH_SHORT).show();
        }
        break;

      default:
        break;
    }
  }
}
