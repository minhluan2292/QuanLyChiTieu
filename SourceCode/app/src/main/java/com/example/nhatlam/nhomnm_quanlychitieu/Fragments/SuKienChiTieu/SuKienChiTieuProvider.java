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
    private String ngaygiaodich;
    private int parent;
    private String ghichu;

    public SuKienChiTieuProvider(int id,int img_res, String tengiaodich, String sotien, String ngaygiaodich, int parent, String ghichu) {
        this.id = id;
        this.img_res = img_res;
        this.tengiaodich = tengiaodich;
        this.sotien = sotien;
        this.ngaygiaodich = ngaygiaodich;
        this.parent = parent;
        this.ghichu = ghichu;
    }

    public int getImg_res() {
        return img_res;
    }

    public void setImg_res(int img_res) {
        this.img_res = img_res;
    }

    public String getTengiaodich() {
        return tengiaodich;
    }

    public void setTengiaodich(String tengiaodich) {
        this.tengiaodich = tengiaodich;
    }

    public String getSotien() {
        return sotien;
    }

    public void setSotien(String sotien) {
        this.sotien = sotien;
    }

    public String getNgaygiaodich() {
        return ngaygiaodich;
    }

    public void setNgaygiaodich(String ngaygiaodich) {
        this.ngaygiaodich = ngaygiaodich;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }
}