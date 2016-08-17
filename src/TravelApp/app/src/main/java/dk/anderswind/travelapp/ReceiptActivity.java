package dk.anderswind.travelapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import dk.anderswind.travelapp.Settings.IntentKeys;

public class ReceiptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        String checkInStation =
                getIntent().getStringExtra(IntentKeys.CHECKIN_STATION);

        String checkOutStation =
                getIntent().getStringExtra(IntentKeys.CHECKOUT_STATION);

        TextView receiptTextView = (TextView) findViewById(R.id.receipt);
        receiptTextView.setText(checkInStation + " to " + checkOutStation);

        Button backButton = (Button) findViewById(R.id.back_to_start_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReceiptActivity.this, CheckinActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}
