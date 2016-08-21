package dk.anderswind.thetravelapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ander on 20-08-2016.
 */
public class TravelDAO {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase  db;

    public TravelDAO(Context context) {
        this.dbHelper = new DatabaseHelper(context);
    }

    public void open()
    {
        db = dbHelper.getWritableDatabase();
    }
    public void close()
    {
        db.close();
        dbHelper.close();
    }

    Cursor getTravels()
    {
        return db.query(dbHelper.TRAVELS, new String[]{"_id", "start", "destination"}, null, null, null, null, "_id");
    }

    void saveTravels(String start, String destination)
    {
        ContentValues insertValues = new ContentValues();
        insertValues.put("start", start);
        insertValues.put("destination", destination);
        db.insert(dbHelper.TRAVELS, null, insertValues);
    }

    Cursor getStations()
    {
        return db.query(dbHelper.STATIONS, new String[]{"station"}, null, null, null, null, "_id");
    }

    void saveStation(String station)
    {
        //db.execSQL("INSERT OR IGNORE INTO "+ dbHelper.STATIONS +"(station) VALUES("+station+")");
    }
}
