package nl.achan.apps.sbb_spelgids.media;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import nl.achan.apps.sbb_spelgids.R;

/**
 * Handles notifications and sounds/vibrations for notifying.
 *
 * Created by fhict on 17/10/16.
 */
public class Notifier {

    private static final boolean CUSTOM_SOUNDS_ENABLED = true;
    public static final int NOTIFICATION_ID_LOCATION_REACHED = 69;

    private static Notifier instance;

    private Context context;
    private MediaPlayer player;

    private Notifier(Context context){
        this.context = context;
        player = MediaPlayer.create(context, R.raw.spooky);
    }

    public static Notifier getInstance(Context context){
        return new Notifier(context);
    }

    public void vibrate(){
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        Log.i("SettingsActivity", "S-senpai! That t-t-tickl~ Fuwaaaaaa~ <3");
        v.vibrate(500);
    }

    public void playSound() {
        if (CUSTOM_SOUNDS_ENABLED) {
            // Play app-defined sound.
            //player = MediaPlayer.create(context, R.raw.spooky);
            Log.i("SettingsActivity", "Playing sound effect.");
            player.start();
        } else {
            // Play user-defined ringtone
            Log.i("SettingsActivity", "Playing default ringtone.");
            Uri ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone ringtoneSound = RingtoneManager.getRingtone(context.getApplicationContext(), ringtoneUri);
            if (ringtoneSound != null)
                ringtoneSound.play();
        }
    }

    public void sendNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                        //.setSmallIcon(R.drawable.notification_icon)
                        .setContentTitle("Level in de buurt!")
                        .setContentText("level 1");
        Notification notification = builder.build();

        // Gets an instance of the NotificationManager service
        NotificationManager manager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Builds the notification and issues it.
        manager.notify(NOTIFICATION_ID_LOCATION_REACHED, notification);
    }

    public void NotifyLocationReached(){
        vibrate();
        playSound();
        sendNotification();
    }
}
