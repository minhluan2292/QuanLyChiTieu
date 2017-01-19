package com.example.nhatlam.nhomnm_quanlychitieu.Models;

/**
 * Created by NHATLAM on 1/18/2017.
 */

public class _sukienchitieu {
    private int sukienchitieu_id;
    private int vi_id;
    private int category_id;
    private String sotien;
    private String ngaythuchien;
    private boolean trangthai;
    private String ghichu;

    public _sukienchitieu(int sukienchitieu_id, int vi_id, int category_id,
                          String sotien, String ngaythuchien, boolean trangthai, String ghichu) {
        this.sukienchitieu_id = sukienchitieu_id;
        this.vi_id = vi_id;
        this.category_id = category_id;
        this.sotien = sotien;
        this.ngaythuchien = ngaythuchien;
        this.trangthai = trangthai;
        this.ghichu = ghichu;
    }

    public _sukienchitieu(int vi_id, int category_id,
                          String sotien, String ngaythuchien, boolean trangthai, String ghichu) {
        //this.sukienchitieu_id = sukienchitieu_id;
        this.vi_id = vi_id;
        this.category_id = category_id;
        this.sotien = sotien;
        this.ngaythuchien = ngaythuchien;
        this.trangthai = trangthai;
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

    public String getNgaythuchien() {
        return ngaythuchien;
    }

    public void setNgaythuchien(String ngaythuchien) {
        this.ngaythuchien = ngaythuchien;
    }

    public String getSotien() {
        return sotien;
    }

    public void setSotien(String sotien) {
        this.sotien = sotien;
    }

    public int getSukienchitieu_id() {
        return sukienchitieu_id;
    }

    public void setSukienchitieu_id(int sukienchitieu_id) {
        this.sukienchitieu_id = sukienchitieu_id;
    }

    public boolean isTrangthai() {
        return trangthai;
    }

    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }

    public int getVi_id() {
        return vi_id;
    }

    public void setVi_id(int vi_id) {
        this.vi_id = vi_id;
    }
}
