package mx.unam.desarrolloappsavanzadas;

//import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;


import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Gravity;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import mx.unam.desarrolloappsavanzadas.Menus.mConfigurarCuenta;
import mx.unam.desarrolloappsavanzadas.Menus.mRecibirNotificaciones;

/**
 * Created by Roy on 23/07/2016.
 */
public class NotificationService extends FirebaseMessagingService {

    private static String TAG = "MyFirebaseMsgService";
    public static int NOTIFICATION_ID = 001;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //super.onMessageReceived(remoteMessage);

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Esto se comenta para implementar las acciones en la notificación
        /*
        Intent intent = new Intent(this, mRecibirNotificaciones.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        */

        Intent intentPrincipal = new Intent(this, MainActivity.class);
        PendingIntent pendingIntentPrincipal = PendingIntent.getBroadcast(this, 0, intentPrincipal, PendingIntent.FLAG_UPDATE_CURRENT);


        // Para las acciones de la notificación
        Intent intent1 = new Intent( );
        intent1.setAction("VER_PERFIL");
        PendingIntent pendingIntent1 = PendingIntent.getBroadcast(this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent intent2 = new Intent( );
        intent2.setAction("FOLLOW");
        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(this, 2, intent2, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent intent3 = new Intent( );
        intent3.setAction("VER_USUARIO");
        PendingIntent pendingIntent3 = PendingIntent.getBroadcast(this, 3, intent3, PendingIntent.FLAG_UPDATE_CURRENT);



        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        // Para la notificación en el smartwatch
        NotificationCompat.Action action1 = new NotificationCompat.Action.Builder(R.drawable.ic_full_corgi_96,
                "Ver perfil", pendingIntent1).build();

        NotificationCompat.Action action2 = new NotificationCompat.Action.Builder(R.drawable.ic_full_dog_park_96,
                "Follow", pendingIntent2).build();

        NotificationCompat.Action action3 = new NotificationCompat.Action.Builder(R.drawable.ic_full_year_of_dog_96,
                "Ver usuario", pendingIntent3).build();


        // Para el fondo en el smartwatch (en la notificación)
        NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender()
                .setHintHideIcon(true)
                .setBackground(BitmapFactory.decodeResource(getResources(), R.drawable._rbol))
                .setGravity(Gravity.CENTER_VERTICAL)
                ;


        NotificationCompat.Builder notificationCompat = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.dog_footprint_52)
                .setContentTitle("Notificacion")
                .setContentText(remoteMessage.getNotification().getBody())
                .setSound(sonido)
                .setContentIntent(pendingIntentPrincipal)
                .setAutoCancel(true)
                .extend(wearableExtender.addAction(action1))
                .extend(wearableExtender.addAction(action2))
                .extend(wearableExtender.addAction(action3))
                .addAction(action1)
                .addAction(action2)
                .addAction(action3)
                ;

    /* Estas líneas se comentan para implementar Android Wear
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationCompat.build());
    */

        // Para Android Wear
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID, notificationCompat.build());

    }
}
