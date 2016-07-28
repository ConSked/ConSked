package com.emailxl.consked.internal_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.emailxl.consked.utils.AppConstants;

/**
 * Class for handling the station table
 * <p/>
 * This class is a wrapper for the station table part of the Content Provider
 *
 * @author ECG
 */

public class StationHandler {
    private Context context;
    private Uri uri;

    /**
     * Method to initialize the station handler
     *
     * @param context The caller's context.
     */
    public StationHandler(Context context) {
        this.context = context;
        this.uri = new Uri.Builder()
                .scheme(AppConstants.SCHEME)
                .authority(AppConstants.AUTHORITY)
                .path(ConSkedProvider.EXPO_TABLE)
                .build();
    }

    /**
     * Method to add an station to the station table
     *
     * @param station The station object.
     * @return The URI for the newly inserted item.
     */
    public long addStation(StationInt station) {

        ContentValues values = new ContentValues();

        values.put(ConSkedProvider.IDEXT, station.getIdExt());
        values.put(ConSkedProvider.JSON, station.getJson());

        Uri newuri = context.getContentResolver().insert(uri, values);

        long lastPathSegment = 0;
        if (newuri != null) {
            lastPathSegment = Long.parseLong(newuri.getLastPathSegment());
        }

        return lastPathSegment;
    }

    /**
     * Method to retrieve a station with a specific external id
     *
     * @param idExt The id of the station to be retrieved.
     * @return The station json for the specified id.
     */
    public StationInt getStationIdExt(int idExt) {

        String selection = ConSkedProvider.IDEXT + " = ?";
        String[] selectionArgs = {Integer.toString(idExt)};

        Cursor cursor = context.getContentResolver().query(uri, null, selection, selectionArgs, null);

        StationInt station = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {

                station = new StationInt(
                        cursor.getInt(0),       // idInt
                        cursor.getInt(1),       // idExt
                        cursor.getString(2));   // json
            }

            cursor.close();
        }
        return station;
    }

    /**
     * Method for deleting all stations
     *
     * @return The number of stations deleted.
     */
    public int deleteStationAll() {

        return context.getContentResolver().delete(uri, null, null);
    }
}
