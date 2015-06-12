package com.example.gottesdiener.projet;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by a26 on 27/05/15.
 */
public class Notification extends BroadcastReceiver{

    public void onReceive(Context context, Intent intent) {
        int count = -1;
        ArrayList<String> categorie = new ArrayList<>();
        try {
            Resources resources = context.getResources();
            String[] categories = resources.getStringArray(R.array.categorie);

            for (int i=0;i < categories.length;i++){

                if (!categories[i].equalsIgnoreCase("catégorie")){

                    SharedPreferences sharedPreferences = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
                    String str = sharedPreferences.getString(categories[i], "");

                    if (!str.equalsIgnoreCase("")){

                        count = new GetCountBook(context,categories[i]).execute().get();
                        DataBaseHandler dataBaseHandler = new DataBaseHandler(context);
                        int previousCount = dataBaseHandler.getCount(categories[i]);

                        if (count > previousCount){
                            dataBaseHandler.updateCount(categories[i], count);
                            categorie.add(categories[i]);
                        }
                    }
                }
            }
            if (!categorie.isEmpty())notification(context, categorie);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(context, "Dispositif hors connexion! ", Toast.LENGTH_SHORT);
        }
    }

    private void notification(Context context,ArrayList<String> categories) {
        // Intent intent = new Intent("dz.esi.intent.notification");
        // PendingIntent pendingIntent= PendingIntent.getActivity(this,0,intent,0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        //long[] pattern = {500,500,500,1000};
        //builder.setVibrate(pattern);
        Uri alarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(alarm);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("Nouveautés");
        String query = "Il y'a de nouveaux livres parmis vos catégories : ";
        for (int i=0; i<categories.size(); i++){
            query += categories.get(i);
            if (i!=categories.size()-1) query += ", ";
        }
        builder.setContentText(query);

        //builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // notificationID allows you to update the notification later on.
        mNotificationManager.notify(1, builder.build());
    }
}
