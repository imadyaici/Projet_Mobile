package com.example.a26.myapplication.backend;
import com.mysql.jdbc.ResultSetImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Imad on 22/05/15.
 */
public class BaseDeDonnees {

        public static final String className = "com.mysql.jdbc.Driver";
        public static final String chaine = "jdbc:mysql://localhost:3306/books_db";
        public static final String username = "root";
        public static final String password = "";

        public Connection connecter() {
            Connection con = null;
            try {
                Class.forName(className);
                con = DriverManager.getConnection(chaine, username, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return con;
        }

        public List<Livre> getAllBooks() {
            List<Livre> list = new ArrayList<>();
            String query = "select * from t_books";
            try {
                Connection con = connecter();
                Statement st = con.createStatement();
                ResultSet cursor = st.executeQuery(query);
                while (cursor.next()) {
                    Livre book = new Livre();
                    book.setId(cursor.getInt(1));
                    book.setCategorie(cursor.getString(2));
                    book.setTitre(cursor.getString(3));
                    book.setAuteur(cursor.getString(4));
                    book.setAnneeEdition(cursor.getString(5));
                    book.setResume(cursor.getString(6));
                    book.setImageView(cursor.getBytes(7));
                    list.add(book);
                }
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            return list;
        }

        public ArrayList<Livre> getBookByTitle(String title){
            String query = "select * from t_books where titre = ?";
            Connection con = connecter();
            ArrayList<Livre> list = new ArrayList<>();
            try{
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1,title);
                ResultSet cursor = preparedStatement.executeQuery();
                while (cursor.next()) {
                    Livre book = new Livre();
                    book.setId(cursor.getInt(1));
                    book.setCategorie(cursor.getString(2));
                    book.setTitre(cursor.getString(3));
                    book.setAuteur(cursor.getString(4));
                    book.setAnneeEdition(cursor.getString(5));
                    book.setResume(cursor.getString(6));
                    book.setImageView(cursor.getBytes(7));
                    list.add(book);
                }
                con.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
            return list;
        }

        public int getCount(String categorie){
            String query = "select count(*) from t_books where categorie = ?";
            Connection con = connecter();
            try{
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1,categorie);
                ResultSet cursor = preparedStatement.executeQuery();
                if (cursor.next()) {
                    int count = cursor.getInt(1);
                    return count;
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            return 0;
        }

        public int insertUser(String nom,String prenom,String date,String lieu
                                , String adresse, String anneeu,String mail,String pass){
            String query = "insert into t_user ( nom, prenom, adresse, date_nais, lieu_nais, annee_univ, mail, password) " +
                    "values ( ?, ?, ?, ?, ?, ?, ?, ?)";
            Connection con = connecter();
            try{
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1,nom);
                preparedStatement.setString(2,prenom);
                preparedStatement.setString(3,date);
                preparedStatement.setString(4,lieu);
                preparedStatement.setString(5,adresse);
                preparedStatement.setString(6,anneeu);
                preparedStatement.setString(7,mail);
                preparedStatement.setString(8,pass);
                preparedStatement.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }
            return 0;
        }

    public int getPassword(String mail){
        String query = "select password from t_user where mail = ?";
        Connection con = connecter();
        try{
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1,mail);
            ResultSet cursor = preparedStatement.executeQuery();
            if (cursor.next()) {
                int pass = cursor.getInt(1);
                return pass;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

}
