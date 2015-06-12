package com.example.gottesdiener.projet;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by a26 on 24/05/15.
 */
public class GetBookByTitle extends AsyncTask<String,Void,String>{

        private Context context;
        ProgressDialog pd = null;
        private String title;

        public GetBookByTitle(Context context, String title) {
            this.context = context;
            this.title = title;
        }

        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(context);
            pd.setTitle("Titre");
            pd.setMessage("En progression...");
            pd.show();
        }



        @Override
        protected String doInBackground(String... params) {
            //String title = params[0];
//            String url ="http://192.168.56.1:8080/titlebook?title="+this.title;
            String url ="http://192.168.94.1:8080/titlebook?title="+this.title;
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

        @Override
        protected void onPostExecute(String s) {
            pd.cancel();
        }

}


