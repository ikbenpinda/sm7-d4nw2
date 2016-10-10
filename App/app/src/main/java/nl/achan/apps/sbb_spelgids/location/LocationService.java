package nl.achan.apps.sbb_spelgids.location;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Polls for location in the background.
 * todo SmartLocation | fake data?
 * Created by fhict on 10/10/16.
 */
public class LocationService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
