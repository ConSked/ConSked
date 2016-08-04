package com.emailxl.consked.external_db;

/**
 * @author ECG
 */

public class Timestamp {
    private String date;
    private int timezoneType;
    private String timezone;

    // Constructors
    public Timestamp() {
    }

    public Timestamp(String date, int timezoneType, String timezone) {
        this.date = date;
        this.timezoneType = timezoneType;
        this.timezone = timezone;
    }

    // date functions
    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // timezoneType functions
    public int getTimezoneType() {
        return this.timezoneType;
    }

    public void setTimezoneType(int timezoneType) {
        this.timezoneType = timezoneType;
    }

    // timezone functions
    public String getTimezone() {
        return this.timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @Override
    public String toString() {
        return getClass().getName() + "[" +
                "date=" + date + ", " +
                "timezoneType=" + timezoneType + ", " +
                "timezone=" + timezone + ", " + "]";
    }
}
