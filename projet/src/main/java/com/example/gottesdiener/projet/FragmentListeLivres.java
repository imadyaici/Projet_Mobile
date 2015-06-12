package com.example.gottesdiener.projet;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import static android.view.View.VISIBLE;

/**
 * Created by Gottesdiener on 28/03/2015.
 */
public class FragmentListeLivres extends Fragment implements SearchView.OnQueryTextListener
        ,AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

    ArrayList<Livre> listeLivres = new ArrayList<Livre>();
    ImageView imageView;
    Resources resources;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.activity_fragment,container,false);
    }


    public void onStart() {
        super.onStart();
        resources = getResources();

        DataBaseHandler dataBaseHandler = new DataBaseHandler(getActivity());
        listeLivres = dataBaseHandler.getAllBooks();
        ListView listView = (ListView) getActivity().findViewById(R.id.listview);
        MonAdaptateur monAdapteteur = new MonAdaptateur(getActivity(),listeLivres,10);
        listView.setAdapter(monAdapteteur);
        listView.setOnItemClickListener(this);


        listView.setOnItemClickListener(this);

        SearchView searchView = (SearchView) getActivity().findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(this);

        Spinner spinner = (Spinner) getActivity().findViewById(R.id.spinnerab);
        spinner.setOnItemSelectedListener(this);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ListView listView = (ListView) getActivity().findViewById(R.id.listview);
        listView.setVisibility(View.GONE);

        TextView titreLivre = (TextView) view.findViewById(R.id.titreLivre);
        TextView auteurLivre = (TextView) view.findViewById(R.id.auteurLivre);
        TextView categorieLivre = (TextView) view.findViewById(R.id.categorieLivre);
        TextView resumeLivre = (TextView) view.findViewById(R.id.resumeLivre);
        TextView anneeEditionLivre = (TextView) view.findViewById(R.id.anneeEditionLivre);
        ImageView couvertureLivre = (ImageView) getActivity().findViewById(R.id.imageLivre);

        Bundle bundle = new Bundle();
        bundle.putString("titre",titreLivre.getText().toString());
        bundle.putString("auteur",auteurLivre.getText().toString());
        bundle.putString("categorie",categorieLivre.getText().toString());
        bundle.putString("resume",resumeLivre.getText().toString());
        bundle.putString("annee",anneeEditionLivre.getText().toString());

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentDetail fragmentDetail = new FragmentDetail();
        fragmentDetail.setArguments(bundle);
        fragmentTransaction.replace(R.id.layoutFragment1,fragmentDetail).replace(R.id.layoutFragment, new FragmentListeLivres());
        fragmentTransaction.commit();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String categorie = (String) parent.getItemAtPosition(position);
        imageView = (ImageView) getActivity().findViewById(R.id.star);
        imageView.setVisibility(View.GONE);
        if (!categorie.equalsIgnoreCase("Catégorie")){
            imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.white_star));
            imageView.setVisibility(VISIBLE);

            ArrayList<Livre> list = filtreCategorie(listeLivres, categorie);
            MonAdaptateur monAdapteteur = new MonAdaptateur(getActivity(),list,10);
            ListView listView = (ListView) getActivity().findViewById(R.id.listview);
            listView.setAdapter(monAdapteteur);
            listView.setOnItemClickListener(this);
            Log.e("List", list.toString());
        }else{
            ListView listView = (ListView) getActivity().findViewById(R.id.listview);
            MonAdaptateur monAdapteteur = new MonAdaptateur(getActivity(),listeLivres,10);
            listView.setAdapter(monAdapteteur);
            listView.setOnItemClickListener(this);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        ArrayList<Livre> list = filtredListBooks(listeLivres,query);
        MonAdaptateur monAdapteteur = new MonAdaptateur(getActivity(),list,10);
        ListView listView = (ListView) getActivity().findViewById(R.id.listview);
        listView.setAdapter(monAdapteteur);
        listView.setOnItemClickListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }


    public ArrayList<Livre> filtredListBooks(ArrayList<Livre> listBooks, String query){
        ArrayList<Livre> filtredList = new ArrayList<Livre>();
        for (int i=0;i<listBooks.size();i++){
            if (listBooks.get(i).containAllKeyWords(query)) filtredList.add(listBooks.get(i));
        }
        return filtredList;
    }

    public ArrayList<Livre> filtreCategorie(ArrayList<Livre> listBooks, String categorie){
        ArrayList<Livre> filtredList = new ArrayList<Livre>();
        for (int i=0;i<listBooks.size();i++){
            if (listBooks.get(i).getCategorie().toLowerCase().equals(categorie.toLowerCase())) filtredList.add(listBooks.get(i));
        }
        return filtredList;
    }

}
