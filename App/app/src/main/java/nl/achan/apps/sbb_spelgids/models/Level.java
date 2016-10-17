package nl.achan.apps.sbb_spelgids.models;

import android.location.Location;

/**
 * Created by fhict on 10/10/16.
 */
public class Level {

    /**
     * As needed for the persistence layer.
     */
    public long _id;

    /**
     * Physical (GPS-)location of the level.
     */
    public Location location;

    /**
     * Identifying code of the level.
     * Not the same as Level._id,
     * which is used for the database
     * and may not be sequentially ordered.
     */
    public String identifier;

    /**
     * Custom human-readable name of the stage, optional.
     */
    public String name = "naamloze locatie";

    public Level(Location location, String name) {
        this.location = location;
        this.name = name;
    }

    /**
     * Custom human-readable name of the stage, optional.
     */
    public Level(Location location) {
        this.location = location;
    }
}
