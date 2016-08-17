package dk.anderswind.travelapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import dk.anderswind.travelapp.Settings.IntentKeys;

public class CheckoutActivity extends AppCompatActivity {

    private String checkInStation = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);


        checkInStation =
                getIntent().getStringExtra(IntentKeys.CHECKIN_STATION);

        Button checkoutButton = (Button) findViewById(R.id.check_out_button);
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView checkOutText = (TextView) findViewById(R.id.check_out_station);
                String checkOutStation = checkOutText.getText().toString();

                Intent intent = new Intent(CheckoutActivity.this, ReceiptActivity.class);
                intent.putExtra(IntentKeys.CHECKIN_STATION, checkInStation);
                intent.putExtra(IntentKeys.CHECKOUT_STATION, checkOutStation);
                startActivity(intent);
            }
        });

    }
}
