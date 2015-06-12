package com.example.gottesdiener.projet;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;


public class FragmentActivity extends ActionBarActivity /*implements AdapterView.OnItemClickListener*/{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listes_livres);

        ActionBar ab = getSupportActionBar();
        ab.hide();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentListeLivres fragmentListeLivres = new FragmentListeLivres();
        fragmentTransaction.replace(R.id.layoutFragment, fragmentListeLivres).commit();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_listes_livres, menu);
        return super.onCreateOptionsMenu(menu);
    }



    public void editTex(View view){
        EditText editText = (EditText) view;
        editText.setText("");
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar2);
        ratingBar.setVisibility(View.VISIBLE);
    }



    public void marquerInteressante(View view){
        ImageView imageView = (ImageView) view;
        imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.gold_star));
       // imageView.setBackground(getResources().getDrawable(R.drawable.gold_star));
    }


    public void showComment(View view){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentCommentaire fragmentCommentaire = new FragmentCommentaire();
        fragmentTransaction.replace(R.id.layoutFragment1, fragmentCommentaire).commit();
    }


}
