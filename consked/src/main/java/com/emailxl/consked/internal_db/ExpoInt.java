package com.emailxl.consked.internal_db;

/**
 * The expo table data class
 *
 * @author ECG
 */
public class ExpoInt {
    private int idInt;
    private int expoIdExt;
    private String startTime;
    private String stopTime;
    private String title;

    // Constructors
    public ExpoInt() {
    }

    public ExpoInt(int idInt, int expoIdExt, String startTime, String stopTime, String title) {
        this.idInt = idInt;
        this.expoIdExt = expoIdExt;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.title = title;
    }

    // idInt functions
    public int getIdInt() {
        return this.idInt;
    }

    public void setIdInt(int idInt) {
        this.idInt = idInt;
    }

    // expoIdExt functions
    public int getExpoIdExt() {
        return this.expoIdExt;
    }

    public void setExpoIdExt(int expoIdExt) {
        this.expoIdExt = expoIdExt;
    }

    // startTime functions
    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    // stopTime functions
    public String getStopTime() {
        return this.stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    // title functions
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return getClass().getName() + "[" +
                "idInt=" + idInt + ", " +
                "expoIdExt=" + expoIdExt + ", " +
                "startTime=" + startTime + ", " +
                "stopTime=" + stopTime + ", " +
                "title=" + title + "]";
    }
}
