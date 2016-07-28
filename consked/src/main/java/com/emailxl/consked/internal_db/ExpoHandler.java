package com.emailxl.consked.internal_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.emailxl.consked.utils.AppConstants;

/**
 * Class for handling the expo table
 * <p/>
 * This class is a wrapper for the expo table part of the Content Provider
 *
 * @author ECG
 */

public class ExpoHandler {

    private Context context;
    private Uri uri;

    /**
     * Method to initialize the expo handler
     *
     * @param context The caller's context.
     */
    public ExpoHandler(Context context) {
        this.context = context;
        this.uri = new Uri.Builder()
                .scheme(AppConstants.SCHEME)
                .authority(AppConstants.AUTHORITY)
                .path(ConSkedProvider.EXPO_TABLE)
                .build();
    }

    /**
     * Method to add an expo to the expo table
     *
     * @param expo The expo object.
     * @return The URI for the newly inserted item.
     */
    public long addExpo(ExpoInt expo) {

        ContentValues values = new ContentValues();

        values.put(ConSkedProvider.IDEXT, expo.getIdExt());
        values.put(ConSkedProvider.JSON, expo.getJson());

        Uri newuri = context.getContentResolver().insert(uri, values);

        long lastPathSegment = 0;
        if (newuri != null) {
            lastPathSegment = Long.parseLong(newuri.getLastPathSegment());
        }

        return lastPathSegment;
    }

    /**
     * Method to retrieve an expo with a specific external id
     *
     * @param idExt The id of the event to be retrieved.
     * @return The expo json for the specified id.
     */
    public ExpoInt getExpoIdExt(int idExt) {

        String selection = ConSkedProvider.IDEXT + " = ?";
        String[] selectionArgs = {Integer.toString(idExt)};

        Cursor cursor = context.getContentResolver().query(uri, null, selection, selectionArgs, null);

        ExpoInt expo = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {

                expo = new ExpoInt(
                        cursor.getInt(0),       // idInt
                        cursor.getInt(1),       // idExt
                        cursor.getString(2));   // json
            }

            cursor.close();
        }
        return expo;
    }

    /**
     * Method for deleting all expos
     *
     * @return The number of rows deleted.
     */
    public int deleteExpoAll() {

        return context.getContentResolver().delete(uri, null, null);
    }
}
