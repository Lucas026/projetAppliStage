package com.dut2.test;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class manuel extends AppCompatActivity{

  private static final int CUTE_PHOTO = 2;
  TextView date, textViewImage;
    EditText codeArticle, article, numPalette, numLot;
    Spinner spinnerDefaut, spinnerChantier, spinnerResponsabilite, spinnerOrigine;
    Button btnPhoto, btnEnvoie;
    ImageView affichePhoto;
    String photoPaths;

    SQLiteHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manuel);

        date = findViewById(R.id.textView_Date);
        codeArticle = findViewById(R.id.editText_codeArticle);
        //article = findViewById(R.id.editText_Article);
        //numPalette = findViewById(R.id.editText_numPalette);
        //numLot= findViewById(R.id.editText_numLot);
        spinnerDefaut = findViewById(R.id.spinner_defaut);
        spinnerChantier = findViewById(R.id.spinner_chantier);
        spinnerResponsabilite = findViewById(R.id.spinner_reponsabilite);
        spinnerOrigine = findViewById(R.id.spinner_origine);
        btnEnvoie = findViewById(R.id.button_envoieManuel);
        textViewImage = findViewById(R.id.textView_Photo);

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
        btnPhoto = (Button) findViewById(R.id.button_photo);
        affichePhoto = (ImageView) findViewById(R.id.imageView_affichePhoto);
        createOnClickBtnPhoto();
        createOnClickEnvoie();
    }

    private void createOnClickEnvoie(){
      btnEnvoie.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          if(VerifCodeArticle() /*&& VerifPhoto()*/){

            /*
            Bitmap image = BitmapFactory.decodeFile(photoPaths);
            // convert bitmap to byte
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] imageInByte = stream.toByteArray();
            */

            db = new SQLiteHelper(getApplicationContext());
            if(db.addBDD(codeArticle.getText().toString(), date.getText().toString(), spinnerDefaut.getSelectedItem().toString(), spinnerChantier.getSelectedItem().toString(),
              spinnerOrigine.getSelectedItem().toString(), spinnerResponsabilite.getSelectedItem().toString()/*, imageInByte*/)){
              Intent intent = new Intent(manuel.this, validation.class);
              startActivity(intent);
            }
          }
        }
      });
    }


    private void createOnClickBtnPhoto(){
        btnPhoto.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                //prendreUnePhoto();
              openCamera();
            }
        });
    }
/*
    private void prendreUnePhoto(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null){
            String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File photoDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            try {
                File photoFile = File.createTempFile("photo" + time,".png", photoDir);
                photoPaths = photoFile.getAbsolutePath();
                Uri photoUri = FileProvider.getUriForFile(manuel.this, manuel.this.getApplicationContext().getPackageName() + ".provider", photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intent, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
*/

  private Uri photoUri;

  public void openCamera()  {

    File outputImg = new File(affichePhoto.getContext().getExternalCacheDir(), System.currentTimeMillis() + ".jpg");
    if (outputImg.exists()) {
      outputImg.delete();
    }
    try {
      outputImg.createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (Build.VERSION.SDK_INT >= 24) {
      photoUri = FileProvider.getUriForFile(affichePhoto.getContext(),
        manuel.this.getApplicationContext().getPackageName() + ".provider", outputImg);
    } else {
      photoUri = Uri.fromFile(outputImg);
    }
    // check for camera permission
    int permissionCheck = ContextCompat.checkSelfPermission(affichePhoto.getContext(), Manifest.permission.CAMERA);

    // do we have camera permission?
    if (permissionCheck == PackageManager.PERMISSION_GRANTED) {

      Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
      if(intent.resolveActivity(getPackageManager()) != null){
        String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File photoDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        try {
          File photoFile = File.createTempFile("photo" + time,".png", photoDir);
          photoPaths = photoFile.getAbsolutePath();
          photoUri = FileProvider.getUriForFile(manuel.this, manuel.this.getApplicationContext().getPackageName() + ".provider", photoFile);
          intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
          startActivityForResult(intent, 1);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

    } else {

      // we don't have it, request camera permission from system
      ActivityCompat.requestPermissions((Activity) affichePhoto.getContext(),
        new String[]{Manifest.permission.CAMERA}, 100);
    }
  }

    /**
     * retour de l'appel de l'appareil photo
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK){
          Bitmap image = BitmapFactory.decodeFile(photoPaths);
          affichePhoto.setImageBitmap(image);
        }

    }

    private boolean VerifCodeArticle(){
      if(codeArticle.getText().toString().isEmpty()){
        codeArticle.setError("Vous n'avez rien entrer");
        codeArticle.requestFocus();
        return false;
      }
      else{
        return true;
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

    /*
    private boolean VerifArticle(){
      if(article.getText().toString().isEmpty()){
        article.setError("Vous n'avez rien entrer");
        article.requestFocus();
        return false;
      }
      else{
        return true;
      }
    }

  private boolean VerifNumPalette(){
    if(numPalette.getText().toString().isEmpty()){
      numPalette.setError("Vous n'avez rien entrer");
      numPalette.requestFocus();
      return false;
    }
    else{
      return true;
    }
  }

  private boolean VerifDeLot(){
    if(numLot.getText().toString().isEmpty()){
      numLot.setError("Vous n'avez rien entrer");
      numLot.requestFocus();
      return false;
    }
    else{
      return true;
    }
  }
  */
}
