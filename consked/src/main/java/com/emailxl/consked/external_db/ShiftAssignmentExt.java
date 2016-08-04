package com.emailxl.consked.external_db;

/**
 * @author ECG
 */

public class ShiftAssignmentExt {
    private int workerIdExt;
    private int jobIdExt;
    private int stationIdExt;
    private int expoIdExt;

    // Constructors
    public ShiftAssignmentExt() {
    }

    public ShiftAssignmentExt(int workerIdExt, int jobIdExt, int stationIdExt, int expoIdExt) {
        this.workerIdExt = workerIdExt;
        this.jobIdExt = jobIdExt;
        this.stationIdExt = stationIdExt;
        this.expoIdExt = expoIdExt;
    }

    // workerIdExt functions
    public int getWorkerIdExt() {
        return this.workerIdExt;
    }

    public void setWorkerIdExt(int workerIdExt) {
        this.workerIdExt = workerIdExt;
    }

    // jobIdExt functions
    public int getJobIdExt() {
        return this.jobIdExt;
    }

    public void setJobIdExt(int jobIdExt) {
        this.jobIdExt = jobIdExt;
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

    @Override
    public String toString() {
        return getClass().getName() + "[" +
                "workerIdExt=" + workerIdExt + ", " +
                "jobIdExt=" + jobIdExt + ", " +
                "stationIdExt=" + stationIdExt + ", " +
                "expoIdExt=" + expoIdExt + ", " + "]";
    }
}
