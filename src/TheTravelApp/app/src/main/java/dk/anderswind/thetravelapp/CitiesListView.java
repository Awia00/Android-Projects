package dk.anderswind.thetravelapp;

import android.app.ListActivity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CitiesListView extends ListActivity {

    private ArrayList<String> stations;
    public CitiesListView() {
        stations = new ArrayList();
        stations.add("Copenhagen");
        stations.add("Aarhus");
        stations.add("Southpark");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stations);
        setListAdapter(adapter);
    }
}
