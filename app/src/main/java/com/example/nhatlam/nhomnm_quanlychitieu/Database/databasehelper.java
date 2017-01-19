package com.example.nhatlam.nhomnm_quanlychitieu.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.nhatlam.nhomnm_quanlychitieu.Models._user;

/**
 * Created by NHATLAM on 1/18/2017.
 */

public class databasehelper extends SQLiteOpenHelper {


    // Logcat tag
    private static final String LOG = databasehelper.class.getName();

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "dbquanlychitieu";

    public databasehelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(dbstring.CREATE_TABLE_USER);
        db.execSQL(dbstring.CREATE_TABLE_VI);
        db.execSQL(dbstring.CREATE_TABlE_LOAIVI);
        db.execSQL(dbstring.CREATE_TABLE_DONVITIEN);
        db.execSQL(dbstring.CREATE_TABLE_CATEGORY);
        db.execSQL(dbstring.CREATE_TABLE_GIAODICH);
        db.execSQL(dbstring.CREATE_TABLE_SUKIENCHITIEU);
        db.execSQL(dbstring.CREATE_TABLE_SOTIETKIEM);
        db.execSQL(dbstring.CREATE_TABLE_SONO);
        db.execSQL(dbstring.CREATE_TABLE_LOAINO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //
    }

    public boolean insertUser(){
        _user user = new _user();
        SQLiteDatabase db= this.getWritableDatabase();
        user.setUsername("nhatlam");
        user.setPassword("123456");
        user.setSdt("123456");
        user.setRemember(1);
        ContentValues value = new ContentValues();
        value.put(dbstring.KEY_USERNAME,user.getUsername());
        value.put(dbstring.KEY_PASSWORD,user.getPassword());
        value.put(dbstring.KEY_SDT,user.getSdt());
        value.put(dbstring.KEY_REMEMBER,user.getRemember());
        try {
            db.insert(dbstring.TABLE_USER, null, value);
            return true;
        }
        catch (Exception e){

        }
        return false;
    }

    public _user getUser(){
        _user user = new _user();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectUser = "select * from "+dbstring.TABLE_USER+" where "+dbstring.KEY_USER_ID+" = 1";
        Cursor c =db.rawQuery(selectUser,null);
        if (c != null)
            c.moveToFirst();

        user.setUsername(c.getString(c.getColumnIndex(dbstring.KEY_USERNAME)));
        return user;
    }
}
