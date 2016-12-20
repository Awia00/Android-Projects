package dk.anderswind.thetravelapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {
    public SmsReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "HASDFCASDG", Toast.LENGTH_SHORT).show();



        Bundle extras = intent.getExtras();
        if (extras != null) {
            Object[] pdus = (Object[]) extras.get("pdus");
            SmsMessage message = SmsMessage.createFromPdu((byte[]) pdus[0]);
            String sender = message.getOriginatingAddress();
            String origMessage = message.getMessageBody();
            if(message.getDisplayMessageBody().equals(context.getResources().getString(R.string.invitation_notation_sms_text)))
            {
                abortBroadcast();

            }
            Toast.makeText(context, message.getDisplayMessageBody(), Toast.LENGTH_SHORT).show();

        }
    }
}
