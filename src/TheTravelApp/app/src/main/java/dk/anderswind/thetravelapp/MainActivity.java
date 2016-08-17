package dk.anderswind.thetravelapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView checkInStation;
    private TextView checkOutStation;
    private Button checkInButton;
    private Button checkOutButton;

    private String startDestination;
    private String endDestination;

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

    private void informUser(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    class CheckInButton implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {
            if(!TextUtils.isEmpty(checkInStation.getText()))
            {
                checkOutButton.setEnabled(true);
                checkOutStation.setEnabled(true);
                checkInButton.setEnabled(false);
                checkInStation.setEnabled(false);
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
                checkInButton.setEnabled(true);
                checkInStation.setEnabled(true);
                checkOutButton.setEnabled(false);
                checkOutStation.setEnabled(false);

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



