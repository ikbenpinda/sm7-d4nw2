package nl.achan.apps.sbb_spelgids;

import android.app.Application;
import android.location.Location;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;
import io.nlopez.smartlocation.location.config.LocationParams;
import nl.achan.apps.sbb_spelgids.database.OpenHelper;
import nl.achan.apps.sbb_spelgids.location.LocationsRepository;
import nl.achan.apps.sbb_spelgids.media.Notifier;
import nl.achan.apps.sbb_spelgids.models.Level;

/**
 * Created by fhict on 10/10/16.
 */
public class D4NW2 extends Application {

    // temporary distance logging to fix GPS inconsistencies.
    public List<Double> distances = new ArrayList<>();

    private OpenHelper database;
    private LocationsRepository locations;
    private Notifier notifier;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("D4NW2", "Initialized custom Application class.\nThis is a sanity check.");
        database = OpenHelper.getInstance(this);
        notifier = Notifier.getInstance(this);
        locations = new LocationsRepository(this);
        SmartLocation.with(this)
                .location()
                .continuous()
                .config(LocationParams.NAVIGATION)
                .start(new OnLocationUpdatedListener() {

                    // Range constants in meters for easy on-the-go redeploying.
                    // [Indoor, WiFi+GPS, Samsung Galaxy S4] deviation = 15 meters max
                    // todo [Indoor, GPS only, Samsung Galaxy S4] deviation = ?? meters max
                    // todo [Outdoor, GPS only, Samsung Galaxy S4] deviation = ?? meters max
                    final int RANGE_ONE_METER = 1;
                    final int RANGE_THREE_METERS = 3;
                    final int RANGE_FIVE_METERS = 5;
                    final int RANGE_TEN_METERS = 10;
                    final int RANGE_FIFTEEN_METERS = 15;
                    final int RANGE_TWENTY_METERS = 20;
                    final int RANGE_THIRTY_METERS = 30;
                    final int RANGE_FIFTY_METERS = 50;

                    /**
                     * Whether or not the user is within a certain distance of the level.
                     */
                    boolean userIsNotified = false;

                    /**
                     * Whether the user just arrived or is still there.
                     * Keeps the device from notifying continuously.
                     */
                    boolean areWeThereYet = false;

                    @Override
                    public void onLocationUpdated(Location location) {

                        // Look up locations of the levels
                        // to see if we're in range with any of them.
                        for (Level level : locations.getAllLevels()) {

                            if (inRange(location, level.location, RANGE_TWENTY_METERS)) {
                                areWeThereYet = true;
                                Log.i("Locations:", "In range with level " + level);
                                break;
                            }
                            else {
                                areWeThereYet = false;
                                Log.i("Locations:", "Out of range for level " + level);
                                userIsNotified = false;
                            }
                        }

                        if (areWeThereYet && !userIsNotified)
                            notifyAndReset();

                        // todo - set current level in cache and update UI!
                        // fixme - compensate for default deviation of GPS.

                        Log.i("Locations:", location.toString());
                    }

                    /**
                     * Calculate distance between location a and b,
                     * and if it's in range.
                     * @param range distance in meters.
                     */
                    public boolean inRange(Location a, Location b, int range){
                        return measure(a, b) < range;
                    }

                    /**
                     * Notifies the user of arrival at a level.
                     */
                    public void notifyAndReset(){
                        Log.i("Locations:", "Notifying user.");
                        notifier.NotifyLocationReached();
                        userIsNotified = true;
                    }

                    /**
                     * Measures difference between gps coordinates according to
                     * http://stackoverflow.com/a/11172685.
                     *
                     * I'm not a math nerd, i don't know how this works.
                     *
                     * returns distance in meters.
                     */
                    public double measure(Location a, Location b){

                        //<editor-fold desc="[shit i'm too stupid for to understand]">
                        double radius = 6378.137; // Radius of earth in KM
                        double dLat = b.getLatitude() * Math.PI / 180 - a.getLatitude() * Math.PI / 180;
                        double dLon = b.getLongitude() * Math.PI / 180 - a.getLongitude() * Math.PI / 180;
                        double _a =
                                Math.sin(dLat/2) * Math.sin(dLat/2) +
                                Math.cos(a.getLatitude() * Math.PI / 180)
                                        * Math.cos(b.getLatitude() * Math.PI / 180)
                                        * Math.sin(dLon/2)
                                        * Math.sin(dLon/2);
                        double c = 2 * Math.atan2(Math.sqrt(_a), Math.sqrt(1-_a));
                        double distance = (radius * c) * 1000; // meters
                        //</editor-fold>

                        Log.i("Locations:", "Distance: " + distance);
                        distances.add(distance);
                        return distance;
                    }
                });
    }

    public OpenHelper getDatabase(){
        return OpenHelper.getInstance(this);
    }

    public LocationsRepository getLocations() {
        return locations;
    }
}
