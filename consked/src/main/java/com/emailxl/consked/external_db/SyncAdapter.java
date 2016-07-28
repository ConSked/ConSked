package com.emailxl.consked.external_db;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SyncResult;
import android.os.Bundle;
import android.util.Log;

import com.emailxl.consked.MainActivity;
import com.emailxl.consked.internal_db.ExpoHandler;
import com.emailxl.consked.internal_db.ExpoInt;
import com.emailxl.consked.internal_db.JobHandler;
import com.emailxl.consked.internal_db.JobInt;
import com.emailxl.consked.internal_db.StationHandler;
import com.emailxl.consked.internal_db.StationInt;
import com.emailxl.consked.internal_db.WorkerHandler;
import com.emailxl.consked.internal_db.WorkerInt;

import static com.emailxl.consked.external_db.ExpoAPI.readExpo;
import static com.emailxl.consked.external_db.JobAPI.readJob;
import static com.emailxl.consked.external_db.StationAPI.readStation;
import static com.emailxl.consked.external_db.WorkerAPI.readWorker;

/**
 * handle the transfer of data between a server and an app.
 * using the Android sync adapter framework.
 *
 * @author ECG
 */
public class SyncAdapter extends AbstractThreadedSyncAdapter {
    private static final String TAG = "SyncAdapter";
    private static final boolean LOG = true;

    ContentResolver mContentResolver;
    private Context mContext;

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);

        mContentResolver = context.getContentResolver();
        this.mContext = context;
    }

    // For compatibility with Android 3.0 and later platform versions.
    public SyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);

        mContentResolver = context.getContentResolver();
        this.mContext = context;
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {

        if (LOG) Log.i(TAG, "Starting sync");

        int id = extras.getInt("id");
        String table = extras.getString("table");

        syncReadExt(id, table);

        if (LOG) Log.i(TAG, "Ending sync");
    }

    private void syncReadExt(int id, String table) {
        String output;

        if (LOG) Log.i(TAG, "Read");

        if ("Expo".equals(table)) {
            ExpoHandler db = new ExpoHandler(mContext);
            db.deleteExpoAll();

            output = readExpo(id);

            ExpoInt expo = new ExpoInt();
            expo.setIdExt(id);
            expo.setJson(output);

            db.addExpo(expo);

        } else if ("Job".equals(table)) {
            JobHandler db = new JobHandler(mContext);
            db.deleteJobAll();

            output = readJob(id);

            JobInt job = new JobInt();
            job.setIdExt(id);
            job.setJson(output);

            db.addJob(job);

        } else if ("Station".equals(table)) {
            StationHandler db = new StationHandler(mContext);
            db.deleteStationAll();

            output = readStation(id);

            StationInt station = new StationInt();
            station.setIdExt(id);
            station.setJson(output);

            db.addStation(station);

        } else if ("Worker".equals(table)) {
            WorkerHandler db = new WorkerHandler(mContext);
            db.deleteWorkerAll();

            output = readWorker(id);

            WorkerInt worker = new WorkerInt();
            worker.setIdExt(id);
            worker.setJson(output);

            db.addWorker(worker);
        }

        mContext.sendBroadcast(new Intent(MainActivity.ACTION_FINISHED_SYNC));
    }
}
