package dk.anderswind.thetravelapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String LAST_START = IntentKeys.PACKAGE_NAME + "LastStart";
    private final String LAST_END = IntentKeys.PACKAGE_NAME + "LastEnd";
    private final String IS_CHECKING_IN = IntentKeys.PACKAGE_NAME + "isCheckingIn";

    private TextView checkInStation;
    private TextView checkOutStation;
    private Button checkInButton;
    private Button checkOutButton;
    private Button selectCheckInButton;
    private Button selectCheckOutButton;

    private String startDestination;
    private String endDestination;
    private boolean isCheckingIn = true;

    private TravelDAO dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbAdapter = new TravelDAO(this);

        checkInButton = (Button) findViewById(R.id.checkInButton);
        checkOutButton = (Button) findViewById(R.id.checkOutButton);
        selectCheckInButton = (Button) findViewById(R.id.selectCheckInButton);
        selectCheckOutButton = (Button) findViewById(R.id.selectCheckOutButton);
        ImageView logoImage = (ImageView) findViewById(R.id.imageView);
        checkInStation = (TextView) findViewById(R.id.checkInStation);
        checkOutStation = (TextView) findViewById(R.id.checkOutStation);

        logoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WebInfoActivity.class);
                startActivity(intent);
            }
        });

        checkInButton.setOnClickListener(new CheckInButton());
        checkOutButton.setOnClickListener(new CheckOutButton());
        selectCheckInButton.setOnClickListener(new SelectStationButton(0));
        selectCheckOutButton.setOnClickListener(new SelectStationButton(1));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        startDestination = savedInstanceState.getString(LAST_START);
        endDestination = savedInstanceState.getString(LAST_END);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(LAST_START, startDestination);
        outState.putString(LAST_END, endDestination);
        outState.putBoolean(IS_CHECKING_IN, isCheckingIn);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem history = menu.add(Menu.NONE, Menu.FIRST, Menu.NONE, "History");
        MenuItem settings = menu.add(Menu.NONE, 2, Menu.NONE, "Settings");
        MenuItem invite = menu.add(Menu.NONE, 3, Menu.NONE, "Invite a friend");
        history.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        settings.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        invite.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == Menu.FIRST)
        {
            Intent intent = new Intent(MainActivity.this, TravelsListView.class);
            startActivity(intent);
        }
        else if (item.getItemId() == 2)
        {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        }
        else if (item.getItemId() == 3)
        {
            Intent intent = new Intent(MainActivity.this, InviteActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK)
        {
            String result = data.getStringExtra(IntentKeys.SELECTED_STATION_NAME);
            if(requestCode == 0)
            {
                checkInStation.setText(result);
            }
            else if (requestCode == 1)
            {
                checkOutStation.setText(result);
            }
        }
    }

    // This is here to allow the innerclass to make toasts.
    private void informUser(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void changeToCheckIn(boolean yes)
    {
        isCheckingIn = yes;
        checkInButton.setEnabled(yes);
        checkInStation.setEnabled(yes);
        findViewById(R.id.selectCheckInButton).setEnabled(yes);
        checkOutButton.setEnabled(!yes);
        checkOutStation.setEnabled(!yes);
        findViewById(R.id.selectCheckOutButton).setEnabled(!yes);
    }


    class SelectStationButton implements View.OnClickListener
    {
        int id;
        public SelectStationButton(int id)
        {
            this.id = id;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, StationsListView.class);
            startActivityForResult(intent, id);
        }
    }
    class CheckInButton implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {
            String station = checkInStation.getText().toString();
            if(!TextUtils.isEmpty(station))
            {
                changeToCheckIn(false);
                dbAdapter.open();
                dbAdapter.saveStation(station);
                dbAdapter.close();
            }else
            {
                checkInStation.setError("Please enter a station");
            }
        }
    }

    class CheckOutButton implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {
            String station = checkOutStation.getText().toString();
            if(!TextUtils.isEmpty(station))
            {
                changeToCheckIn(true);



                startDestination = checkInStation.getText().toString();
                endDestination = station;

                checkInStation.setText("");
                checkOutStation.setText("");
                informUser("Travel finished");

                dbAdapter.open();
                dbAdapter.saveStation(station);
                dbAdapter.saveTravels(startDestination, endDestination);
                dbAdapter.close();
            }else
            {
                checkOutStation.setError("Please enter a station");
            }
        }
    }


}



