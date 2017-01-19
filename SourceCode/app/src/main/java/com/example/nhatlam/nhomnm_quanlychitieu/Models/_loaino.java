package com.example.nhatlam.nhomnm_quanlychitieu.Models;

/**
 * Created by NHATLAM on 1/18/2017.
 */

public class _loaino {
    private int loaino_id;
    private String loaino_name;

    public _loaino(int loaino_id, String loaino_name) {
        this.loaino_id = loaino_id;
        this.loaino_name = loaino_name;
    }

    public _loaino(String loaino_name) {
        this.loaino_name = loaino_name;
    }

    public int getLoaino_id() {
        return loaino_id;
    }

    public void setLoaino_id(int loaino_id) {
        this.loaino_id = loaino_id;
    }

    public String getLoaino_name() {
        return loaino_name;
    }

    public void setLoaino_name(String loaino_name) {
        this.loaino_name = loaino_name;
    }
}
