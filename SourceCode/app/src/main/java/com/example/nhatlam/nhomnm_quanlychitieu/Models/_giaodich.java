package com.example.nhatlam.nhomnm_quanlychitieu.Models;

/**
 * Created by NHATLAM on 1/18/2017.
 */

public class _giaodich {
    private int giaodich_id;
    private int vi_id;
    private int category_id;
    private String sotien;
    private String ngaygiaodich;
    private String ghichu;


    public _giaodich() {

    }
    public _giaodich(int giaodich_id, int vi_id, int category_id, String sotien, String ngaygiaodich, String ghichu) {
        this.giaodich_id = giaodich_id;
        this.vi_id = vi_id;
        this.category_id = category_id;
        this.sotien = sotien;
        this.ngaygiaodich = ngaygiaodich;
        this.ghichu = ghichu;
    }

    public _giaodich(int vi_id, int category_id, String sotien, String ngaygiaodich, String ghichu) {
        //this.giaodich_id = giaodich_id;
        this.vi_id = vi_id;
        this.category_id = category_id;
        this.sotien = sotien;
        this.ngaygiaodich = ngaygiaodich;
        this.ghichu = ghichu;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public int getGiaodich_id() {
        return giaodich_id;
    }

    public void setGiaodich_id(int giaodich_id) {
        this.giaodich_id = giaodich_id;
    }

    public String getNgaygiaodich() {
        return ngaygiaodich;
    }

    public void setNgaygiaodich(String ngaygiaodich) {
        this.ngaygiaodich = ngaygiaodich;
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
}
