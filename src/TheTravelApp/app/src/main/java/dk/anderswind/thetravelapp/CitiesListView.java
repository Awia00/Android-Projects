package dk.anderswind.thetravelapp;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CitiesListView extends ListActivity {

    private ArrayList<String> stations;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stations = new ArrayList();
        stations.add("Copenhagen");
        stations.add("Aarhus");
        stations.add("Southpark");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stations);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String value = stations.get((int) id);
        Intent intent = new Intent().putExtra(IntentKeys.SELECTED_STATION_NAME, value);
        setResult(RESULT_OK, intent);
        finish();
    }
}
