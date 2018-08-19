package com.wooplr.brandwith.brandwith;

import android.annotation.SuppressLint;
import android.app.Notification;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.google.firebase.messaging.FirebaseMessagingService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;
import com.squareup.picasso.Picasso;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.Map;

/**
 * Created by Sagar on 10-08-2018.
 */

public class FcmMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
       if (remoteMessage.getData().size()>0){
           String title,message,img_url;
           title = remoteMessage.getData().get("title");
           message = remoteMessage.getData().get("message");
           img_url = remoteMessage.getData().get("img_url");

           Intent intent = new Intent(this,MainActivity.class);
           intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
           PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
           Uri sounduri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

           final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
           builder.setContentTitle(title);
           builder.setContentText(message);
           builder.setContentIntent(pendingIntent);
           builder.setSound(sounduri);
           builder.setSmallIcon(R.mipmap.brand);

           ImageRequest imageRequest = new ImageRequest(img_url, new Response.Listener<Bitmap>() {
               @Override
               public void onResponse(Bitmap response) {
                   builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(response));
                   NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                   notificationManager.notify(0,builder.build());

               }
           }, 0, 0, null,Bitmap.Config.RGB_565, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {

               }
           });

           MySingleton.getmInstance(this).addToRequest(imageRequest);
       }

    }

}
