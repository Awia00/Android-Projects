package dk.anderswind.thetravelapp;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class StationsListView extends ListActivity {

    TravelDAO dbAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbAdapter = new TravelDAO(this);
        dbAdapter.open();
        Cursor stations = dbAdapter.getStations();
        startManagingCursor(stations);
        CursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, stations, new String[]{dbAdapter.STATIONS_TITLE}, new int[]{android.R.id.text1});
        setListAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        dbAdapter.close();
        super.onDestroy();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Cursor cursor = (Cursor) l.getItemAtPosition(position);
        startManagingCursor(cursor);
        Intent intent = new Intent().putExtra(
                IntentKeys.SELECTED_STATION_NAME,
                cursor.getString(cursor.getColumnIndexOrThrow(dbAdapter.STATIONS_TITLE)));

        setResult(RESULT_OK, intent);
        finish();
    }
}
