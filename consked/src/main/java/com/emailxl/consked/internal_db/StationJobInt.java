package com.emailxl.consked.internal_db;

/**
 * The station table data class
 *
 * @author ECG
 */
public class StationJobInt {
    private int idInt;
    private int stationIdExt;
    private int expoIdExt;
    private String startTime;
    private String stopTime;
    private String stationTitle;

    // Constructors
    public StationJobInt() {
    }

    public StationJobInt(int idInt, int stationIdExt, int expoIdExt, String startTime, String stopTime,
                         String stationTitle) {
        this.idInt = idInt;
        this.stationIdExt = stationIdExt;
        this.expoIdExt = expoIdExt;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.stationTitle = stationTitle;
    }

    // idInt functions
    public int getIdInt() {
        return this.idInt;
    }

    public void setIdInt(int idInt) {
        this.idInt = idInt;
    }

    // stationIdExt functions
    public int getStationIdExt() {
        return this.stationIdExt;
    }

    public void setStationIdExt(int stationIdExt) {
        this.stationIdExt = stationIdExt;
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

    // stationTitle functions
    public String getStationTitle() {
        return this.stationTitle;
    }

    public void setStationTitle(String stationTitle) {
        this.stationTitle = stationTitle;
    }


    @Override
    public String toString() {
        return getClass().getName() + "[" +
                "idInt=" + idInt + ", " +
                "stationIdExt=" + stationIdExt + ", " +
                "expoIdExt=" + expoIdExt + ", " +
                "startTime=" + startTime + ", " +
                "stopTime=" + stopTime + ", " +
                "stationTitle=" + stationTitle + "]";
    }
}
