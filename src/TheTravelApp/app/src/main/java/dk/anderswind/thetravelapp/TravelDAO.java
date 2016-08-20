package dk.anderswind.thetravelapp;

import android.database.Cursor;

/**
 * Created by ander on 20-08-2016.
 */
public class TravelDAO {
    private DatabaseHelper dbHelper;

    public TravelDAO(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void open()
    {

    }
    public void close()
    {

    }

    Cursor getTravels()
    {
        return null;
    }

    void saveTravels(String start, String destination)
    {

    }

    Cursor getStations()
    {
        return null;
    }

    void saveStation(String station)
    {

    }
}
