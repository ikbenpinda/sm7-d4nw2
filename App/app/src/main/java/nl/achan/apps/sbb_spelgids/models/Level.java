package nl.achan.apps.sbb_spelgids.models;

import android.location.Location;

/**
 * Created by fhict on 10/10/16.
 */
public class Level {

    /**
     * As needed by Cupboard for the persistence layer.
     */
    public long _id;

    /**
     * Physical (GPS-)location of the level.
     */
    public Location location;

    /**
     * Custom name, optional.
     */
    public String name = "naamloze locatie";

    public Level(Location location, String name) {
        this.location = location;
        this.name = name;
    }
}
