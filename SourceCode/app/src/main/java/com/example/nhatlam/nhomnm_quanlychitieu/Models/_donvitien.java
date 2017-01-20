package com.example.nhatlam.nhomnm_quanlychitieu.Models;

/**
 * Created by NHATLAM on 1/18/2017.
 */

public class _donvitien {
    private int donvitien_id;
    private String donvitien_name;
    private String tigiadola;

    public _donvitien() {

    }
    public _donvitien(int donvitien_id, String donvitien_name, String tigiadola) {
        this.donvitien_id = donvitien_id;
        this.donvitien_name = donvitien_name;
        this.tigiadola = tigiadola;
    }

    public _donvitien(String donvitien_name, String tigiadola) {
        this.donvitien_name = donvitien_name;
        this.tigiadola = tigiadola;
    }

    public int getDonvitien_id() {
        return donvitien_id;
    }

    public void setDonvitien_id(int donvitien_id) {
        this.donvitien_id = donvitien_id;
    }

    public String getDonvitien_name() {
        return donvitien_name;
    }

    public void setDonvitien_name(String donvitien_name) {
        this.donvitien_name = donvitien_name;
    }

    public String getTigiadola() {
        return tigiadola;
    }

    public void setTigiadola(String tigiadola) {
        this.tigiadola = tigiadola;
    }
}
