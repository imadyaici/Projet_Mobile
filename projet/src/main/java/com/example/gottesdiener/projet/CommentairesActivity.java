package com.example.gottesdiener.projet;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Date;


public class CommentairesActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commentaires);

        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.hide();

        ArrayList<Commentaire> listeCommentaires = new ArrayList();
        listeCommentaires.add(new Commentaire("NOM","PRENOM","CONTENU",new Date(2015,1,1,00,00)));
        listeCommentaires.add(new Commentaire("NOM1","PRENOM1","CONTENU1",new Date(2015,1,1,00,00)));
        listeCommentaires.add(new Commentaire("NOM2","PRENOM2","CONTENU2",new Date(2015,1,1,00,00)));
        listeCommentaires.add(new Commentaire("NOM3","PRENOM3","CONTENU3",new Date(2015,1,1,00,00)));
        listeCommentaires.add(new Commentaire("NOM4","PRENOM4","CONTENU4",new Date(2015,1,1,00,00)));
        listeCommentaires.add(new Commentaire("NOM5","PRENOM5","CONTENU5",new Date(2015,1,1,00,00)));
        listeCommentaires.add(new Commentaire("NOM6","PRENOM6","CONTENU6",new Date(2015,1,1,00,00)));
        listeCommentaires.add(new Commentaire("NOM7","PRENOM7","CONTENU7",new Date(2015,1,1,00,00)));

        ListView listView = (ListView) findViewById(R.id.listCommentaire);
        CommentaireAdapter monAdapteteur = new CommentaireAdapter(this,listeCommentaires,20);
        listView.setAdapter(monAdapteteur);

        SearchView searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setVisibility(View.GONE);

        Spinner spinner = (Spinner) findViewById(R.id.spinnerab);
        spinner.setVisibility(View.GONE);

        ImageButton imageButton = (ImageButton) findViewById(R.id.back);
        imageButton.setVisibility(View.VISIBLE);

    }

    public void finish(View view){
        this.finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_commentaires, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void editTex(View view){
        EditText editText = (EditText) view;
        editText.setText("");
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar2);
        ratingBar.setVisibility(View.VISIBLE);
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void marquerInteressante(View view){
        ImageView imageView = (ImageView) view;
        imageView.setBackground(getResources().getDrawable(R.drawable.gold_star));
    }
}
