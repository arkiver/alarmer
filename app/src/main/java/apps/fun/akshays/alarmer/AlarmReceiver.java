package apps.fun.akshays.alarmer;

/**
 * Created by akshay on 1/11/15.
 */

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.content.WakefulBroadcastReceiver;

public class AlarmReceiver extends WakefulBroadcastReceiver {
    static Ringtone ringtone;

    public void onReceive(final Context context, Intent intent){
        // Wil update the UI with message
        MainActivity inst = MainActivity.instance();
        inst.setAlarmText("Time to wake up!");

        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        if(alarmUri == null){
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }

        ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();

        // will send a notification message

        ComponentName comp = new ComponentName(context.getPackageName(),
                AlarmService.class.getName());
        startWakefulService(context, intent.setComponent(comp));

        setResultCode(Activity.RESULT_OK);
    }
    public void stopPlayingRingtone(){
        ringtone.stop();
    }
}
