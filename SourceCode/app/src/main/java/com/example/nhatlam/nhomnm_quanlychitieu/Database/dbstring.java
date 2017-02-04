package com.example.nhatlam.nhomnm_quanlychitieu.Database;

/**
 * Created by NHATLAM on 1/18/2017.
 */

public class dbstring {

    //table name
    public static final String TABLE_USER="user";
    public static final String TABLE_VI="vi";
    public static final String TABLE_LOAIVI="loaivi";
    public static final String TABLE_CATEGORY="category";
    public static final String TABLE_DONVITIEN="donvitien";
    public static final String TABLE_GIAODICH="giaodich";
    public static final String TABLE_SUKIENCHITIEU="sukienchitieu";
    public static final String TABLE_SOTIETKIEM="sotietkiem";
    public static final String TABLE_SONO="sono";
    public static final String TABLE_LOAINO="loaino";

    //=================================table column============================================

    //user
    public static final String KEY_USER_ID="user_id";
    public static final String KEY_USERNAME="username";
    public static final String KEY_PASSWORD="password";
    public static final String KEY_SDT="sdt";
    public static final String KEY_REMEMBER="remember";

    //vi
    public static final String KEY_VI_ID="vi_id";
    public static final String KEY_VI_NAME="vi_name";
    //public static final String KEY_LOAIVI_ID="loaivi_id";
    public static final String KEY_SOTIEN="sotien";
    //public static final String KEY_DONVITIEN="donvitien_id";

    //loaivi
    public static final String KEY_LOAIVI_ID="loaivi_id";
    public static final String KEY_LOAIVI_NAME="loaivi_name";

    //donvitien
    public static final String KEY_DONVITIEN_ID="donvitien_id";
    public static final String KEY_DONVITIEN_NAME="donvitien_name";
    public static final String KEY_TIGIADOLA="tigiadola";

    //category
    public static final String KEY_CATEGORY_ID="category_id";
    public static final String KEY_CATEGORY_NAME="category_name";
    public static final String KEY_PARENT="parent";

    //giaodich
    public static final String KEY_GIAODICH_ID="giaodich";
    //public static final String KEY_SOTIEN="sotien";
    public static final String KEY_NGAYGIAODICH="ngaygiaodich";
    public static final String KEY_GHICHU="ghichu";

    //sukienchitieu
    public static final String KEY_SUKIENCHITIEU_ID="sukienchitieu";
    // public static final String KEY_SOTIEN="sotien";
    public static final String KEY_NGAYTHUCHIEN="ngaythuchien";
    public static final String KEY_TRANGTHAI="trangthai";

    //sotietkiem
    public static final String KEY_SOTIETKIEM_ID="sotietkiem_id";
    public static final String KEY_SOTIETKIEM_NAME="sotietkiem_name";
    public static final String KEY_MUCTIEU="muctieu";
    public static final String KEY_SOTIENBANDAU="sotienbandau";
    public static final String KEY_NGAYTAO="ngaytao";

    //sono
    public static final String KEY_SONO_ID="sono_id";
    public static final String KEY_LOAINO="loaino_id";
    public static final String KEY_DOITUONG="doituong";
    public static final String KEY_DIADIEM="diadiem";
    public static final String KEY_THOIHAN="thoihan";

    //loaino
    public static final String KEY_LOAINO_ID="loaino_id";
    public static final String KEY_LOAINO_NAME="loaino_name";

    //=========================create table==================================
    public static final String CREATE_TABLE_USER="CREATE TABLE "+TABLE_USER+
            "("+
            KEY_USER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            KEY_USERNAME+" TEXT,"+
            KEY_PASSWORD+" TEXT,"+
            KEY_SDT+" TEXT,"+
            KEY_REMEMBER+" INTEGER"+
            ")";

