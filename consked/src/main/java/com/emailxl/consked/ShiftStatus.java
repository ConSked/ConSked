package com.emailxl.consked;

import android.accounts.Account;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.emailxl.consked.internal_db.ShiftStatusHandler;
import com.emailxl.consked.internal_db.ShiftStatusInt;
import com.emailxl.consked.utils.AppConstants;

import java.util.List;

import static com.emailxl.consked.utils.Utils.createSyncAccount;
import static com.emailxl.consked.utils.Utils.toastError;

public class ShiftStatus extends AppCompatActivity {
    public static final String ACTION_FINISHED_SYNC = "com.emailxl.consked.ACTION_FINISHED_SYNC";
    private static final String TAG = "ShiftStatus";
    private static boolean LOG = false;

    private EditText etExpoId, etStationId, etWorkerId;
    private TextView tvOutput;
    private Account account;
    private static IntentFilter syncIntentFilter = new IntentFilter(ACTION_FINISHED_SYNC);
    private ProgressDialog pd;
    private BroadcastReceiver shiftstatusBroadcastReceiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_status);
        if (LOG) Log.i(TAG, "Create Page");

        // Check network connectivity
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            etExpoId = (EditText) findViewById(R.id.edit_expoid);
            etExpoId.setText("1");

            etStationId = (EditText) findViewById(R.id.edit_stationid);
            etStationId.setText("1");

            etWorkerId = (EditText) findViewById(R.id.edit_workerid);
            etWorkerId.setText("1");

            tvOutput = (TextView) findViewById(R.id.output);
            tvOutput.setText("");

            account = createSyncAccount(this);

            findViewById(R.id.button_test).setOnClickListener(
                    new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {
                            int expoId = Integer.parseInt(etExpoId.getText().toString().trim());
                            int stationId = Integer.parseInt(etStationId.getText().toString().trim());
                            int workerId = Integer.parseInt(etWorkerId.getText().toString().trim());

                            Bundle extras = new Bundle();
                            extras.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
                            extras.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
                            extras.putInt("expoId", expoId);
                            extras.putInt("stationId", stationId);
                            extras.putInt("workerId", workerId);
                            extras.putString("table", "ShiftStatus");

                            ContentResolver.requestSync(account, AppConstants.AUTHORITY, extras);
                            pd = ProgressDialog.show(view.getContext(), "", getString(R.string.loading), true);
                        }
                    }
            );
        } else {
            toastError(ShiftStatus.this, R.string.error_network);
        }

        shiftstatusBroadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                String output = "";

                int expoId = Integer.parseInt(etExpoId.getText().toString().trim());
                int stationId = Integer.parseInt(etStationId.getText().toString().trim());
                int workerId = Integer.parseInt(etWorkerId.getText().toString().trim());

                ShiftStatusHandler db = new ShiftStatusHandler(context);
                List<ShiftStatusInt> shiftstatusInts = db.getShiftStatusIdExt(expoId, stationId, workerId);
                if (shiftstatusInts != null) {
                    for (ShiftStatusInt shiftstatusInt: shiftstatusInts) {
                        output += shiftstatusInt.toString() + "\n\n";
                    }
                }

                tvOutput.setText(output);

                if (pd != null && pd.isShowing()) {
                    pd.dismiss();
                }
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(shiftstatusBroadcastReceiver, syncIntentFilter);
    }

    protected void onPause() {
        super.onPause();

        if (shiftstatusBroadcastReceiver != null) {
            unregisterReceiver(shiftstatusBroadcastReceiver);
        }
    }
}
