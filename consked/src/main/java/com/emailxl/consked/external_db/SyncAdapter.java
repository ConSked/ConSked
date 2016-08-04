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
import com.emailxl.consked.internal_db.ShiftAssignmentHandler;
import com.emailxl.consked.internal_db.ShiftAssignmentInt;
import com.emailxl.consked.internal_db.ShiftStatusHandler;
import com.emailxl.consked.internal_db.ShiftStatusInt;
import com.emailxl.consked.internal_db.StationJobHandler;
import com.emailxl.consked.internal_db.StationJobInt;
import com.emailxl.consked.internal_db.WorkerHandler;
import com.emailxl.consked.internal_db.WorkerInt;

import static com.emailxl.consked.external_db.ExpoAPI.readExpo;
import static com.emailxl.consked.external_db.ShiftAssignmentAPI.readShiftAssignment;
import static com.emailxl.consked.external_db.ShiftStatusAPI.readShiftStatus;
import static com.emailxl.consked.external_db.StationJobAPI.readStationJob;
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
        int expoId = extras.getInt("expoId");
        int stationId = extras.getInt("stationId");
        int workerId = extras.getInt("workerId");
        String table = extras.getString("table");

        syncReadExt(id, expoId, stationId, workerId, table);

        if (LOG) Log.i(TAG, "Ending sync");
    }

    private void syncReadExt(int id, int expoId, int stationId, int workerId, String table) {

        if (LOG) Log.i(TAG, "Read");

        if ("Expo".equals(table)) {
            ExpoHandler db = new ExpoHandler(mContext);
            db.deleteExpoAll();

            ExpoExt[] expoExts = readExpo(id);

            if (expoExts != null && expoExts.length != 0)
            {
                for (ExpoExt expoExt : expoExts) {

                    ExpoInt expo = new ExpoInt();

                    expo.setExpoIdExt(expoExt.getExpoIdExt());
                    expo.setStartTime(expoExt.getStartTime().getDate());
                    expo.setStopTime(expoExt.getStopTime().getDate());
                    expo.setTitle(expoExt.getTitle());

                    db.addExpo(expo);
                }
            }
        } else if ("ShiftAssignment".equals(table)) {
            ShiftAssignmentHandler db = new ShiftAssignmentHandler(mContext);
            db.deleteShiftAssignmentAll();

            ShiftAssignmentExt[] shiftAssignmentExts = readShiftAssignment(expoId, workerId);

            if (shiftAssignmentExts != null && shiftAssignmentExts.length != 0) {
                for (ShiftAssignmentExt shiftAssignmentExt : shiftAssignmentExts) {

                    ShiftAssignmentInt shiftassignment = new ShiftAssignmentInt();

                    shiftassignment.setWorkerIdExt(shiftAssignmentExt.getWorkerIdExt());
                    shiftassignment.setExpoIdExt(shiftAssignmentExt.getExpoIdExt());
                    shiftassignment.setStationIdExt(shiftAssignmentExt.getStationIdExt());
                    shiftassignment.setWorkerIdExt(shiftAssignmentExt.getWorkerIdExt());

                    db.addShiftAssignment(shiftassignment);
                }
            }
        } else if ("ShiftStatus".equals(table)) {
            ShiftStatusHandler db = new ShiftStatusHandler(mContext);
            db.deleteShiftStatusAll();

            ShiftStatusExt[] shiftStatusExts = readShiftStatus(expoId, stationId, workerId);

            if (shiftStatusExts != null && shiftStatusExts.length != 0) {
                for (ShiftStatusExt shiftStatusExt : shiftStatusExts) {

                    ShiftStatusInt shiftstatus = new ShiftStatusInt();

                    shiftstatus.setShiftstatusIdExt(shiftStatusExt.getShiftstatusIdExt());
                    shiftstatus.setExpoIdExt(shiftStatusExt.getExpoIdExt());
                    shiftstatus.setStationIdExt(shiftStatusExt.getStationIdExt());
                    shiftstatus.setWorkerIdExt(shiftStatusExt.getWorkerIdExt());
                    shiftstatus.setStatusType(shiftStatusExt.getStatusType());
                    shiftstatus.setStatusTime(shiftStatusExt.getStatusTime().getDate());

                    db.addShiftStatus(shiftstatus);
                }
            }
        } else if ("StationJob".equals(table)) {
            StationJobHandler db = new StationJobHandler(mContext);
            db.deleteStationJobAll();

            StationJobExt[] stationJobExts = readStationJob(id);

            if (stationJobExts != null && stationJobExts.length != 0) {
                for (StationJobExt stationJobExt: stationJobExts) {

                    StationJobInt stationjob = new StationJobInt();

                    stationjob.setStationIdExt(stationJobExt.getStationIdExt());
                    stationjob.setExpoIdExt(stationJobExt.getExpoIdExt());
                    stationjob.setStartTime(stationJobExt.getStartTime().getDate());
                    stationjob.setStopTime(stationJobExt.getStopTime().getDate());
                    stationjob.setStationTitle(stationJobExt.getStationTitle());

                    db.addStationJob(stationjob);
                }
            }
        } else if ("Worker".equals(table)) {
            WorkerHandler db = new WorkerHandler(mContext);
            db.deleteWorkerAll();

            WorkerExt[] workerExts = readWorker(id);

            if (workerExts != null && workerExts.length != 0) {
                for (WorkerExt workerExt: workerExts) {

                    WorkerInt worker = new WorkerInt();

                    worker.setWorkerIdExt(workerExt.getWorkerIdExt());
                    worker.setFirstName(workerExt.getFirstName());
                    worker.setLastName(workerExt.getLastName());
                    worker.setAuthrole(worker.getAuthrole());

                    db.addWorker(worker);
                }
            }
        }

        mContext.sendBroadcast(new Intent(MainActivity.ACTION_FINISHED_SYNC));
    }
}
