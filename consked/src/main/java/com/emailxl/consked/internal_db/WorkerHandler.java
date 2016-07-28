package com.emailxl.consked.internal_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.emailxl.consked.utils.AppConstants;

/**
 * Class for handling the worker table
 * <p/>
 * This class is a wrapper for the worker table part of the Content Provider
 *
 * @author ECG
 */
public class WorkerHandler {
    private Context context;
    private Uri uri;

    /**
     * Method to initialize the worker handler
     *
     * @param context The caller's context.
     */
    public WorkerHandler(Context context) {
        this.context = context;
        this.uri = new Uri.Builder()
                .scheme(AppConstants.SCHEME)
                .authority(AppConstants.AUTHORITY)
                .path(ConSkedProvider.EXPO_TABLE)
                .build();
    }

    /**
     * Method to add an worker to the worker table
     *
     * @param worker The worker object.
     * @return The URI for the newly inserted item.
     */
    public long addWorker(WorkerInt worker) {

        ContentValues values = new ContentValues();

        values.put(ConSkedProvider.IDEXT, worker.getIdExt());
        values.put(ConSkedProvider.JSON, worker.getJson());

        Uri newuri = context.getContentResolver().insert(uri, values);

        long lastPathSegment = 0;
        if (newuri != null) {
            lastPathSegment = Long.parseLong(newuri.getLastPathSegment());
        }

        return lastPathSegment;
    }

    /**
     * Method to retrieve a worker with a specific external id
     *
     * @param idExt The id of the worker to be retrieved.
     * @return The worker json for the specified id.
     */
    public WorkerInt getWorkerIdExt(int idExt) {

        String selection = ConSkedProvider.IDEXT + " = ?";
        String[] selectionArgs = {Integer.toString(idExt)};

        Cursor cursor = context.getContentResolver().query(uri, null, selection, selectionArgs, null);

        WorkerInt worker = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {

                worker = new WorkerInt(
                        cursor.getInt(0),       // idInt
                        cursor.getInt(1),       // idExt
                        cursor.getString(2));   // json
            }

            cursor.close();
        }
        return worker;
    }

    /**
     * Method for deleting all workers
     *
     * @return The number of rows deleted.
     */
    public int deleteWorkerAll() {

        return context.getContentResolver().delete(uri, null, null);
    }
}
