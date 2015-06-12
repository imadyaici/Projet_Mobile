package com.example.gottesdiener.projet;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Gottesdiener on 31/03/2015.
 */
public class FragmentCommentaire extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_commentaire,container,false);
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void onStart() {
        super.onStart();
        ArrayList<Commentaire> listeCommentaires = new ArrayList();
        listeCommentaires.add(new Commentaire("NOM","PRENOM","CONTENU",new Date(2015,1,1,00,00)));
        listeCommentaires.add(new Commentaire("NOM1","PRENOM1","CONTENU1",new Date(2015,1,1,00,00)));
        listeCommentaires.add(new Commentaire("NOM2","PRENOM2","CONTENU2",new Date(2015,1,1,00,00)));
        listeCommentaires.add(new Commentaire("NOM3","PRENOM3","CONTENU3",new Date(2015,1,1,00,00)));
        listeCommentaires.add(new Commentaire("NOM4","PRENOM4","CONTENU4",new Date(2015,1,1,00,00)));
        listeCommentaires.add(new Commentaire("NOM5","PRENOM5","CONTENU5",new Date(2015,1,1,00,00)));
        listeCommentaires.add(new Commentaire("NOM6","PRENOM6","CONTENU6",new Date(2015,1,1,00,00)));
        listeCommentaires.add(new Commentaire("NOM7","PRENOM7","CONTENU7",new Date(2015,1,1,00,00)));

        ListView listView = (ListView) getActivity().findViewById(R.id.listCommentaire);
        CommentaireAdapter monAdapteteur = new CommentaireAdapter(getActivity(),listeCommentaires,40);
        listView.setAdapter(monAdapteteur);
    }
}
