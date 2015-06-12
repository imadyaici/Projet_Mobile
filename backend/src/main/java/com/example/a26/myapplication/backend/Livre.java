package com.example.a26.myapplication.backend;

import java.sql.Blob;

/**
 * Created by Gottesdiener on 24/03/2015.
 */
public class Livre {
    private int id;
    private String categorie;
    private String titre;
    private String auteur;
    private String anneeEdition;
    private String resume;
    private byte[] imageView;

    public Livre(String categorie, String titre, String auteur, String anneeEdition, String resume, byte[] imageView) {
        this.categorie = categorie;
        this.titre = titre;
        this.auteur = auteur;
        this.anneeEdition = anneeEdition;
        this.resume = resume;
        this.imageView = imageView;
    }



    public Livre(String categorie, String titre, String auteur, String resume, byte[] imageView) {
        this.categorie = categorie;
        this.titre = titre;
        this.auteur = auteur;
        this.resume = resume;
        this.imageView = imageView;
    }

    public Livre(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Livre(int id, String categorie, String titre, String auteur, String anneeEdition, String resume, byte[] imageView) {
        this.id = id;
        this.categorie = categorie;
        this.titre = titre;
        this.auteur = auteur;
        this.anneeEdition = anneeEdition;
        this.resume = resume;
        this.imageView = imageView;
    }

    public void setImageView(byte[] imageView) {
        this.imageView = imageView;
    }

    public byte[] getImageView() {
        return imageView;
    }



    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getCategorie() {
        return categorie;
    }

    public Livre(String titre, String auteur, String resume, byte[] imageView) {
        this.titre = titre;
        this.auteur = auteur;
        this.resume = resume;
        this.imageView = imageView;
    }

    public Livre() {

    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public String getAnneeEdition() {
        return anneeEdition;
    }

    public String getResume() {
        return resume;
    }


    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setAnneeEdition(String anneeEdition) {
        this.anneeEdition = anneeEdition;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public boolean containKeyWord (String word){
        if (this.titre.toLowerCase().indexOf(word.toLowerCase()) != -1
               || this.categorie.toLowerCase().indexOf(word.toLowerCase()) != -1
                    || this.auteur.toLowerCase().indexOf(word.toLowerCase()) != -1
                        || this.resume.toLowerCase().indexOf(word.toLowerCase()) != -1) {
            return true;
        } else {
            return false;
        }
    }


    public boolean containAllKeyWords (String sentence){
        String[] words = sentence.split("\\s+");
        boolean bool = true;
        int i=0;
        while (i<words.length && bool==true){
            if (this.containKeyWord(words[i])==false) {
                bool=false;
            }
            else {
                i++;
            }
        }
        return bool;
    }
}
