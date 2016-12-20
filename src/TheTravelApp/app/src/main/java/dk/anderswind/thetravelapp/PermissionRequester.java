package dk.anderswind.thetravelapp;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.READ_SMS;
import static android.Manifest.permission.RECEIVE_SMS;
import static android.Manifest.permission.SEND_SMS;

/**
 * Created by ander on 19-12-2016.
 */

public abstract class PermissionRequester {

    public static final int
            REQUEST_READ_CONTACTS = 0,
            REQUEST_SEND_SMS = 1,
            REQUEST_RECEIVE_SMS = 2,
            REQUEST_READ_SMS = 3;


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

    public static boolean mayReceiveSms(Activity context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (context.checkSelfPermission(RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            context.requestPermissions(new String[]{RECEIVE_SMS}, REQUEST_RECEIVE_SMS);
        }
        return false;
    }

    public static boolean mayReadSms(Activity context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (context.checkSelfPermission(READ_SMS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            context.requestPermissions(new String[]{READ_SMS}, REQUEST_READ_SMS);
        }
        return false;
    }
}
