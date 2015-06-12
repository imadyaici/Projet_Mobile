package com.example.a26.myapplication.backend;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by a26 on 24/05/15.
 */
public class LivreTitreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String titre = req.getParameter("title");
        ArrayList<Livre> list = new BaseDeDonnees().getBookByTitle(titre);
        Gson gson = new Gson();
        resp.getWriter().print(gson.toJson(list));
    }
}