    public static final String CREATE_TABLE_VI="CREATE TABLE "+ TABLE_VI+
            "("+
            KEY_VI_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            KEY_USER_ID+ " INTEGER,"+
            KEY_VI_NAME+" TEXT,"+
            KEY_LOAIVI_ID+" INTEGER,"+
            KEY_SOTIEN+" TEXT,"+
            KEY_DONVITIEN_ID+" INTEGER"+
           // "FOREIGN KEY ("+KEY_USER_ID+") REFERENCES "+TABLE_USER+"("+KEY_USER_ID+"),"+
           // "FOREIGN KEY ("+KEY_LOAIVI_ID+") REFERENCES "+TABLE_LOAIVI+"("+KEY_LOAIVI_ID+"),"+
           // "FOREIGN KEY ("+KEY_DONVITIEN_ID+") REFERENCES "+TABLE_DONVITIEN+"("+KEY_DONVITIEN_ID+")"+
            ")";

    public static final String CREATE_TABlE_LOAIVI="CREATE TABLE "+TABLE_LOAIVI+
            "("+
            KEY_LOAIVI_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            KEY_LOAIVI_NAME+" TEXT"+
            ")";

    public static final String CREATE_TABLE_DONVITIEN="CREATE TABLE "+TABLE_DONVITIEN+
            "("+
            KEY_DONVITIEN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            KEY_DONVITIEN_NAME+" TEXT"+
            ")";

    public static final String CREATE_TABLE_CATEGORY="CREATE TABLE "+TABLE_CATEGORY+
            "("+
            KEY_CATEGORY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            KEY_CATEGORY_NAME+" TEXT,"+
            KEY_PARENT+" INTEGER"+
            ")";

    public static final String CREATE_TABLE_GIAODICH="CREATE TABLE "+TABLE_GIAODICH+
            "("+
            KEY_GIAODICH_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+
            KEY_VI_ID+" INTEGER,"+
            KEY_CATEGORY_ID+" INTEGER,"+
            KEY_SOTIEN+" TEXT,"+
            KEY_NGAYGIAODICH+" TEXT,"+
            KEY_GHICHU+" TEXT"+
            ")";

    public static final String CREATE_TABLE_SUKIENCHITIEU="CREATE TABLE "+TABLE_SUKIENCHITIEU+
            "("+
            KEY_SUKIENCHITIEU_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            KEY_VI_ID+" INTEGER,"+
            KEY_CATEGORY_ID+" INTEGER,"+
            KEY_SOTIEN+" TEXT,"+
            KEY_NGAYTHUCHIEN+" TEXT,"+
            KEY_TRANGTHAI+" INTEGER,"+
            KEY_GHICHU+" TEXT"+
            ")";

    public static final String CREATE_TABLE_SOTIETKIEM="CREATE TABLE "+TABLE_SOTIETKIEM+
            "("+
            KEY_SOTIETKIEM_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            KEY_VI_ID+" INTEGER,"+
            KEY_SOTIETKIEM_NAME+" TEXT,"+
            KEY_MUCTIEU+" TEXT,"+
            KEY_SOTIENBANDAU+" TEXT,"+
            KEY_NGAYTAO+" TEXT,"+
            KEY_GHICHU+" TEXT"+
            ")";

    public static final String CREATE_TABLE_SONO="CREATE TABLE "+TABLE_SONO+
            "("+
            KEY_SONO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            KEY_VI_ID+" INTEGER,"+
            KEY_LOAINO_ID+" INTEGER,"+
            KEY_SOTIEN+" TEXT,"+
            KEY_GHICHU+" TEXT,"+
            KEY_NGAYGIAODICH+" TEXT,"+
            KEY_DOITUONG+" TEXT,"+
            KEY_DIADIEM+ " TEXT,"+
            KEY_THOIHAN+" TEXT,"+
            KEY_TRANGTHAI+" INTEGER"+
            ")";

    public static final String CREATE_TABLE_LOAINO="CREATE TABLE "+TABLE_LOAINO+
            "("+
            KEY_LOAINO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            KEY_LOAINO_NAME+" TEXT"+
            ")";
}
