package com.example.gottesdiener.projet;

import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by a26 on 29/05/15.
 */
public class InsertUser extends AsyncTask<String,Void,Void> {
    Context context;
    ArrayList<String> param;

    public InsertUser(Context context,ArrayList<String> param){
        this.context = context;
        this.param = param;
    }

    @Override
    protected Void doInBackground(String... strings) {
//        String url ="http://192.168.56.1:8080/creation?nom="+param.get(0)
//                +"&prenom="+param.get(1)+"&adresse="+param.get(2)+"&date="+param.get(3)+"&lieu="+param.get(4)
//                +"&anneeu="+param.get(5)+"&mail="+param.get(6)+"&pass="+param.get(7);

        String url ="http://192.168.94.1:8080/creation?nom="+param.get(0)
                +"&prenom="+param.get(1)+"&adresse="+param.get(2)+"&date="+param.get(3)+"&lieu="+param.get(4)
                +"&anneeu="+param.get(5)+"&mail="+param.get(6)+"&pass="+param.get(7);

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        String result =null;
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            result= EntityUtils.toString(httpResponse.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
