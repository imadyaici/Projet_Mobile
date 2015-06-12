package com.example.a26.myapplication.backend;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by a26 on 29/05/15.
 */
public class PasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mail = req.getParameter("mail");
        int password = new BaseDeDonnees().getPassword(mail);
        Gson gson = new Gson();
        resp.getWriter().print(gson.toJson(password));
    }
}
