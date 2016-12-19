package dk.anderswind.thetravelapp;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.CursorLoader;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

public class InviteActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);
        ContentResolver contentResolver = getContentResolver();
        Uri contentUri = ContactsContract.Contacts.CONTENT_URI;
        CursorLoader contacts = new CursorLoader(this, contentUri, null, null, null, null);
        CursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, contacts.loadInBackground(), new String[]{ContactsContract.Contacts.DISPLAY_NAME}, new int[]{android.R.id.text1});
    }
}
