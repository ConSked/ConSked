package com.emailxl.consked.internal_db;

/**
 * The station table data class
 *
 * @author ECG
 */
public class StationInt {
    private int idInt;
    private int idExt;
    private String json;

    // Constructors
    public StationInt() {
    }

    public StationInt(int idInt, int idExt, String json) {
        this.idInt = idInt;
        this.idExt = idExt;
        this.json = json;
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

    // json functions
    public String getJson() {
        return this.json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    @Override
    public String toString() {
        return getClass().getName() + "[" +
                "idInt=" + idInt + ", " +
                "idExt=" + idExt + ", " +
                "json=" + json + "]";
    }

}
