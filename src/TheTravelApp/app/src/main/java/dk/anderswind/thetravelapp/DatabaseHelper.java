package dk.anderswind.thetravelapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ander on 20-08-2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "travel", null, 1);
    }

    public final String TRAVELS = "travels";
    public final String STATIONS = "stations";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE "+TRAVELS+" (_id integer primary key autoincrement, start text,\n" +
                "destination text);\n" +
                "CREATE TABLE "+STATIONS+" (_id integer primary key autoincrement, station text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
