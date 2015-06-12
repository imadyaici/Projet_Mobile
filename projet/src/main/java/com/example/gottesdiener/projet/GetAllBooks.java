package com.example.gottesdiener.projet;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by a26 on 22/05/15.
 */
public class GetAllBooks extends AsyncTask<Void,Void,String>{
    private Context context;
    ProgressDialog pd = null;

    public GetAllBooks(Context context) {
        this.context = context;
    }


    protected void onPreExecute() {
        pd = ProgressDialog.show(context, "Attendez", "En progression...",true);
    }


    protected String doInBackground(Void... params) {
//        String url ="http://192.168.56.1:8080/book";
        String url ="http://192.168.94.1:8080/book";
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        String result ="";
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            result= EntityUtils.toString(httpResponse.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    protected void onPostExecute(String s) {
        pd.cancel();
        Type type = new TypeToken<List<Livre>>(){}.getType();
        ArrayList<Livre> list = new Gson().fromJson(s, type);
        ListView listView = (ListView) ((Activity)context).findViewById(R.id.listview);
        MonAdaptateur monAdapteteur = new MonAdaptateur(context,list,10);
        listView.setAdapter(monAdapteteur);
    }


}
