package dwiyan.com.scm_anagata.FCM;

import static java.lang.Integer.valueOf;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Objects;
import java.util.Random;

import dwiyan.com.scm_anagata.Base.GlobalVar;
import dwiyan.com.scm_anagata.MainActivity;
import dwiyan.com.scm_anagata.R;

public class MyServiceFCM extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";
    private String chanelId = "chanel_Id";
    private String id = "1";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        id = remoteMessage.getMessageId();
        // Check if message contains a notification payload.
        if(remoteMessage.getData().get("to").equals(GlobalVar.ID)){
            showNotification(
                    remoteMessage.getData().get("title"),
                    remoteMessage.getData().get("body"),
                    remoteMessage.getData().get("type"));
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getData().get("body"));
        }
    }
    // Method to get the custom Design for the display of
    // notification.
    private RemoteViews getCustomDesign(String title,
                                        String message,String Type) {
        RemoteViews remoteViews = new RemoteViews(
                getApplicationContext().getPackageName(),
                R.layout.notification);
        remoteViews.setTextViewText(R.id.title, title);
        remoteViews.setTextViewText(R.id.message, message);
        switch (Type) {
            case "CHT":
                remoteViews.setImageViewResource(R.id.icon,
                        R.drawable.chat_new);
                break;
            case "INF":
                remoteViews.setImageViewResource(R.id.icon,
                        R.drawable.info);
                break;
            case "CNF":
                remoteViews.setImageViewResource(R.id.icon,
                        R.drawable.confirm);
                break;
            case "PYM":
                remoteViews.setImageViewResource(R.id.icon,
                        R.drawable.payment);
                break;
            default:
                remoteViews.setImageViewResource(R.id.icon,
                        R.drawable.notification);
                break;
        }
//        remoteViews.setImageViewResource(R.id.icon,
//                R.drawable.notification);

        return remoteViews;
    }

    // Method to display the notifications
    @SuppressLint({"ObsoleteSdkInt", "UnspecifiedImmutableFlag"})
    public void showNotification(String title,
                                 String message, String Type) {
        // Pass the intent to switch to the MainActivity
        Intent intent
                = new Intent(this, MainActivity.class);
        // Assign channel ID
        String channel_id = "notification_channel";
        // Here FLAG_ACTIVITY_CLEAR_TOP flag is set to clear
        // the activities present in the activity stack,
        // on the top of the Activity that is to be launched
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // Pass the intent to PendingIntent to start the
        // next Activity
        PendingIntent pendingIntent
                = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_IMMUTABLE);
        } else {
            pendingIntent = PendingIntent.getActivity(
                    this, 0, intent,
                    PendingIntent.FLAG_ONE_SHOT);
        }

        // Create a Builder object using NotificationCompat
        // class. This will allow control over all the flags
        NotificationCompat.Builder builder
                = new NotificationCompat
                .Builder(getApplicationContext(),
                channel_id+id)
                .setSmallIcon(R.drawable.notification)
                .setAutoCancel(true)
                .setVibrate(new long[]{1000, 1000, 1000,
                        1000, 1000})
                .setOnlyAlertOnce(true)
                .setContentIntent(pendingIntent);

        // A customized design for the notification can be
        // set only for Android versions 4.1 and above. Thus
        // condition for the same is checked here.
        if (Build.VERSION.SDK_INT
                >= Build.VERSION_CODES.JELLY_BEAN) {

            builder = builder.setContent(
                    getCustomDesign(title, message,Type));
        } // If Android Version is lower than Jelly Beans,
        // customized layout cannot be used and thus the
        // layout is set as follows
        else {
            switch (Type) {
                case "CHT":
                    builder = builder.setContentTitle(title)
                            .setContentText(message)
                            .setSmallIcon(R.drawable.chat_new);
                    break;
                case "INF":
                    builder = builder.setContentTitle(title)
                            .setContentText(message)
                            .setSmallIcon(R.drawable.info);
                    break;
                case "CNF":
                    builder = builder.setContentTitle(title)
                            .setContentText(message)
                            .setSmallIcon(R.drawable.confirm);
                    break;
                case "PYM":
                    builder = builder.setContentTitle(title)
                            .setContentText(message)
                            .setSmallIcon(R.drawable.payment);
                    break;
                default:
                    builder = builder.setContentTitle(title)
                            .setContentText(message)
                            .setSmallIcon(R.drawable.notification);
                    break;
            }

        }
        // Create an object of NotificationManager class to
        // notify the
        // user of events that happen in the background.
        NotificationManager notificationManager
                = (NotificationManager) getSystemService(
                Context.NOTIFICATION_SERVICE);
        // Check if the Android Version is greater than Oreo
        if (Build.VERSION.SDK_INT
                >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel
                    = new NotificationChannel(
                    channel_id+id, "web_app"+id,
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(
                    notificationChannel);
        }
        final int min = 20;
        final int max = 80;
        final int random = new Random().nextInt((max - min) + 1) + min;
        notificationManager.notify(random, builder.build());
    }
}
