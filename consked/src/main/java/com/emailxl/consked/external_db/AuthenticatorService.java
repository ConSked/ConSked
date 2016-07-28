package com.emailxl.consked.external_db;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * @author ECG
 */

public class AuthenticatorService extends Service {
    private static final String TAG = "AuthenticatorService";
    private static final boolean LOG = true;

    // Instance field that stores the authenticator object
    private Authenticator mAuthenticator;

    @Override
    public void onCreate() {
        if (LOG) Log.i(TAG, "onCreate called");
        mAuthenticator = new Authenticator(this);
    }

    @Override
    public void onDestroy() {
        if (LOG) Log.i(TAG, "onDestroy called");
    }

    /*
     * When the system binds to this Service to make the RPC call
     * return the authenticator's IBinder
     */
    @Override
    public IBinder onBind(Intent intent) {
        if (LOG) Log.i(TAG, "onBind called");
        return mAuthenticator.getIBinder();
    }
}
