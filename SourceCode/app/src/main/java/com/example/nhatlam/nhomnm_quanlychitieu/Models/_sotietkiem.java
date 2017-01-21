package com.example.nhatlam.nhomnm_quanlychitieu.Models;

/**
 * Created by NHATLAM on 1/18/2017.
 */

public class _sotietkiem {
    private int sotietkiem_id;
    private int vi_id;
    private String sotietkiem_name;
    private String muctieu;
    private String sotienbandau;
    private String ngaytao;
    private String ghichu;

    public _sotietkiem() {

    }

    public _sotietkiem(int sotietkiem_id, int vi_id, String sotietkiem_name,
                       String muctieu, String sotienbandau, String ngaytao, String ghichu) {
        this.sotietkiem_id = sotietkiem_id;
        this.vi_id = vi_id;
        this.sotietkiem_name = sotietkiem_name;
        this.muctieu = muctieu;
        this.sotienbandau = sotienbandau;
        this.ngaytao = ngaytao;
        this.ghichu = ghichu;
    }

    public _sotietkiem( int vi_id, String sotietkiem_name,
                       String muctieu, String sotienbandau, String ngaytao, String ghichu) {
        //this.sotietkiem_id = sotietkiem_id;
        this.vi_id = vi_id;
        this.sotietkiem_name = sotietkiem_name;
        this.muctieu = muctieu;
        this.sotienbandau = sotienbandau;
        this.ngaytao = ngaytao;
        this.ghichu = ghichu;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public String getMuctieu() {
        return muctieu;
    }

    public void setMuctieu(String muctieu) {
        this.muctieu = muctieu;
    }

    public String getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(String ngaytao) {
        this.ngaytao = ngaytao;
    }

    public String getSotienbandau() {
        return sotienbandau;
    }

    public void setSotienbandau(String sotienbandau) {
        this.sotienbandau = sotienbandau;
    }

    public int getSotietkiem_id() {
        return sotietkiem_id;
    }

    public void setSotietkiem_id(int sotietkiem_id) {
        this.sotietkiem_id = sotietkiem_id;
    }

    public String getSotietkiem_name() {
        return sotietkiem_name;
    }

    public void setSotietkiem_name(String sotietkiem_name) {
        this.sotietkiem_name = sotietkiem_name;
    }

    public int getVi_id() {
        return vi_id;
    }

    public void setVi_id(int vi_id) {
        this.vi_id = vi_id;
    }
}
