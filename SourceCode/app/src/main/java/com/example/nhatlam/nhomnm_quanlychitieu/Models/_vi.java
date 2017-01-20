package com.example.nhatlam.nhomnm_quanlychitieu.Models;

/**
 * Created by NHATLAM on 1/18/2017.
 */

public class _vi {
    private int vi_id;
    private int user_id;
    private String vi_name;
    private int loaivi_id;
    private String sotien;
    private int donvitien;

    public _vi() {
        //
    }
    public _vi(int vi_id, int user_id, String vi_name, int loaivi_id, String sotien, int donvitien) {
        this.vi_id = vi_id;
        this.user_id = user_id;
        this.vi_name = vi_name;
        this.loaivi_id = loaivi_id;
        this.sotien = sotien;
        this.donvitien = donvitien;
    }

    public _vi(int user_id, String vi_name, int loaivi_id, String sotien, int donvitien) {
        //this.vi_id = vi_id;
        this.user_id = user_id;
        this.vi_name = vi_name;
        this.loaivi_id = loaivi_id;
        this.sotien = sotien;
        this.donvitien = donvitien;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getDonvitien() {
        return donvitien;
    }

    public void setDonvitien(int donvitien) {
        this.donvitien = donvitien;
    }

    public int getLoaivi_id() {
        return loaivi_id;
    }

    public void setLoaivi_id(int loaivi_id) {
        this.loaivi_id = loaivi_id;
    }

    public String getSotien() {
        return sotien;
    }

    public void setSotien(String sotien) {
        this.sotien = sotien;
    }

    public int getVi_id() {
        return vi_id;
    }

    public void setVi_id(int vi_id) {
        this.vi_id = vi_id;
    }

    public String getVi_name() {
        return vi_name;
    }

    public void setVi_name(String vi_name) {
        this.vi_name = vi_name;
    }
}
