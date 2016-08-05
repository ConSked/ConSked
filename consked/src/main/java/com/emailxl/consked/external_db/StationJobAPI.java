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

public class StationJobAPI {

    private static final String SERVER_URL = "http://dev1.consked.com/webservice/StationJob/";
    private static final String TAG = "StationJobAPI";
    private static final boolean LOG = false;

    public static StationJobExt[] readStationJob(int id) {

        String stringUrl = SERVER_URL;

        if (id != 0) {
            stringUrl = stringUrl + id;
        }

        InputStream is = null;
        StationJobExt[] output = null;

        try {
            URL url = new URL(stringUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("x-li-format", "json");

            if (conn.getResponseCode() == 200) {

                is = conn.getInputStream();
                String result = readStream(is);
                output = loadStationJob(result);
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

    private static StationJobExt[] loadStationJob(String result) throws Exception {

        JSONArray jArray = new JSONArray(result);

        int len = jArray.length();
        StationJobExt[] output = new StationJobExt[len];

        for (int i = 0; i < len; i++) {
            JSONObject json = jArray.getJSONObject(i);

            int jstationid = json.has("stationid") ? json.getInt("stationid") : 0;
            int jexpoid = json.has("expoid") ? json.getInt("expoid") : 0;
            JSONObject jstartTime = json.has("startTime") ? json.getJSONObject("startTime") : null;
            JSONObject jstopTime = json.has("stopTime") ? json.getJSONObject("stopTime") : null;
            String jstationTitle = json.has("title") ? json.getString("title") : null;
            String jdescription = json.has("description") ? json.getString("description") : null;
            String jlocation = json.has("location") ? json.getString("location") : null;
            String jURL = json.has("URL") ? json.getString("URL") : null;
            String jinstruction = json.has("instruction") ? json.getString("instruction") : null;
            int jjobid = json.has("jobid") ? json.getInt("jobid") : 0;
            String jjobTitle = json.has("jobTitle") ? json.getString("jobTitle") : null;
            int jmaxCrew = json.has("maxCrew") ? json.getInt("maxCrew") : 0;
            int jminCrew = json.has("minCrew") ? json.getInt("minCrew") : 0;
            int jassignedCrew = json.has("assignedCrew") ? json.getInt("assignedCrew") : 0;
            int jmaxSupervisor = json.has("maxSupervisor") ? json.getInt("maxSupervisor") : 0;
            int jminSupervisor = json.has("minSupervisor") ? json.getInt("minSupervisor") : 0;
            int jassignedSupervisor = json.has("assignedSupervisor") ? json.getInt("assignedSupervisor") : 0;


            StationJobExt stationjob = new StationJobExt();
            stationjob.setStationIdExt(jstationid);
            stationjob.setExpoIdExt(jexpoid);
            stationjob.setStartTime(TimestampUtils.loadTimestamp(jstartTime));
            stationjob.setStopTime(TimestampUtils.loadTimestamp(jstopTime));
            stationjob.setStationTitle(jstationTitle);
            stationjob.setDescription(jdescription);
            stationjob.setLocation(jlocation);
            stationjob.setURL(jURL);
            stationjob.setInstruction(jinstruction);
            stationjob.setJobIdExt(jjobid);
            stationjob.setJobTitle(jjobTitle);
            stationjob.setMaxCrew(jmaxCrew);
            stationjob.setMinCrew(jminCrew);
            stationjob.setAssignedCrew(jassignedCrew);
            stationjob.setMaxSupervisor(jmaxSupervisor);
            stationjob.setMinSupervisor(jminSupervisor);
            stationjob.setAssignedSupervisor(jassignedSupervisor);

            output[i] = stationjob;
        }

        return output;
    }
}
