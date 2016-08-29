package dk.anderswind.thetravelapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ander on 20-08-2016.
 */
public class TravelDAO {
    private SQLiteDatabase  db;
    private Context context;
    private DatabaseHelper dbHelper;

    public final String TRAVELS_TABLE = "travels";
    public final String TRAVELS_ID = "_id";
    public final String TRAVELS_START_TITLE = "start";
    public final String TRAVELS_DESTINATION_TITLE = "destination";
    public final String STATIONS_TABLE = "stations";
    public final String STATIONS_ID = "_id";
    public final String STATIONS_TITLE = "station";
    public final String CREATE_TRAVELS =
            "CREATE TABLE " + TRAVELS_TABLE + " (" + TRAVELS_ID + " integer primary key autoincrement, " + TRAVELS_START_TITLE + " text, " + TRAVELS_DESTINATION_TITLE + " text);";
    public final String CREATE_STATIONS = "CREATE TABLE " + STATIONS_TABLE + " (" + STATIONS_ID + " integer primary key autoincrement, " + STATIONS_TITLE + " text);";


    SharedPreferences preferences;
    public TravelDAO(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(context.getPackageName() + "_preferences", Context.MODE_PRIVATE);
    }

    public void open()
    {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public void close()
    {
        dbHelper.close();
    }

    Cursor getTravels()
    {
        String limit = preferences.getString("historySizePref", "10");
        return db.query(TRAVELS_TABLE, new String[]{TRAVELS_ID, TRAVELS_START_TITLE, TRAVELS_DESTINATION_TITLE}, null, null, null, null, TRAVELS_ID + " DESC", limit);
    }

    void saveTravels(String start, String destination)
    {
        ContentValues insertValues = new ContentValues();
        insertValues.put(TRAVELS_START_TITLE, start);
        insertValues.put(TRAVELS_DESTINATION_TITLE, destination);
        db.insert(TRAVELS_TABLE, null, insertValues);
    }

    void clearTravels()
    {
        db.delete(TRAVELS_TABLE, null, null);
    }

    Cursor getStations()
    {
        return db.query(STATIONS_TABLE, new String[]{STATIONS_ID, STATIONS_TITLE}, null, null, null, null, STATIONS_ID);
    }

    void saveStation(String station)
    {
        if(preferences.getBoolean("autoSavePref", true))
        {
            Cursor c = db.query(STATIONS_TABLE, new String[]{STATIONS_ID, STATIONS_TITLE}, STATIONS_TITLE + " = '" + station + "'", null, null, null, STATIONS_ID);
            if (c.getCount() == 0)
            {
                ContentValues insertValues = new ContentValues();
                insertValues.put(STATIONS_TITLE, station);
                db.insert(STATIONS_TABLE, null, insertValues);
            }
            c.close();
        }
    }

    void clearStations()
    {
        db.delete(STATIONS_TABLE, null, null);
    }


    private class DatabaseHelper extends SQLiteOpenHelper {
        public static final String DATABASE_NAME = "traveldb";
        public static final int DATABASE_VERSION = 5;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_TRAVELS);
            sqLiteDatabase.execSQL(CREATE_STATIONS);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + STATIONS_TABLE);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TRAVELS_TABLE);
            onCreate(sqLiteDatabase);
        }
    }

}
