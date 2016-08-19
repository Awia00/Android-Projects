package dk.anderswind.thetravelapp;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

    private String startDestination;
    private String endDestination;
    private boolean isCheckingIn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkInButton = (Button) findViewById(R.id.checkInButton);
        checkOutButton = (Button) findViewById(R.id.checkOutButton);
        checkInStation = (TextView) findViewById(R.id.checkInStation);
        checkOutStation = (TextView) findViewById(R.id.checkOutStation);

        checkInButton.setOnClickListener(new CheckInButton());
        checkOutButton.setOnClickListener(new CheckOutButton());
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
        MenuItem item = menu.add(Menu.NONE, Menu.FIRST, Menu.NONE, "Receipt");
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == Menu.FIRST)
        {
            informUser(startDestination + " to " + endDestination);
        }
        return super.onOptionsItemSelected(item);
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


    class CheckInButton implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {
            if(!TextUtils.isEmpty(checkInStation.getText()))
            {
                changeToCheckIn(false);
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
            if(!TextUtils.isEmpty(checkOutStation.getText()))
            {
                changeToCheckIn(true);

                startDestination = checkInStation.getText().toString();
                endDestination = checkOutStation.getText().toString();

                checkInStation.setText("");
                checkOutStation.setText("");
                informUser("Travel finished");
            }else
            {
                checkOutStation.setError("Please enter a station");
            }
        }
    }
}



