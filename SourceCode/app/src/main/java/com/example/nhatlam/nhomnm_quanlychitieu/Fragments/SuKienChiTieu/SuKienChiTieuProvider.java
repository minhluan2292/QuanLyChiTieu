package com.example.nhatlam.nhomnm_quanlychitieu.Fragments.SuKienChiTieu;

/**
 * Created by MinhLuan on 2/4/2017.
 */

public class SuKienChiTieuProvider {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int img_res;
    private String tengiaodich;
    private String sotien;

    public SuKienChiTieuProvider(int id,int img_res, String tengiaodich, String sotien) {
        this.id = id;
        this.img_res = img_res;
        this.tengiaodich = tengiaodich;
        this.sotien = sotien;
    }

    public int getImg_res() {
        return img_res;
    }

    public void setImg_res(int img_res) {
        this.img_res = img_res;
    }

    public String gettengiaodich() {
        return tengiaodich;
    }

    public void settengiaodich(String tengiaodich) {
        this.tengiaodich = tengiaodich;
    }

    public String getSotien() {
        return sotien;
    }

    public void setSotien(String sotien) {
        this.sotien = sotien;
    }
}