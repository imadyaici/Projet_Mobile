package com.example.gottesdiener.projet;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import static android.widget.Spinner.*;


public class ListBooksActivity extends ActionBarActivity implements SearchView.OnQueryTextListener
        ,OnItemClickListener, OnItemSelectedListener{

    ArrayList<Livre> listeLivres = new ArrayList<Livre>();
    ImageView imageView;
    Resources resources;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_books);

        ActionBar ab = getSupportActionBar();
        ab.hide();

        resources = getResources();

        SearchView searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(this);

        Spinner spinner = (Spinner) findViewById(R.id.spinnerab);
        spinner.setOnItemSelectedListener(this);

        //new GetAllBooks(this).execute();

        Intent intent = new Intent("dz.intent.notif");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE, 5);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),1000*360*24*30, pendingIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        DataBaseHandler dataBaseHandler = new DataBaseHandler(this);
        listeLivres = dataBaseHandler.getAllBooks();
        ListView listView = (ListView) this.findViewById(R.id.listview);
        MonAdaptateur monAdapteteur = new MonAdaptateur(this,listeLivres,25);
        listView.setAdapter(monAdapteteur);
        listView.setOnItemClickListener(this);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void marquerInteressante(View view){
        Spinner spinner = (Spinner) findViewById(R.id.spinnerab);
        String categorie= (String) spinner.getSelectedItem();
        ImageView imageView = (ImageView) view;

        SharedPreferences sharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
        String str = sharedPreferences.getString(categorie, "");

        if (str.equalsIgnoreCase("")){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(categorie,categorie);
            editor.commit();
            imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.gold_star));
            Toast.makeText(this,"Catégorie ajoutée au favories",Toast.LENGTH_SHORT).show();
        }else{
            sharedPreferences.edit().remove(categorie).commit();
            imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.white_star));
            Toast.makeText(this,"Catégorie supprimée des favories",Toast.LENGTH_SHORT).show();
        }


    }

    public ArrayList<Livre> filtreCategorie(ArrayList<Livre> listBooks, String categorie){
        ArrayList<Livre> filtredList = new ArrayList();
        for (int i=0;i<listBooks.size();i++){
            if (listBooks.get(i).getCategorie().equalsIgnoreCase(categorie)) filtredList.add(listBooks.get(i));
        }
        return filtredList;
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setVisibility(View.GONE);

        TextView titreLivre = (TextView) view.findViewById(R.id.titreLivre);

        Intent intent = new Intent(this,DetailBookActivity.class);
        intent.putExtra("titre",titreLivre.getText().toString().substring(8));

        this.startActivity(intent);

    }

    public ArrayList<Livre> filtredListBooks(ArrayList<Livre> listBooks, String query){
        ArrayList<Livre> filtredList = new ArrayList();
        for (int i=0;i<listBooks.size();i++){
            if (listBooks.get(i).containAllKeyWords(query)) filtredList.add(listBooks.get(i));
        }
        return filtredList;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        //ArrayList<Livre> list = filtredListBooks(listeLivres,query);
        ArrayList<Livre> list = new ArrayList();
        DataBaseHandler dataBaseHandler = new DataBaseHandler(this);
        Livre book = dataBaseHandler.getBookByTitle(query);
        if (book!=null){
            list.add(book);

        }else{
            try {
                String str = new GetBookByTitle(this,query).execute().get();
                Type type = new TypeToken<List<Livre>>(){}.getType();
                list = new Gson().fromJson(str, type);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
                Toast.makeText(this, "Dispositif hors connexion! ", Toast.LENGTH_SHORT);
            }
        }
        MonAdaptateur monAdapteteur = new MonAdaptateur(this,list,10);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(monAdapteteur);
        listView.setOnItemClickListener(this);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String categorie = (String) parent.getItemAtPosition(position);
        imageView = (ImageView) findViewById(R.id.star);
        imageView.setVisibility(View.GONE);
        if (!categorie.equalsIgnoreCase("Catégorie")){
            SharedPreferences sharedPreferences = getSharedPreferences("pref",Context.MODE_PRIVATE);
            String str = sharedPreferences.getString(categorie,"");

            if (str.equalsIgnoreCase("")){
                imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.white_star));
            }else{
                imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.gold_star));
            }


            imageView.setVisibility(VISIBLE);

            ArrayList<Livre> list = filtreCategorie(listeLivres, categorie);
            MonAdaptateur monAdapteteur = new MonAdaptateur(this,list,10);
            ListView listView = (ListView) findViewById(R.id.listview);
            listView.setAdapter(monAdapteteur);
            listView.setOnItemClickListener(this);
        }else{
            ListView listView = (ListView) this.findViewById(R.id.listview);
            MonAdaptateur monAdapteteur = new MonAdaptateur(this,listeLivres,10);
            listView.setAdapter(monAdapteteur);
            listView.setOnItemClickListener(this);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_books, menu);
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
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
