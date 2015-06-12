package com.example.gottesdiener.projet;

import android.widget.TextView;

/**
 * Created by Gottesdiener on 27/03/2015.
 */
public class CommentaireHolder {
    private TextView nomTV;
    private TextView prenomTV;
    private TextView contenuTV;
    private TextView dateTV;

    public CommentaireHolder(TextView nomTV, TextView prenomTV, TextView contenuTV) {
        this.nomTV = nomTV;
        this.prenomTV = prenomTV;
        this.contenuTV = contenuTV;
    }

    public void setDateTV(TextView dateTV) {
        this.dateTV = dateTV;
    }

    public TextView getDateTV() {

        return dateTV;
    }

    public CommentaireHolder(CommentaireHolder holder){
        this.setNomTV(holder.getNomTV());
        this.setPrenomTV(holder.getPrenomTV());
        this.setContenuTV(holder.getContenuTV());
        this.setDateTV(holder.getDateTV());
    }

    public CommentaireHolder(){

    }

    public TextView getNomTV() {
        return nomTV;
    }

    public TextView getPrenomTV() {
        return prenomTV;
    }

    public TextView getContenuTV() {
        return contenuTV;
    }

    public void setNomTV(TextView nomTV) {
        this.nomTV = nomTV;
    }

    public void setPrenomTV(TextView prenomTV) {
        this.prenomTV = prenomTV;
    }

    public void setContenuTV(TextView contenuTV) {
        this.contenuTV = contenuTV;
    }


}
