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

public class ExpoAPI {

    private static final String SERVER_URL = "http://dev1.consked.com/webservice/Expo/";
    private static final String TAG = "ExpoAPI";
    private static final boolean LOG = false;

    public static ExpoExt[] readExpo(int id) {

        String stringUrl = SERVER_URL;

        if (id != 0) {
            stringUrl = stringUrl + id;
        }

        InputStream is = null;
        ExpoExt[] output = null;

        try {
            URL url = new URL(stringUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("x-li-format", "json");

            if (conn.getResponseCode() == 200) {

                is = conn.getInputStream();
                String result = readStream(is);
                output = loadExpo(result);
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

    private static ExpoExt[] loadExpo(String result) throws Exception {

        JSONArray jArray = new JSONArray(result);

        int len = jArray.length();
        ExpoExt[] output = new ExpoExt[len];

        for (int i = 0; i < len; i++) {
            JSONObject json = jArray.getJSONObject(i);

            int jexpoid = json.has("expoid") ? json.getInt("expoid") : 0;
            JSONObject jstartTime = json.has("startTime") ? json.getJSONObject("startTime") : null;
            JSONObject jstopTime = json.has("stopTime") ? json.getJSONObject("stopTime") : null;
            int jexpoHourCeiling = json.has("expoHourCeiling") ? json.getInt("expoHourCeiling") : 0;
            String jtitle = json.has("title") ? json.getString("title") : null;
            String jdescription = json.has("description") ? json.getString("description") : null;
            int jscheduleAssignAsYouGo = json.has("scheduleAssignAsYouGo") ? json.getInt("scheduleAssignAsYouGo") : 0;
            int jscheduleVisible = json.has("scheduleVisible") ? json.getInt("scheduleVisible") : 0;
            int jallowScheduleTimeConflict = json.has("allowScheduleTimeConflict") ? json.getInt("allowScheduleTimeConflict") : 0;
            int jnewUserAddedOnRegistration = json.has("newUserAddedOnRegistration") ? json.getInt("newUserAddedOnRegistration") : 0;
            int jworkerid = json.has("workerid") ? json.getInt("workerid") : 0;

            ExpoExt expo = new ExpoExt();
            expo.setExpoIdExt(jexpoid);
            expo.setStartTime(TimestampUtils.loadTimestamp(jstartTime));
            expo.setStopTime(TimestampUtils.loadTimestamp(jstopTime));
            expo.setExpoHourCeiling(jexpoHourCeiling);
            expo.setTitle(jtitle);
            expo.setDescription(jdescription);
            expo.setScheduleAssignAsYouGo(jscheduleAssignAsYouGo);
            expo.setScheduleVisible(jscheduleVisible);
            expo.setAllowScheduleTimeConflict(jallowScheduleTimeConflict);
            expo.setNewUserAddedOnRegistration(jnewUserAddedOnRegistration);
            expo.setWorkerIdExt(jworkerid);

            output[i] = expo;
        }

        return output;
    }
}
