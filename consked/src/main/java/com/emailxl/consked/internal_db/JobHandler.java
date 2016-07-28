package com.emailxl.consked.internal_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.emailxl.consked.utils.AppConstants;

/**
 * Class for handling the job table
 * <p/>
 * This class is a wrapper for the job table part of the Content Provider
 *
 * @author ECG
 */

public class JobHandler {
    private Context context;
    private Uri uri;

    /**
     * Method to initialize the job handler
     *
     * @param context The caller's context.
     */
    public JobHandler(Context context) {
        this.context = context;
        this.uri = new Uri.Builder()
                .scheme(AppConstants.SCHEME)
                .authority(AppConstants.AUTHORITY)
                .path(ConSkedProvider.EXPO_TABLE)
                .build();
    }

    /**
     * Method to add an job to the job table
     *
     * @param job The job object.
     * @return The URI for the newly inserted item.
     */
    public long addJob(JobInt job) {

        ContentValues values = new ContentValues();

        values.put(ConSkedProvider.IDEXT, job.getIdExt());
        values.put(ConSkedProvider.JSON, job.getJson());

        Uri newuri = context.getContentResolver().insert(uri, values);

        long lastPathSegment = 0;
        if (newuri != null) {
            lastPathSegment = Long.parseLong(newuri.getLastPathSegment());
        }

        return lastPathSegment;
    }

    /**
     * Method to retrieve a job with a specific external id
     *
     * @param idExt The id of the job to be retrieved.
     * @return The job json for the specified id.
     */
    public JobInt getJobIdExt(int idExt) {

        String selection = ConSkedProvider.IDEXT + " = ?";
        String[] selectionArgs = {Integer.toString(idExt)};

        Cursor cursor = context.getContentResolver().query(uri, null, selection, selectionArgs, null);

        JobInt job = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {

                job = new JobInt(
                        cursor.getInt(0),       // idInt
                        cursor.getInt(1),       // idExt
                        cursor.getString(2));   // json
            }

            cursor.close();
        }
        return job;
    }

    /**
     * Method for deleting all jobs
     *
     * @return The number of rows deleted.
     */
    public int deleteJobAll() {

        return context.getContentResolver().delete(uri, null, null);
    }
}
