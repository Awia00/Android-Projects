package dk.anderswind.thetravelapp;

import android.app.ListActivity;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class TravelsListView extends ListActivity {

    TravelDAO dbAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbAdapter = new TravelDAO(this);
        dbAdapter.open();
        Cursor travels = dbAdapter.getTravels();
        startManagingCursor(travels);
        CursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.historyitem, travels, new String[]{dbAdapter.TRAVELS_START_TITLE, dbAdapter.TRAVELS_DESTINATION_TITLE}, new int[]{R.id.start, R.id.destination});
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
    }
}
