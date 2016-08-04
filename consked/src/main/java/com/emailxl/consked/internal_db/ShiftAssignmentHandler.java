package com.emailxl.consked.internal_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.emailxl.consked.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ECG
 */

public class ShiftAssignmentHandler {
    private Context context;
    private Uri uri;

    /**
     * Method to initialize the expo handler
     *
     * @param context The caller's context.
     */
    public ShiftAssignmentHandler(Context context) {
        this.context = context;
        this.uri = new Uri.Builder()
                .scheme(AppConstants.SCHEME)
                .authority(AppConstants.AUTHORITY)
                .path(ConSkedProvider.SHIFTASSIGNMENT_TABLE)
                .build();
    }

    /**
     * Method to add a shiftassignment to the shiftassignment table
     *
     * @param shiftassignment The shiftassignment object.
     * @return The URI for the newly inserted item.
     */
    public long addShiftAssignment(ShiftAssignmentInt shiftassignment) {

        ContentValues values = new ContentValues();

        values.put(ConSkedProvider.WORKERIDEXT, shiftassignment.getWorkerIdExt());
        values.put(ConSkedProvider.JOBIDEXT, shiftassignment.getJobIdExt());
        values.put(ConSkedProvider.STATIONIDEXT, shiftassignment.getStationIdExt());
        values.put(ConSkedProvider.EXPOIDEXT, shiftassignment.getExpoIdExt());

        Uri newuri = context.getContentResolver().insert(uri, values);

        long lastPathSegment = 0;
        if (newuri != null) {
            lastPathSegment = Long.parseLong(newuri.getLastPathSegment());
        }

        return lastPathSegment;
    }

    /**
     * Method to retrieve a shiftassignment with a specific external id
     *
     * @param expoIdExt The id of the shift expo.
     * @param workerIdExt The id of the shift worker.
     * @return The shiftassignment object for the specified id.
     */
    public List<ShiftAssignmentInt> getShiftAssignmentIdExt(int expoIdExt, int workerIdExt) {

        String selection = ConSkedProvider.EXPOIDEXT + " = ? AND " +
                ConSkedProvider.WORKERIDEXT + " = ?";
        String[] selectionArgs = {Integer.toString(expoIdExt),
                Integer.toString(workerIdExt)};

        Cursor cursor = context.getContentResolver().query(uri, null, selection, selectionArgs, null);

        List<ShiftAssignmentInt> shiftassignmentList = new ArrayList<>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    ShiftAssignmentInt shiftassignment = new ShiftAssignmentInt(
                            cursor.getInt(0),       // idInt
                            cursor.getInt(1),       // workerIdExt
                            cursor.getInt(2),       // jobIdext
                            cursor.getInt(3),       // stationIdExt
                            cursor.getInt(4));      // expoIdExt

                    shiftassignmentList.add(shiftassignment);
                } while (cursor.moveToNext());
            }

            cursor.close();
        }
        return shiftassignmentList;
    }

    /**
     * Method for deleting all shiftassignments
     *
     * @return The number of rows deleted.
     */
    public int deleteShiftAssignmentAll() {

        return context.getContentResolver().delete(uri, null, null);
    }
}
