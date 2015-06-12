package com.example.a26.myapplication.backend;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by a26 on 27/05/15.
 */
public class CountServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categorie = req.getParameter("categorie");
        int count = new BaseDeDonnees().getCount(categorie);
        Gson gson = new Gson();
        resp.getWriter().print(gson.toJson(count));
    }
}
