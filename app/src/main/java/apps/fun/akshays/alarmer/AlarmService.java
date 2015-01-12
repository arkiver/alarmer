package apps.fun.akshays.alarmer;

/**
 * Created by akshay on 1/11/15.
 */

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class AlarmService extends IntentService {
    private NotificationManager alarmNotificationManager;

    public AlarmService(){
        super("AlarmService");
    }

    @Override
    public void onHandleIntent(Intent intent){
        sendNotification("Wake up ! Wake up !");
    }

    private void sendNotification(String msg){
        Log.d("AlarmService", "Preparing to send notification..:" + msg);

        alarmNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        NotificationCompat.Builder alarmNotificationBuilder = new NotificationCompat.Builder(this)
                .setContentTitle("Alarm").setSmallIcon(R.drawable.ic_launcher)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg)).setContentText(msg);

        PendingIntent contentIntent2 = PendingIntent.getActivity(this, 0,
                new Intent(this, DismissAlarm.class), 0);

        alarmNotificationBuilder.setContentIntent(contentIntent)
                .addAction(R.drawable.ic_launcher, "--Dismiss--", contentIntent2).build();

        alarmNotificationManager.notify(1, alarmNotificationBuilder.build());

        Log.d("AlarmService", "Notification Sent");
    }
}
