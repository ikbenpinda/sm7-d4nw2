package nl.achan.apps.sbb_spelgids.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import nl.achan.apps.sbb_spelgids.models.Level;
import nl.achan.apps.sbb_spelgids.models.Team;
import nl.qbusict.cupboard.DatabaseCompartment;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * SQLiteHelper class using Cupboard for easy persistence.
 */
public class OpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "D4NW2.database";
    public static final int DATABASE_VERSION = 3;

    private static OpenHelper instance;

    static {
        cupboard().register(Team.class);
        cupboard().register(Level.class);
    }

    public static OpenHelper getInstance(Context context){
        if(instance == null)
            instance = new OpenHelper(context);
        return instance;
    }

    /**
     * Used for easy querying of the database.
     * Equal to cupboard().withDatabase(db).
     *
     * Reduces boilerplate.
     *
     * @return
     */
    public DatabaseCompartment call(){
        return cupboard().withDatabase(getWritableDatabase());
    }

    /**
     * Default constructor supplying its own database name and version number.
     * @param context
     */
    private OpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Custom fallback constructor for cases where OpenHelper(context) doesn't work.
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    private OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * Custom fallback constructor for cases where OpenHelper(context) doesn't work.
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    private OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        cupboard().withDatabase(db).createTables();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        cupboard().withDatabase(db).upgradeTables();
    }
}
