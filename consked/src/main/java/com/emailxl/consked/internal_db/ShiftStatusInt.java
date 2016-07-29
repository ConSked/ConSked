package com.emailxl.consked.internal_db;

/**
 * @author ECG
 */

public class ShiftStatusInt {
    private int idInt;
    private int idExt;
    private int workerIdExt;
    private int stationIdExt;
    private int expoIdExt;
    private String statusType;
    private String statusTime;

    // Constructors
    public ShiftStatusInt() {
    }

    public ShiftStatusInt(int idInt, int idExt,
                          int workerIdExt, int stationIdExt, int expoIdExt,
                          String statusType, String statusTime) {
        this.idInt = idInt;
        this.idExt = idExt;
        this.workerIdExt = workerIdExt;
        this.stationIdExt = stationIdExt;
        this.expoIdExt = expoIdExt;
        this.statusType = statusType;
        this.statusTime = statusTime;
    }

    // idInt functions
    public int getIdInt() {
        return this.idInt;
    }

    public void setIdInt(int idInt) {
        this.idInt = idInt;
    }

    // idExt functions
    public int getIdExt() {
        return this.idExt;
    }

    public void setIdExt(int idExt) {
        this.idExt = idExt;
    }

    // workerIdExt functions
    public int getWorkerIdExt() {
        return this.workerIdExt;
    }

    public void setWorkerIdExt(int workerIdExt) {
        this.workerIdExt = workerIdExt;
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

    // statusType functions
    public String getStatusType() {
        return this.statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    // statusTime functions
    public String getStatusTime() {
        return this.statusTime;
    }

    public void setStatusTime(String statusTime) {
        this.statusTime = statusTime;
    }

    @Override
    public String toString() {
        return getClass().getName() + "[" +
                "idInt=" + idInt + ", " +
                "idExt=" + idExt + ", " +
                "workerIdExt=" + workerIdExt + ", " +
                "stationIdExt=" + stationIdExt + ", " +
                "expoIdExt" + expoIdExt + ", " +
                "statusType=" + statusType + ", " +
                "statusTime=" + statusTime + "]";
    }

}
