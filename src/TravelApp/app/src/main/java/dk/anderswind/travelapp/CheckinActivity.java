package dk.anderswind.travelapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import dk.anderswind.travelapp.Settings.IntentKeys;

public class CheckinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);
        Button checkinButton = (Button) findViewById(R.id.check_in_button);
        checkinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView checkInText = (TextView) findViewById(R.id.check_in_station);
                String checkInStation = checkInText.getText().toString();
                Intent intent = new Intent(CheckinActivity.this, CheckoutActivity.class);
                intent.putExtra(IntentKeys.CHECKIN_STATION, checkInStation);
                startActivity(intent);
            }
        });
    }
}
