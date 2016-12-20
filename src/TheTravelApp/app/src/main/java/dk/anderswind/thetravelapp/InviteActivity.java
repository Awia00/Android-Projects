package dk.anderswind.thetravelapp;

import android.app.ListActivity;
import android.content.CursorLoader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class InviteActivity extends ListActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);
        if (!PermissionRequester.mayRequestContacts(this)) {
            return;
        }
        if (!PermissionRequester.maySendSms(this)) {
            return;
        }
        Uri contentUri = ContactsContract.Contacts.CONTENT_URI;
        CursorLoader contacts = new CursorLoader(this, contentUri, null, null, null, null);
        CursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, contacts.loadInBackground(), new String[]{ContactsContract.Contacts.DISPLAY_NAME}, new int[]{android.R.id.text1});
        setListAdapter(adapter);
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Cursor cursor = (Cursor) l.getItemAtPosition(position);
        startManagingCursor(cursor);
        int hasPhone = cursor.getInt(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.HAS_PHONE_NUMBER));
        if(hasPhone >= 1)
        {
            String userId = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
            Cursor user = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + userId, null, null);
            user.moveToFirst();
            String phoneNumber = user.getString(user.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));

            SmsManager smsManager = SmsManager.getDefault();
            try{
                smsManager.sendTextMessage(phoneNumber, null, getResources().getString(R.string.invite_sms_text), null, null);
                Toast.makeText(getApplicationContext(), "Your sms has successfully sent!",
                        Toast.LENGTH_LONG).show();
            }catch (Exception e)
            {
                Toast.makeText(getApplicationContext(), "Could not send sms",
                        Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "This contact does not have a phone number",
                    Toast.LENGTH_LONG).show();
        }

        finish();
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == PermissionRequester.REQUEST_READ_CONTACTS || requestCode == PermissionRequester.REQUEST_SEND_SMS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                finish();
                startActivity(getIntent());
            }
        }
    }
}
