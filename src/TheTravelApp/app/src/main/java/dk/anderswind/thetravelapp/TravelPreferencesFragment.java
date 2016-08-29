package dk.anderswind.thetravelapp;


import android.os.Bundle;
import android.app.Fragment;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class TravelPreferencesFragment extends PreferenceFragment {

    private TravelDAO dbAdapter;

    public TravelPreferencesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the layout for this fragment
        addPreferencesFromResource(R.xml.preferences);
        dbAdapter = new TravelDAO(this.getContext());
        dbAdapter.open();

        Preference clearHistory = findPreference("clearHistoryPref");
        clearHistory.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                dbAdapter.clearTravels();
                return true;
            }
        });

        Preference clearStations = findPreference("clearStationsPref");
        clearStations.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                dbAdapter.clearStations();
                return true;
            }
        });
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        return inflater.inflate(R.layout.fragment_travel_preferences, container, false);
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dbAdapter.close();
    }
}
