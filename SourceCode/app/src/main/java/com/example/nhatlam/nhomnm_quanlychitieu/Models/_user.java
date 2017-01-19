package com.example.nhatlam.nhomnm_quanlychitieu.Models;

/**
 * Created by NHATLAM on 1/18/2017.
 */

public class _user {
    private int user_id;
    private String username;
    private String password;
    private String sdt;
    private int remember;

    public int getRemember() {
        return remember;
    }

    public _user() {
        //
    }

    public _user(int user_id, String username, String password, String sdt, int remember   ) {
        this.password = password;
        this.remember = remember;
        this.sdt = sdt;
        this.user_id = user_id;
        this.username = username;
    }

    public _user(String username, String password,String sdt, int remember ) {
        this.password = password;
        this.remember = remember;
        this.sdt = sdt;
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int isRemember() {
        return remember;
    }

    public void setRemember(int remember) {
        this.remember = remember;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
