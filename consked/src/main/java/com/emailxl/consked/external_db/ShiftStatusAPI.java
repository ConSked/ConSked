package com.emailxl.consked.external_db;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.emailxl.consked.utils.Utils.readStream;

/**
 * @author ECG
 */

public class ShiftStatusAPI {

    private static final String SERVER_URL = "http://dev1.consked.com/webservice/ShiftStatus/";
    private static final String TAG = "ShiftStatusAPI";
    private static final boolean LOG = false;

    public static ShiftStatusExt[] readShiftStatus(int expoIdExt, int stationIdExt, int workerIdExt) {

        String stringUrl = SERVER_URL;

        if (expoIdExt != 0) {
            stringUrl += expoIdExt;

            if (stationIdExt != 0) {
                stringUrl += "/" + stationIdExt;

                if (workerIdExt != 0) {
                    stringUrl += "/" + workerIdExt;
                }
            }
        }

        InputStream is = null;
        ShiftStatusExt[] output = null;

        try {
            URL url = new URL(stringUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("x-li-format", "json");

            if (conn.getResponseCode() == 200) {

                is = conn.getInputStream();
                String result = readStream(is);
                output = loadShiftStatus(result);
            }
        } catch (Exception e) {
            if (LOG) Log.e(TAG, e.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                    if (LOG) Log.e(TAG, e.getMessage());
                }
            }
        }

        return output;
    }

    private static ShiftStatusExt[] loadShiftStatus(String result) throws Exception {

        JSONArray jArray = new JSONArray(result);

        int len = jArray.length();
        ShiftStatusExt[] output = new ShiftStatusExt[len];

        for (int i = 0; i < len; i++) {
            JSONObject json = jArray.getJSONObject(i);

            int idExt = json.has("shiftstatusid") ? json.getInt("shiftstatusid") : 0;
            int workerIdExt = json.has("workerid") ? json.getInt("workerid") : 0;
            int stationIdExt = json.has("stationid") ? json.getInt("stationid") : 0;
            int expoIdExt = json.has("expoid") ? json.getInt("expoid") : 0;
            String statusType = json.has("statusType") ? json.getString("statusType") : null;
            JSONObject jStatusTime = json.has("statusTime") ? json.getJSONObject("statusTime") : null;

            String statusTime = "";
            if (jStatusTime != null) {
                statusTime = jStatusTime.has("date") ? jStatusTime.getString("date") : null;
            }

            ShiftStatusExt shiftstatus = new ShiftStatusExt();
            shiftstatus.setIdExt(idExt);
            shiftstatus.setWorkerIdExt(workerIdExt);
            shiftstatus.setStationIdExt(stationIdExt);
            shiftstatus.setExpoIdExt(expoIdExt);
            shiftstatus.setStatusType(statusType);
            shiftstatus.setStatusTime(statusTime);

            output[i] = shiftstatus;
        }

        return output;
    }
}
