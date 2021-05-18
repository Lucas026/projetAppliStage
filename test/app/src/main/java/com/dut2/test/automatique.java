package com.dut2.test;

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

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class automatique extends AppCompatActivity {

  Button Scan1, Scan2, Scan3, btnPhoto, btnEnvoie, btnMail, btnExcel;
  TextView afficheScan1, afficheScan2, afficheScan3, date, textViewImage;
  Spinner spinnerDefaut, spinnerChantier, spinnerResponsabilite, spinnerOrigine, spinnerCommande;
  ImageView affichePhoto;
  String photoPaths;

  public static int REQUEST_CODE = 0x0000c0de;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.automatique);

    Scan1 = findViewById(R.id.bt_scan);
    Scan2 = findViewById(R.id.bt_scanContent);
    Scan3 = findViewById(R.id.bt_scanSSCC);
    afficheScan1 = findViewById(R.id.textView_afficheCode);
    afficheScan2 = findViewById(R.id.textView_afficheCodeContent);
    afficheScan3 = findViewById(R.id.textView_afficheCodeSSCC);
    date = findViewById(R.id.textView_Date);
    spinnerDefaut = findViewById(R.id.spinner_defautAuto);
    spinnerChantier = findViewById(R.id.spinner_chantierAuto);
    spinnerResponsabilite = findViewById(R.id.spinner_reponsabiliteAuto);
    spinnerOrigine = findViewById(R.id.spinner_origineAuto);
    textViewImage = findViewById(R.id.textView_Photo);
    btnEnvoie = findViewById(R.id.button_envoieAuto);
    btnMail = findViewById(R.id.btn_envoie_mail);
    btnExcel = findViewById(R.id.btn_genere_excel);

    Scan1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        int requestCode = 2;
        IntentIntegrator intentIntegrator = new IntentIntegrator(automatique.this);
        intentIntegrator.setPrompt("Pour le flash utiliser le bouton haut du son");
        intentIntegrator.setDesiredBarcodeFormats(intentIntegrator.ALL_CODE_TYPES);
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.setCaptureActivity(Capture.class);
        intentIntegrator.setBarcodeImageEnabled(false);
        intentIntegrator.setRequestCode(requestCode);
        intentIntegrator.initiateScan();
      }
    });

    Scan2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        int requestCode = 3;
        IntentIntegrator intentIntegrator = new IntentIntegrator(automatique.this);
        intentIntegrator.setPrompt("Pour le flash utiliser le bouton haut du son");
        intentIntegrator.setDesiredBarcodeFormats(intentIntegrator.ALL_CODE_TYPES);
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.setCaptureActivity(Capture.class);
        intentIntegrator.setBarcodeImageEnabled(false);
        intentIntegrator.setRequestCode(requestCode);
        intentIntegrator.initiateScan();
      }
    });

    Scan3.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        int requestCode = 4;
        IntentIntegrator intentIntegrator = new IntentIntegrator(automatique.this);
        intentIntegrator.setPrompt("Pour le flash utiliser le bouton haut du son");
        intentIntegrator.setDesiredBarcodeFormats(intentIntegrator.ALL_CODE_TYPES);
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.setCaptureActivity(Capture.class);
        intentIntegrator.setBarcodeImageEnabled(false);
        intentIntegrator.setRequestCode(requestCode);
        intentIntegrator.initiateScan();
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
    createOnClickExcel();
    createOnClickMail();
    Envoie();
  }

  private void createOnClickMail(){
    btnMail.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(VerifPhoto() && VerifScan1() && VerifScan2() && VerifScan3()){

          File photoFile = new File (getExternalFilesDir(null), "photo.png");
          Uri photo = FileProvider.getUriForFile(automatique.this, automatique.this.getApplicationContext().getPackageName() + ".provider", photoFile);
          if(!photoFile.exists() || !photoFile.canRead()){
            return;
          }

          Intent emailIntent= new Intent(Intent.ACTION_SEND);
          emailIntent.setType("text/plain");
          emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"scan.palette@outlook.fr"});
          emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Palette xls");
          //emailIntent.putExtra(Intent.EXTRA_TEXT, "Palette xls");
          emailIntent.putExtra(Intent.EXTRA_TEXT,
            "Date : " + date.getText().toString() + "\n" +
              "Code article : " + afficheScan1.getText().toString() + "\n" +
              "Numéro de palette : " + afficheScan2.getText().toString() + "\n" +
              "Numéro de lot : " + afficheScan3.getText().toString() + "\n" +
              "Défaut : " + spinnerDefaut.getSelectedItem().toString() + "\n" +
              "Chantier : " + spinnerChantier.getSelectedItem().toString() + "\n" +
              "Origine : " + spinnerOrigine.getSelectedItem().toString() + "\n" +
              "Responsabilité : " + spinnerResponsabilite.getSelectedItem().toString()
          );
          emailIntent.putExtra(Intent.EXTRA_STREAM, photo);
          startActivity(Intent.createChooser(emailIntent, "Envoie mail ..."));
          btnEnvoie.setVisibility(View.VISIBLE);
        }
      }
    });
  }

  private void createOnClickExcel(){
    btnExcel.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(VerifPhoto() && VerifScan1() && VerifScan2() && VerifScan3()){
          buttonCreateExcel();
          btnMail.setVisibility(View.VISIBLE);
          btnExcel.setVisibility(View.GONE);
        }
      }
    });
  }

  public void buttonCreateExcel() {
    HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
    HSSFSheet hssfSheet = hssfWorkbook.createSheet("Donnees palette");

    HSSFRow hssfRow = hssfSheet.createRow(0);
    HSSFCell hssfCellDate = hssfRow.createCell(0);
    HSSFCell hssfCellScan1 = hssfRow.createCell(1);
    HSSFCell hssfCellScan2 = hssfRow.createCell(2);
    HSSFCell hssfCellScan3 = hssfRow.createCell(3);
    HSSFCell hssfCellDefaut = hssfRow.createCell(4);
    HSSFCell hssfCellChantier = hssfRow.createCell(5);
    HSSFCell hssfCellOrigine = hssfRow.createCell(6);
    HSSFCell hssfCellResponsabilite = hssfRow.createCell(7);
    HSSFCell hssfCellPhoto = hssfRow.createCell(8);

    hssfCellDate.setCellValue(date.getText().toString());
    hssfCellScan1.setCellValue(afficheScan1.getText().toString());
    hssfCellScan2.setCellValue(afficheScan2.getText().toString());
    hssfCellScan3.setCellValue(afficheScan3.getText().toString());
    hssfCellDefaut.setCellValue(spinnerDefaut.getSelectedItem().toString());
    hssfCellChantier.setCellValue(spinnerChantier.getSelectedItem().toString());
    hssfCellOrigine.setCellValue(spinnerOrigine.getSelectedItem().toString());
    hssfCellResponsabilite.setCellValue(spinnerResponsabilite.getSelectedItem().toString());

    Bitmap image = BitmapFactory.decodeFile(photoPaths);
    //Valeur de la photo en bitmap
    hssfCellPhoto.setCellValue(String.valueOf(image));

    File filePath = new File(getExternalFilesDir(null), "scanPalette.xls");

    try {
      if (!filePath.exists()){
        filePath.createNewFile();
      }

      FileOutputStream fileOutputStream= new FileOutputStream(filePath);
      hssfWorkbook.write(fileOutputStream);

      if (fileOutputStream!=null){
        fileOutputStream.flush();
        fileOutputStream.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void Envoie(){
    btnEnvoie.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(VerifPhoto() && VerifScan1() && VerifScan2() && VerifScan3()){
          Intent intent = new Intent(automatique.this, validation.class);
          startActivity(intent);
        }
      }
    });
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
        startActivityForResult(intent, 1);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

    super.onActivityResult(requestCode, resultCode, data);

    if(requestCode == 1){
      Bitmap image = BitmapFactory.decodeFile(photoPaths);
      affichePhoto.setImageBitmap(image);
    }else if (requestCode == 2){
      IntentResult intentResult = IntentIntegrator.parseActivityResult(resultCode ,data);
      if(intentResult.getContents() != null){
        afficheScan1.setText(intentResult.getContents());
      }else{
        Toast.makeText(getApplicationContext(), "Vous n'avez rien scannez", Toast.LENGTH_SHORT).show();
      }
    }else if(requestCode == 3){
      IntentResult intentResult = IntentIntegrator.parseActivityResult(resultCode ,data);
      if(intentResult.getContents() != null){
        afficheScan2.setText(intentResult.getContents());
      }else{
        Toast.makeText(getApplicationContext(), "Vous n'avez rien scannez", Toast.LENGTH_SHORT).show();
      }
    }else if(requestCode == 4){
      IntentResult intentResult = IntentIntegrator.parseActivityResult(resultCode ,data);
      if(intentResult.getContents() != null){
        afficheScan3.setText(intentResult.getContents());
      }else{
        Toast.makeText(getApplicationContext(), "Vous n'avez rien scannez", Toast.LENGTH_SHORT).show();
      }
    }
  }

  private boolean VerifPhoto(){
    if(null == affichePhoto.getDrawable()){
      textViewImage.setError("Vous n'avez pas pris de photo");
      textViewImage.requestFocus();
      return false;
    }
    else{
      return true;
    }
  }

  private boolean VerifScan1(){
    if(afficheScan1.getText() == "" ){
      afficheScan1.setError("Vous n'avez pas pris de photo");
      afficheScan1.requestFocus();
      return false;
    }
    else{
      return true;
    }
  }

  private boolean VerifScan2(){
    if(afficheScan2.getText() == "" ){
      afficheScan2.setError("Vous n'avez pas pris de photo");
      afficheScan2.requestFocus();
      return false;
    }
    else{
      return true;
    }
  }

  private boolean VerifScan3(){
    if(afficheScan3.getText() == "" ){
      afficheScan3.setError("Vous n'avez pas pris de photo");
      afficheScan3.requestFocus();
      return false;
    }
    else{
      return true;
    }
  }
}
