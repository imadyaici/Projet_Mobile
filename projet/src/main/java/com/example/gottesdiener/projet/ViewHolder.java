package com.example.gottesdiener.projet;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Gottesdiener on 24/03/2015.
 */
public class ViewHolder {
    private ImageView imageView;
    private TextView titre;
    private TextView auteur;
    private TextView resume;
    private TextView categorie;
    private TextView anneeEdition;

    public ViewHolder(ImageView imageView, TextView titre, TextView auteur, TextView resume, TextView categorie, TextView anneeEdition) {
        this.imageView = imageView;
        this.titre = titre;
        this.auteur = auteur;
        this.resume = resume;
        this.categorie = categorie;
        this.anneeEdition = anneeEdition;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public TextView getTitre() {
        return titre;
    }

    public TextView getAuteur() {
        return auteur;
    }

    public TextView getResume() {
        return resume;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setTitre(TextView titre) {
        this.titre = titre;
    }

    public void setAuteur(TextView auteur) {
        this.auteur = auteur;
    }

    public void setResume(TextView resume) {
        this.resume = resume;
    }

    public void setCategorie(TextView categorie) {
        this.categorie = categorie;
    }

    public void setAnneeEdition(TextView anneeEdition) {
        this.anneeEdition = anneeEdition;
    }

    public TextView getCategorie() {
        return categorie;
    }

    public TextView getAnneeEdition() {
        return anneeEdition;
    }

    public ViewHolder(ImageView imageView, TextView titre, TextView auteur, TextView resume) {
        this.imageView = imageView;
        this.titre = titre;
        this.auteur = auteur;
        this.resume = resume;
    }

    public ViewHolder() {
    }

    public ViewHolder(ViewHolder holder) {
        this.imageView = holder.getImageView();
        this.titre = holder.getTitre();
        this.auteur = holder.getAuteur();
        this.resume = holder.getResume();
        this.anneeEdition = holder.getAnneeEdition();
        this.categorie = holder.getCategorie();
    }
}
