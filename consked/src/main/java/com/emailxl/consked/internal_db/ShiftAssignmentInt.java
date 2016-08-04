package com.emailxl.consked.internal_db;

/**
 * @author ECG
 */

public class ShiftAssignmentInt {
    private int idInt;
    private int workerIdExt;
    private int jobIdExt;
    private int stationIdExt;
    private int expoIdExt;

    // Constructors
    public ShiftAssignmentInt() {
    }

    public ShiftAssignmentInt(int idInt, int workerIdExt, int jobIdExt, int stationIdExt, int expoIdExt) {
        this.idInt = idInt;
        this.workerIdExt = workerIdExt;
        this.jobIdExt = jobIdExt;
        this.stationIdExt = stationIdExt;
        this.expoIdExt = expoIdExt;
    }

    // idInt functions
    public int getIdInt() {
        return this.idInt;
    }

    public void setIdInt(int idInt) {
        this.idInt = idInt;
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
                "idInt=" + idInt + ", " +
                "workerIdExt=" + workerIdExt + ", " +
                "jobIdExt=" + jobIdExt + ", " +
                "stationIdExt=" + stationIdExt + ", " +
                "expoIdExt=" + expoIdExt + "]";
    }
}
