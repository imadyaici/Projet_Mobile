package com.example.a26.myapplication.backend;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by a26 on 28/05/15.
 */
public class CreationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String date = req.getParameter("date");
        String lieu = req.getParameter("lieu");
        String adresse = req.getParameter("adresse");
        String anneeu = req.getParameter("anneeu");
        String mail = req.getParameter("mail");
        String pass = req.getParameter("pass");
        new BaseDeDonnees().insertUser(nom,prenom,adresse,date,lieu,anneeu,mail,pass);
    }
}
