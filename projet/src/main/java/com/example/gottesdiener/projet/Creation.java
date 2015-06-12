package com.example.gottesdiener.projet;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class Creation extends ActionBarActivity {
    Spinner spinner;
    Resources resources;
    ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ActionBar ab = getSupportActionBar();

        ab.setTitle("");
        ab.setDisplayHomeAsUpEnabled(true);


        TranslateAnimation translateAnimation = new TranslateAnimation(1100,0,0,0);
        translateAnimation.setStartOffset(1100);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(1000);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout);
        linearLayout.startAnimation(translateAnimation);

        String jour[] = new String[31];
        for (int i=0;i<31;i++) jour[i] = String.valueOf(i+1);
        spinner = (Spinner) findViewById(R.id.jj);
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,jour);
        spinner.setAdapter(arrayAdapter);


        spinner = (Spinner) findViewById(R.id.list_mois);
        resources = getResources();
        String[] mois = resources.getStringArray(R.array.mois);
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,mois);
        spinner.setAdapter(arrayAdapter);

        spinner = (Spinner) findViewById(R.id.list_annee);
        String[] anneeUniversitaire = resources.getStringArray(R.array.annee_univ);
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,anneeUniversitaire);
        spinner.setAdapter(arrayAdapter);

    }

    public void createUser(View view){
        String nom  = ((EditText) findViewById(R.id.ed1)).getText().toString();
        String prenom  = ((EditText) findViewById(R.id.ed2)).getText().toString();

        String dateNais = ((EditText) findViewById(R.id.aa)).getText().toString()+"-"+
                (((Spinner) findViewById(R.id.list_mois)).getSelectedItemPosition()+1)+"-"+
                ((Spinner) findViewById(R.id.jj)).getSelectedItem();

        String lieunais  = ((EditText) findViewById(R.id.ed3)).getText().toString();
        String adresse  = ((EditText) findViewById(R.id.ed4)).getText().toString();

        String anneeUniv = (String) ((Spinner) findViewById(R.id.list_annee)).getSelectedItem();

        String mail  = ((EditText) findViewById(R.id.ed5)).getText().toString();
        String pass  = ((EditText) findViewById(R.id.ed6)).getText().toString();
        String cpass  = ((EditText) findViewById(R.id.ed7)).getText().toString();

        if (pass.hashCode()==cpass.hashCode()){
            ArrayList<String> param = new ArrayList<>();
            param.add(nom);
            param.add(prenom);
            param.add(adresse);
            param.add(dateNais);
            param.add(lieunais);
            param.add(anneeUniv);
            param.add(mail);
            param.add(pass.hashCode() + "");
            try{
                new InsertUser(this,param).execute();
                Toast.makeText(this,"L'utilisateur a été ajouté avec succès !",Toast.LENGTH_SHORT);
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(this,"Dispositif hors connexion! ",Toast.LENGTH_SHORT);
            }

            this.finish();
        }else ((EditText) findViewById(R.id.ed7)).setError("Les mots de passe ne sont pas identiques");
    }


}
