package dk.anderswind.thetravelapp;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class CitiesListView extends ListActivity {

    TravelDAO dbAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbAdapter = new TravelDAO(this);
        dbAdapter.open();
        Cursor stations = dbAdapter.getStations();
        startManagingCursor(stations);
        CursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, stations, new String[]{"station"}, new int[]{android.R.id.text1});
        setListAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbAdapter.close();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Cursor cursor = (Cursor) l.getItemAtPosition(position);
        startManagingCursor(cursor);
        Intent intent = new Intent().putExtra(
                IntentKeys.SELECTED_STATION_NAME,
                cursor.getString(cursor.getColumnIndexOrThrow("station")));

        setResult(RESULT_OK, intent);
        finish();
    }
}
