package com.example.nhatlam.nhomnm_quanlychitieu.Models;

/**
 * Created by NHATLAM on 1/18/2017.
 */

public class _loaivi {
    private int loaivi_id;
    private String loaivi_name;

    public _loaivi(int loaivi_id, String loaivi_name) {
        this.loaivi_id = loaivi_id;
        this.loaivi_name = loaivi_name;
    }

    public _loaivi(String loaivi_name) {
        this.loaivi_name = loaivi_name;
    }

    public int getLoaivi_id() {
        return loaivi_id;
    }

    public void setLoaivi_id(int loaivi_id) {
        this.loaivi_id = loaivi_id;
    }

    public String getLoaivi_name() {
        return loaivi_name;
    }

    public void setLoaivi_name(String loaivi_name) {
        this.loaivi_name = loaivi_name;
    }
}
