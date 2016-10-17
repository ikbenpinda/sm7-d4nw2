package nl.achan.apps.sbb_spelgids.location;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import nl.achan.apps.sbb_spelgids.models.Level;

/**
 * Repository for storing and retrieving GPS levels for the levels.
 * FIXME currently made to store everything in-memory because of some weird recursion shit.
 * Created by fhict on 10/10/16.
 */
public class LocationsRepository {

    Context context;
//    OpenHelper database; // FIXME architectural choices.
    List<Level> cache;

    public LocationsRepository(Context context) {
        this.context = context;
//        database = ((D4NW2) context.getApplicationContext()).getDatabase();
        cache = new ArrayList<>();
    }

    /**
     * Returns a list of all levels.
     * @return
     */
    public List<Level> getAllLevels(){
//        List<Level> levels = new ArrayList<>();
//        for (Level level : database.call().query(Level.class).query()) {
//            levels.add(level);
//        }
//        return levels;
        return cache;
    }

    /**
     * Inserts/updates the given model.
     * @param level
     * @return
     */
    public Level register(Level level){
        cache.add(level);
        //database.call().put(level);
        Log.i("levelsRepository", "Added model " + level.toString());
        return level;
    }

    public boolean delete(Level level){
        boolean deleted = cache.remove(level);
//        boolean deleted = database.call().delete(level);
        if (deleted)
            Log.i("levelsRepository", "Deleted model " + level.toString());
        else
            Log.w("levelsRepository", "Deletion failed! Check log output for details.");
        return deleted;
    }
}
