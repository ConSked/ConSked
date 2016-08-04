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

public class StationJobHandler {
    private Context context;
    private Uri uri;

    /**
     * Method to initialize the station handler
     *
     * @param context The caller's context.
     */
    public StationJobHandler(Context context) {
        this.context = context;
        this.uri = new Uri.Builder()
                .scheme(AppConstants.SCHEME)
                .authority(AppConstants.AUTHORITY)
                .path(ConSkedProvider.STATIONJOB_TABLE)
                .build();
    }

    /**
     * Method to add an station to the station table
     *
     * @param stationJob The station object.
     * @return The URI for the newly inserted item.
     */
    public long addStationJob(StationJobInt stationJob) {

        ContentValues values = new ContentValues();

        values.put(ConSkedProvider.STATIONIDEXT, stationJob.getStationIdExt());
        values.put(ConSkedProvider.EXPOIDEXT, stationJob.getExpoIdExt());
        values.put(ConSkedProvider.STARTTIME, stationJob.getStartTime());
        values.put(ConSkedProvider.STOPTIME, stationJob.getStopTime());
        values.put(ConSkedProvider.STATIONTITLE, stationJob.getStationTitle());

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
     * @param stationIdExt The id of the station to be retrieved.
     * @return The station json for the specified id.
     */
    public StationJobInt getStationJobIdExt(int stationIdExt) {

        String selection = ConSkedProvider.STATIONIDEXT + " = ?";
        String[] selectionArgs = {Integer.toString(stationIdExt)};

        Cursor cursor = context.getContentResolver().query(uri, null, selection, selectionArgs, null);

        StationJobInt stationJob = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {

                stationJob = new StationJobInt(
                        cursor.getInt(0),       // idInt
                        cursor.getInt(1),       // stationIdExt
                        cursor.getInt(2),       // expoIdExt
                        cursor.getString(3),    // startTime
                        cursor.getString(4),    // stopTime
                        cursor.getString(5));   // stationTitle
            }

            cursor.close();
        }
        return stationJob;
    }

    /**
     * Method for deleting all stations
     *
     * @return The number of stations deleted.
     */
    public int deleteStationJobAll() {

        return context.getContentResolver().delete(uri, null, null);
    }
}
