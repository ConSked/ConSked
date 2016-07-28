package com.emailxl.consked.external_db;

import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.emailxl.consked.utils.Utils.readStream;

/**
 * @author ECG
 */

public class JobAPI {

    private static final String SERVER_URL = "http://dev1.consked.com/webservice/Job/";
    private static final String TAG = "JobAPI";
    private static final boolean LOG = false;

    public static String readJob(int id) {

        String stringUrl = SERVER_URL;

        if (id != 0) {
            stringUrl = stringUrl + id;
        }

        InputStream is = null;
        String output = null;

        try {
            URL url = new URL(stringUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("x-li-format", "json");

            if (conn.getResponseCode() == 200) {

                is = conn.getInputStream();
                output = readStream(is);
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
}
