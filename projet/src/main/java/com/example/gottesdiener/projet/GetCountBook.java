package com.example.gottesdiener.projet;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Calendar;

/**
 * Created by a26 on 27/05/15.
 */
public class GetCountBook extends AsyncTask<String, Void, Integer> {
    String categorie;
    Context context;

    public GetCountBook(Context context,String categorie){
        this.categorie = categorie;
        this.context = context;
    }


    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
    }

    @Override
    protected Integer doInBackground(String... strings) {
//        String url ="http://192.168.56.1:8080/count?categorie="+this.categorie;
        String url ="http://192.168.94.1:8080/count?categorie="+this.categorie;
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        String result =null;
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            result= EntityUtils.toString(httpResponse.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(result);
    }
}
