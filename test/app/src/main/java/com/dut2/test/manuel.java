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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class manuel extends AppCompatActivity{

    TextView date;
    EditText codeArticle, article, numPalette;
    Spinner spinnerDefaut, spinnerChantier, spinnerResponsabilite, spinnerOrigine, spinnerCommande;
    Button btnPhoto;
    ImageView affichePhoto;
    String photoPaths;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manuel);

        date = findViewById(R.id.textView_Date);
        codeArticle = findViewById(R.id.editText_codeArticle);
        article = findViewById(R.id.editText_Article);
        numPalette = findViewById(R.id.editText_numPalette);
        spinnerDefaut = findViewById(R.id.spinner_defaut);
        spinnerChantier = findViewById(R.id.spinner_chantier);
        spinnerResponsabilite = findViewById(R.id.spinner_reponsabilite);
        spinnerOrigine = findViewById(R.id.spinner_origine);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(calendar.getTime());
        date.setText(formattedDate);

        codeArticle.getText();
        article.getText();
        numPalette.getText();

        //Spinner des defauts
        List<String> listeDefaut = new ArrayList<>();
        listeDefaut.add("Trou jonction join");
        listeDefaut.add("Absence tapis de sol");
        listeDefaut.add("Chapeau décalé / mal mis");
        listeDefaut.add("Bac mal collé");
        listeDefaut.add("Manque liens / détendus / mal positionnés");
        listeDefaut.add("Cols manquant");
        listeDefaut.add("Double plaque / plaque manquante");
        listeDefaut.add("Plaque décalée");
        listeDefaut.add("Lit en boule");
        listeDefaut.add("Verticalité palette");
        listeDefaut.add("Mauvaise soudure de toit");
        listeDefaut.add("Demi lune");
        listeDefaut.add("Pli craquelé");
        listeDefaut.add("Pli chapeau");
        listeDefaut.add("Trou angle de coiffe");
        listeDefaut.add("Trou de chauffe");
        listeDefaut.add("Trou angle de bac");
        listeDefaut.add("Trou angle PPA");
        listeDefaut.add("Mauvais soudure tapis au sol");
        listeDefaut.add("Housse mal rétracté");
        listeDefaut.add("Petit trou (2-3mm) origine brûlure");
        listeDefaut.add("Coup de fourche");
        listeDefaut.add("Cols cassés");
        listeDefaut.add("Housse déchiré");
        listeDefaut.add("Chocs manutention");
        listeDefaut.add("Trou sur coiffe à la manipulation");
        listeDefaut.add("Palettes bois KC");
        listeDefaut.add("Mauvais étiquetage");
        listeDefaut.add("Etiquette tombée / Mal positionnée / Colle sur bouteille");
        listeDefaut.add("Sans étiquette");
        listeDefaut.add("Sale(Poussière, fientes, oiseaux, etc...)");
        listeDefaut.add("Condensation");
        listeDefaut.add("Corps étranger dans palette (insectes, etc...)");
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
        listeChantier.add("DH/RH");
        listeChantier.add("Essuyage");
        listeChantier.add("Balayette");
        listeChantier.add("Casser");
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
        listeResponsable.add("Verallia");
        listeResponsable.add("Derichebourg");
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
        listeOrigine.add("Production");
        listeOrigine.add("Parc");
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
                Uri photoUri = FileProvider.getUriForFile(manuel.this, manuel.this.getApplicationContext().getPackageName() + ".provider", photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intent, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
}
