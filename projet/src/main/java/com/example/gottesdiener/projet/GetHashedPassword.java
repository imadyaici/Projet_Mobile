package com.example.gottesdiener.projet;

import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by a26 on 29/05/15.
 */
public class GetHashedPassword extends AsyncTask<String,Void,String> {
    String mail;
    Context context;

    public GetHashedPassword(Context context,String categorie){
        this.mail = categorie;
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
//        String url ="http://192.168.56.1:8080/pass?mail="+this.mail;
        String url ="http://192.168.94.1:8080/pass?mail="+this.mail;
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        String result =null;
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            result= EntityUtils.toString(httpResponse.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
