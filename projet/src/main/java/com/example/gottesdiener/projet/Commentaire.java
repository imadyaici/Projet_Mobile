package com.example.gottesdiener.projet;

import java.util.Date;

/**
 * Created by Gottesdiener on 27/03/2015.
 */
public class Commentaire {
    private String nom;
    private String prenom;
    private String contenu;
    private Date date;

    /**
     * CECI EST UN TEST YA JMA3A
     * */


    public void fonctionmaTdirWalou (){

    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {

        return date;
    }

    public Commentaire(String nom, String prenom, String contenu, Date date) {
        this.nom = nom;
        this.prenom = prenom;
        this.contenu = contenu;
        this.date = date;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getContenu() {
        return contenu;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
}
