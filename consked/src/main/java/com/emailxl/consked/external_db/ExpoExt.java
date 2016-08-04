package com.emailxl.consked.external_db;

/**
 * @author ECG
 */

public class ExpoExt {
    private int expIdExt;
    private Timestamp startTime;
    private Timestamp stopTime;
    private int expoHourCeiling;
    private String title;
    private String description;
    private int scheduleAssignAsYouGo;
    private int scheduleVisible;
    private int allowScheduleTimeConflict;
    private int newUserAddedOnRegistration;
    private int workerIdExt;

    // Constructors
    public ExpoExt() {
    }

    public ExpoExt(int expIdExt, Timestamp startTime, Timestamp stopTime, int expoHourCeiling,
                   String title, String description, int scheduleAssignAsYouGo,
                   int scheduleVisible, int allowScheduleTimeConflict,
                   int newUserAddedOnRegistration, int workerIdExt) {
         this.expIdExt = expIdExt;
         this.startTime = startTime;
         this.stopTime = stopTime;
         this.expoHourCeiling = expoHourCeiling;
         this.title = title;
         this.description = description;
         this.scheduleAssignAsYouGo = scheduleAssignAsYouGo;
         this.scheduleVisible = scheduleVisible;
         this.allowScheduleTimeConflict = allowScheduleTimeConflict;
         this.newUserAddedOnRegistration = newUserAddedOnRegistration;
         this.workerIdExt = workerIdExt;
    }

    // expIdExt functions
    public int getExpoIdExt() {
        return this.expIdExt;
    }

    public void setExpoIdExt(int expIdExt) {
        this.expIdExt = expIdExt;
    }

    // startTime functions
    public Timestamp getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    // stopTime functions
    public Timestamp getStopTime() {
        return this.stopTime;
    }

    public void setStopTime(Timestamp stopTime) {
        this.stopTime = stopTime;
    }

    // expoHourCeiling functions
    public int getExpoHourCeiling() {
        return this.expoHourCeiling;
    }

    public void setExpoHourCeiling(int expoHourCeiling) {
        this.expoHourCeiling = expoHourCeiling;
    }

    // title functions
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // description functions
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // scheduleAssignAsYouGo functions
    public int getScheduleAssignAsYouGo() {
        return this.scheduleAssignAsYouGo;
    }

    public void setScheduleAssignAsYouGo(int scheduleAssignAsYouGo) {
        this.scheduleAssignAsYouGo = scheduleAssignAsYouGo;
    }

    // scheduleVisible functions
    public int getScheduleVisible() {
        return this.scheduleVisible;
    }

    public void setScheduleVisible(int scheduleVisible) {
        this.scheduleVisible = scheduleVisible;
    }

    // allowScheduleTimeConflict functions
    public int getAllowScheduleTimeConflic() {
        return this.allowScheduleTimeConflict;
    }

    public void setAllowScheduleTimeConflict(int allowScheduleTimeConflict) {
        this.allowScheduleTimeConflict = allowScheduleTimeConflict;
    }

    // newUserAddedOnRegistration functions
    public int getNewUserAddedOnRegistration() {
        return this.newUserAddedOnRegistration;
    }

    public void setNewUserAddedOnRegistration(int newUserAddedOnRegistration) {
        this.newUserAddedOnRegistration = newUserAddedOnRegistration;
    }

    // workerIdExt functions
    public int getWorkerIdExt() {
        return this.workerIdExt;
    }

    public void setWorkerIdExt(int workerIdExt) {
        this.workerIdExt = workerIdExt;
    }

    @Override
    public String toString() {
        return getClass().getName() + "[" +
                "expIdExt=" + expIdExt + ", " +
                "startTime=" + startTime.toString() + ", " +
                "stopTime=" + stopTime.toString() + ", " +
                "expoHourCeiling=" + expoHourCeiling + ", " +
                "title=" + title + ", " +
                "description=" + description + ", " +
                "scheduleAssignAsYouGo=" + scheduleAssignAsYouGo + ", " +
                "scheduleVisible=" + scheduleVisible + ", " +
                "allowScheduleTimeConflict=" + allowScheduleTimeConflict + ", " +
                "newUserAddedOnRegistration=" + newUserAddedOnRegistration + ", " +
                "workerIdExt=" + workerIdExt + "]";
    }
}
