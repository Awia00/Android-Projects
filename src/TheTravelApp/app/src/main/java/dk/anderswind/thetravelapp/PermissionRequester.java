package dk.anderswind.thetravelapp;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.SEND_SMS;

/**
 * Created by ander on 19-12-2016.
 */

public abstract class PermissionRequester {

    public static final int REQUEST_READ_CONTACTS = 0;
    public static final int REQUEST_SEND_SMS = 1;

    public static boolean mayRequestContacts(Activity context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (context.checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            context.requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    public static boolean maySendSms(Activity context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (context.checkSelfPermission(SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            context.requestPermissions(new String[]{SEND_SMS}, REQUEST_SEND_SMS);
        }
        return false;
    }
}
