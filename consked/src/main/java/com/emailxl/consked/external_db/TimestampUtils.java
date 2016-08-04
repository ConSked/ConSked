package com.emailxl.consked.external_db;

import org.json.JSONObject;

/**
 * @author ECG
 */

public class TimestampUtils {

    static Timestamp loadTimestamp(JSONObject json) throws Exception {

        String jdate = json.has("date") ? json.getString("date") : null;
        int jtimezoneType = json.has("timezone_type") ? json.getInt("timezone_type") : 0;
        String jtimezone = json.has("timezone") ? json.getString("timezone") : null;

        return new Timestamp(jdate, jtimezoneType, jtimezone);
    }
}
