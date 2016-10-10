package nl.achan.apps.sbb_spelgids;

import android.app.Application;
import android.util.Log;

import nl.achan.apps.sbb_spelgids.location.LocationsRepository;
import nl.achan.apps.sbb_spelgids.database.OpenHelper;

/**
 * Created by fhict on 10/10/16.
 */
public class D4NW2 extends Application {

    private OpenHelper database;
    private LocationsRepository locations;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("D4NW2", "Initialized custom application class.");
        database = OpenHelper.getInstance(this);
        locations = new LocationsRepository(this);
    }

    public OpenHelper getDatabase(){
        return OpenHelper.getInstance(this);
    }

    public LocationsRepository getLocations() {
        return locations;
    }
}
