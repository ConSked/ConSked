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

public class WorkerAPI {

    private static final String SERVER_URL = "http://dev1.consked.com/webservice/Worker/";
    private static final String TAG = "WorkerAPI";
    private static final boolean LOG = false;

    public static WorkerExt[] readWorker(int id) {

        String stringUrl = SERVER_URL;

        if (id != 0) {
            stringUrl = stringUrl + id;
        }

        InputStream is = null;
        WorkerExt[] output = null;

        try {
            URL url = new URL(stringUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("x-li-format", "json");

            if (conn.getResponseCode() == 200) {

                is = conn.getInputStream();
                String result = readStream(is);
                output = loadWorker(result);
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

    private static WorkerExt[] loadWorker(String result) throws Exception {

        JSONArray jArray = new JSONArray(result);

        int len = jArray.length();
        WorkerExt[] output = new WorkerExt[len];

        for (int i = 0; i < len; i++) {
            JSONObject json = jArray.getJSONObject(i);

            int jworkerid = json.has("workerid") ? json.getInt("workerid") : 0;
            int jisDisabled = json.has("isDisabled") ? json.getInt("isDisabled") : 0;
            JSONObject jlastLoginTime = json.has("lastLoginTime") ? json.getJSONObject("lastLoginTime") : null;
            String jphone = json.has("phone") ? json.getString("phone") : null;
            String jemail = json.has("email") ? json.getString("email") : null;
            String jsmsemail = json.has("smsemail") ? json.getString("smsemail") : null;
            String jpasswordHash = json.has("passwordHash") ? json.getString("passwordHash") : null;
            String jresetCodeHash = json.has("resetCodeHash") ? json.getString("resetCodeHash") : null;
            String jfirstName = json.has("firstName") ? json.getString("firstName") : null;
            String jmiddleName = json.has("middleName") ? json.getString("middleName") : null;
            String jlastName = json.has("lastName") ? json.getString("lastName") : null;
            String jexternalAuthentication = json.has("externalAuthentication") ? json.getString("externalAuthentication") : null;
            String jauthrole = json.has("authrole") ? json.getString("authrole") : null;

            WorkerExt worker = new WorkerExt();
            worker.setWorkerIdExt(jworkerid);
            worker.setIsDisabled(jisDisabled);
            worker.setLastLoginTime(TimestampUtils.loadTimestamp(jlastLoginTime));
            worker.setPhone(jphone);
            worker.setEmail(jemail);
            worker.setSmsemail(jsmsemail);
            worker.setPasswordHash(jpasswordHash);
            worker.setResetCodeHash(jresetCodeHash);
            worker.setFirstName(jfirstName);
            worker.setMiddleName(jmiddleName);
            worker.setLastName(jlastName);
            worker.setExternalAuthentication(jexternalAuthentication);
            worker.setAuthrole(jauthrole);

            output[i] = worker;
        }

        return output;
    }
}
