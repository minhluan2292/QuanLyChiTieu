package com.example.nhatlam.nhomnm_quanlychitieu.Models;

/**
 * Created by NHATLAM on 1/18/2017.
 */

public class _sono {
    private  int sono_id;
    private int vi_id;
    private int loaino_id;
    private String sotien;
    private String ghichu;
    private String ngaygiaodich;
    private String doituong;
    private String diadiem;
    private String thoihan;
    private boolean trangthai;

    public _sono(int sono_id, int vi_id, int loaino_id, String sotien, String ghichu,
                 String ngaygiaodich, String doituong, String diadiem, String thoihan, boolean trangthai) {
        this.sono_id = sono_id;
        this.vi_id = vi_id;
        this.loaino_id = loaino_id;
        this.sotien = sotien;
        this.ghichu = ghichu;
        this.ngaygiaodich = ngaygiaodich;
        this.doituong = doituong;
        this.diadiem = diadiem;
        this.thoihan = thoihan;
        this.trangthai = trangthai;
    }

    public _sono( int vi_id, int loaino_id, String sotien, String ghichu,
                 String ngaygiaodich, String doituong, String diadiem, String thoihan, boolean trangthai) {
        //this.sono_id = sono_id;
        this.vi_id = vi_id;
        this.loaino_id = loaino_id;
        this.sotien = sotien;
        this.ghichu = ghichu;
        this.ngaygiaodich = ngaygiaodich;
        this.doituong = doituong;
        this.diadiem = diadiem;
        this.thoihan = thoihan;
        this.trangthai = trangthai;
    }

    public String getDiadiem() {
        return diadiem;
    }

    public void setDiadiem(String diadiem) {
        this.diadiem = diadiem;
    }

    public String getDoituong() {
        return doituong;
    }

    public void setDoituong(String doituong) {
        this.doituong = doituong;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public int getLoaino_id() {
        return loaino_id;
    }

    public void setLoaino_id(int loaino_id) {
        this.loaino_id = loaino_id;
    }

    public String getNgaygiaodich() {
        return ngaygiaodich;
    }

    public void setNgaygiaodich(String ngaygiaodich) {
        this.ngaygiaodich = ngaygiaodich;
    }

    public int getSono_id() {
        return sono_id;
    }

    public void setSono_id(int sono_id) {
        this.sono_id = sono_id;
    }

    public String getSotien() {
        return sotien;
    }

    public void setSotien(String sotien) {
        this.sotien = sotien;
    }

    public String getThoihan() {
        return thoihan;
    }

    public void setThoihan(String thoihan) {
        this.thoihan = thoihan;
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
