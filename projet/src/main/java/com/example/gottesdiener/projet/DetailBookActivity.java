package com.example.gottesdiener.projet;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class DetailBookActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_book);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.hide();
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            TextView textView = (TextView) findViewById(R.id.titre);
            TextView textView2 = (TextView) findViewById(R.id.auteur);
            TextView textView3 = (TextView) findViewById(R.id.categorie);
            TextView textView4 = (TextView) findViewById(R.id.resume);
            TextView textView5 = (TextView) findViewById(R.id.annee);
            ImageView imageView = (ImageView) findViewById(R.id.imad);


            DataBaseHandler dataBaseHandler = new DataBaseHandler(this);
            Livre livre = dataBaseHandler.getBookByTitle(extras.getString("titre"));

            if(livre==null){
                try {
                    String str = new GetBookByTitle(this,extras.getString("titre")).execute().get();
                    Type type = new TypeToken<List<Livre>>(){}.getType();
                    ArrayList<Livre> list = new Gson().fromJson(str, type);
                    livre = list.get(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

            textView.setText("Titre : "+livre.getTitre());
            textView2.setText("Auteur : "+livre.getAuteur());
            textView3.setText("Catégorie : "+livre.getCategorie());
            textView4.setText("Résumé : \n"+livre.getResume());
            textView5.setText("Année d'édition : "+livre.getAnneeEdition());

            byte[] image = livre.getImageView();
            ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
            Bitmap theImage = BitmapFactory.decodeStream(imageStream);
            imageView.setImageBitmap(theImage);


            SearchView searchView = (SearchView) findViewById(R.id.searchView);
            searchView.setVisibility(View.GONE);

            Spinner spinner = (Spinner) findViewById(R.id.spinnerab);
            spinner.setVisibility(View.GONE);

            ImageButton imageButton = (ImageButton) findViewById(R.id.back);
            imageButton.setVisibility(View.VISIBLE);
        }
    }


    public void finish(View view){
        this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_book, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this,MainActivity.class);
            this.startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showComment(View view){
        Intent intent = new Intent(this,CommentairesActivity.class);
        this.startActivityForResult(intent,1000);
    }



}
