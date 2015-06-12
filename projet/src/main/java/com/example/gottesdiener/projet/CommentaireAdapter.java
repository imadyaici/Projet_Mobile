package com.example.gottesdiener.projet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Gottesdiener on 27/03/2015.
 */
public class CommentaireAdapter extends BaseAdapter implements View.OnClickListener{

    private ArrayList<Commentaire> listeCommentaires;
    private LayoutInflater monInflateur;
    private Context monContext;
    private int currentPage;
    private int pagination;


    public CommentaireAdapter(Context context, ArrayList<Commentaire> listeCommentaires, int pagination){
        this.monInflateur = LayoutInflater.from(context);
        this.monContext = context;
        this.listeCommentaires = listeCommentaires;
        this.pagination = pagination;
        this.currentPage = 1;
    }

    @Override
    public int getCount() {
        return Math.min(this.listeCommentaires.size(),this.pagination * this.currentPage);
    }

    @Override
    public Object getItem(int position) {
        return this.listeCommentaires.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommentaireHolder commentaireHolder;
        if (convertView == null){
            convertView = monInflateur.inflate(R.layout.liste_commentaires,null);
            commentaireHolder = new CommentaireHolder();
            commentaireHolder.setNomTV((TextView) convertView.findViewById(R.id.nomCommentaire));
            commentaireHolder.setPrenomTV((TextView) convertView.findViewById(R.id.prenomCommentaire));
            commentaireHolder.setContenuTV((TextView) convertView.findViewById(R.id.contenuCommentaire));
            commentaireHolder.setDateTV((TextView) convertView.findViewById(R.id.dateCommentaire));
            convertView.setTag(commentaireHolder);
        }else{
            commentaireHolder = new CommentaireHolder((CommentaireHolder) convertView.getTag());
        }

        if (position==this.currentPage*this.pagination-1){
            TextView textView = (TextView) convertView.findViewById(R.id.plus);
            textView.setVisibility(View.VISIBLE);
            textView.setOnClickListener(this);
        }else{
            TextView textView = (TextView) convertView.findViewById(R.id.plus);
            textView.setVisibility(View.GONE);
            commentaireHolder.getNomTV().setText("Nom : "+listeCommentaires.get(position).getNom());
            commentaireHolder.getPrenomTV().setText(" "+listeCommentaires.get(position).getPrenom());
            commentaireHolder.getContenuTV().setText("Contenu :\n"+listeCommentaires.get(position).getContenu());
            commentaireHolder.getDateTV().setText("  "+dateToString(listeCommentaires.get(position).getDate()));
        }
        return convertView;
    }

    public String dateToString(Date date){
        return date.getDate()+"-"+date.getMonth()+"-"+date.getYear()+" "+date.getHours()+":"+date.getMinutes();
    }

    @Override
    public void onClick(View v) {
        currentPage++;
        notifyDataSetChanged();
        v.setVisibility(View.GONE);
    }
}
