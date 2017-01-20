package com.example.nhatlam.nhomnm_quanlychitieu.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.nhatlam.nhomnm_quanlychitieu.Models._category;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._donvitien;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._loaivi;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._user;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._vi;

import java.util.ArrayList;
import java.util.List;

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


    //Test user===========================================================================
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

    //===============================================================================================================



    //===============================USER TABLE==========================================================================
    //đăng ký
    public boolean dangkyUser(_user user){
        SQLiteDatabase db= this.getWritableDatabase();
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


    //đăng nhập
    public _user dangnhapUser(_user user,int remember){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectUser = "select * from "+dbstring.TABLE_USER+" where "+
                dbstring.KEY_USERNAME+"='"+user.getUsername()+"' and "+
                dbstring.KEY_PASSWORD+"='"+user.getPassword()+"'";

        Cursor c =db.rawQuery(selectUser,null);
        if (c != null)
            c.moveToFirst();
        else
            return null;

        //set user if != null
        user.setUser_id(c.getInt(c.getColumnIndex(dbstring.KEY_USER_ID)));
        user.setSdt(c.getString(c.getColumnIndex(dbstring.KEY_SDT)));
        if(remember==1)
            setRemember(user,1);
        user.setRemember(c.getInt(c.getColumnIndex(dbstring.KEY_REMEMBER)));
        return user;
    }

    //set remember
    public void setRemember(_user user, int remember){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(dbstring.KEY_REMEMBER,remember);
        try {
            db.update(dbstring.TABLE_USER,value,dbstring.KEY_USER_ID+"="+user.getUser_id(),null);
        } catch (Exception e){

        }

    }

    //auto đăng nhập
    public _user autodangnhapUser(){
        _user user = new _user();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectUserRemember="select * from "+dbstring.TABLE_USER+" where "+dbstring.KEY_REMEMBER+"=1";

        Cursor c =db.rawQuery(selectUserRemember,null);
        if(c!=null)
            c.moveToFirst();
        else
            return null;

        user.setUser_id(c.getInt(c.getColumnIndex(dbstring.KEY_USER_ID)));
        user.setUsername(c.getString(c.getColumnIndex(dbstring.KEY_USERNAME)));
        user.setPassword(c.getString(c.getColumnIndex(dbstring.KEY_PASSWORD)));
        user.setSdt(c.getString(c.getColumnIndex(dbstring.KEY_SDT)));
        user.setRemember(c.getInt(c.getColumnIndex(dbstring.KEY_REMEMBER)));

        return user;
    }

    //============================================================================================================
                                         //**********END*********

    //===========================================TABLE VÍ=========================================================

    //them vi moi
    public boolean themVi(_vi vi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value= new ContentValues();
        value.put(dbstring.KEY_VI_NAME,vi.getVi_name());
        value.put(dbstring.KEY_USER_ID,vi.getUser_id());
        value.put(dbstring.KEY_LOAIVI_ID,vi.getLoaivi_id());
        value.put(dbstring.KEY_DONVITIEN_ID,vi.getDonvitien());
        value.put(dbstring.KEY_SOTIEN,vi.getSotien());
        try {
            db.insert(dbstring.TABLE_VI,null,value);
            return true;
        }catch (Exception e){

        }
        return false;
    }

    //lay danh sach vi
    public List<_vi> laydanhsachVi(_user user){
        List<_vi> lstVi = new ArrayList<_vi>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectVi="select * from "+dbstring.TABLE_VI+" where "+dbstring.KEY_USER_ID+"="+user.getUser_id();
        Cursor c = db.rawQuery(selectVi,null);
        if(c.moveToFirst()){
            do{
                _vi vi = new _vi();
                vi.setVi_id(c.getInt(c.getColumnIndex(dbstring.KEY_VI_ID)));
                vi.setVi_name(c.getString(c.getColumnIndex(dbstring.KEY_VI_NAME)));
                vi.setUser_id(c.getInt(c.getColumnIndex(dbstring.KEY_USER_ID)));
                vi.setLoaivi_id(c.getInt(c.getColumnIndex(dbstring.KEY_LOAIVI_ID)));
                vi.setSotien(c.getString(c.getColumnIndex(dbstring.KEY_SOTIEN)));
                vi.setDonvitien(c.getInt(c.getColumnIndex(dbstring.KEY_DONVITIEN_ID)));
                lstVi.add(vi);
            }while(c.moveToNext());
        }

        return lstVi;
    }

    //chinh sua vi
    public boolean chinhsuaVi(_vi vi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(dbstring.KEY_VI_NAME,vi.getVi_name());
        value.put(dbstring.KEY_USER_ID,vi.getUser_id());
        value.put(dbstring.KEY_LOAIVI_ID,vi.getLoaivi_id());
        value.put(dbstring.KEY_DONVITIEN_ID,vi.getDonvitien());
        value.put(dbstring.KEY_SOTIEN,vi.getSotien());

        try{
            db.update(dbstring.TABLE_VI,value,dbstring.KEY_VI_ID+"="+vi.getVi_id(),null);
            return true;
        } catch (Exception e){

        }


        return false;
    }

    //Xoa vi
    public boolean xoaVi(_vi vi){
        SQLiteDatabase db= this.getWritableDatabase();
        try {
            db.delete(dbstring.TABLE_VI, dbstring.KEY_VI_ID + "=?", new String[]{Integer.toString(vi.getVi_id())});
            return true;
        }catch (Exception e){

        }
        return false;
    }

    //============================================================================================================
                                        //**********END*********


    //==========================================TABLE LOAI VI=====================================================
    //them loai vi
    public boolean themLoaiVi(_loaivi loaivi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(dbstring.KEY_LOAIVI_NAME,loaivi.getLoaivi_name());

        try{
            db.insert(dbstring.TABLE_LOAIVI,null,value);
            return true;
        }catch (Exception e){

        }

        return false;
    }

    //lay danh sach loaivi
    public List<_loaivi> laydanhsachLoaiVi(){
        List<_loaivi> lstLoaiVi = new ArrayList<_loaivi>();
        String selectAllLoaiVi= "select * from "+dbstring.TABLE_LOAIVI;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery(selectAllLoaiVi,null);
        if(c.moveToFirst()){
            do{
                _loaivi loaivi = new _loaivi();
                loaivi.setLoaivi_id(c.getInt(c.getColumnIndex(dbstring.KEY_LOAIVI_ID)));
                loaivi.setLoaivi_name(c.getString(c.getColumnIndex(dbstring.KEY_LOAIVI_NAME)));
                lstLoaiVi.add(loaivi);
            }while(c.moveToNext());
        }

        return lstLoaiVi;
    }

    //chinh sua loaivi
    public boolean chinhsuaLoaiVi(_loaivi loaivi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(dbstring.KEY_LOAIVI_NAME,loaivi.getLoaivi_name());
        try{
            db.update(dbstring.TABLE_LOAIVI,value,dbstring.KEY_LOAIVI_ID+"=?",
                    new String[]{Integer.toString(loaivi.getLoaivi_id())});
            return true;
        }catch (Exception e){

        }
        return false;
    }

    //xoa loaivi
    public boolean xoaLoaiVi(_loaivi loaivi){
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            db.delete(dbstring.TABLE_LOAIVI,dbstring.KEY_LOAIVI_ID+"=?",
                    new String[]{Integer.toString(loaivi.getLoaivi_id())});
            return true;
        }catch (Exception e){}
        return false;
    }

    //============================================================================================================
                                        //**********END*********



    //===========================================TABLE DONVITIEN==================================================

    //Them donvitien
    public boolean themDonvitien(_donvitien donvitien){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(dbstring.KEY_DONVITIEN_NAME,donvitien.getDonvitien_name());
        value.put(dbstring.KEY_TIGIADOLA,donvitien.getTigiadola());

        try{
            db.insert(dbstring.TABLE_DONVITIEN,null,value);
            return true;
        }catch(Exception e){}
        return false;
    }

    //lay danh sach donvitien
    public List<_donvitien> laydanhsachDonvitien(){
        List<_donvitien> lstDonvitien = new ArrayList<_donvitien>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAllDonvitien = "select * from "+dbstring.TABLE_DONVITIEN;

        Cursor c = db.rawQuery(selectAllDonvitien,null);

        if(c.moveToFirst()){
            do{
                _donvitien donvitien = new _donvitien();
                donvitien.setDonvitien_id(c.getInt(c.getColumnIndex(dbstring.KEY_DONVITIEN_ID)));
                donvitien.setDonvitien_name(c.getString(c.getColumnIndex(dbstring.KEY_DONVITIEN_NAME)));
                donvitien.setTigiadola(c.getString(c.getColumnIndex(dbstring.KEY_TIGIADOLA)));
                lstDonvitien.add(donvitien);
            }while(c.moveToNext());
        }

        return lstDonvitien;
    }

    //chinh sua donvitien
    public boolean chinhsuaDonvitien(_donvitien donvitien){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(dbstring.KEY_DONVITIEN_NAME,donvitien.getDonvitien_name());
        value.put(dbstring.KEY_TIGIADOLA,donvitien.getTigiadola());

        try{
            db.update(dbstring.TABLE_DONVITIEN,value,dbstring.KEY_DONVITIEN_ID+"=?",
                    new String[]{Integer.toString(donvitien.getDonvitien_id())});
            return true;
        }catch(Exception e){
            Log.e(null,e.toString());
        }
        return false;
    }

    //Xoa donvitien
    public boolean xoaDonvitien(_donvitien donvitien){
        SQLiteDatabase db= this.getWritableDatabase();
        try{
            db.delete(dbstring.TABLE_DONVITIEN,dbstring.KEY_DONVITIEN_ID+"=?",
                    new String[]{Integer.toString(donvitien.getDonvitien_id())});
            return true;
        }catch (Exception e){
            Log.e(null,e.toString());
        }
        return false;
    }

    //============================================================================================================
                                        //**********END*********


    //============================================TABLE CATEGORY==================================================

    //Them category
    public boolean themCategory(_category category){
        SQLiteDatabase db=  this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(dbstring.KEY_CATEGORY_NAME,category.getCategory_name());
        value.put(dbstring.KEY_PARENT,category.getParent());
        try{
            db.insert(dbstring.TABLE_CATEGORY,null,value);
            return true;
        }catch (Exception e){
            Log.e(null,e.toString());
        }
        return false;
    }

    //lay danh sach category
    public List<_category> laydanhsachCategory(){
        List<_category> lstCategory = new ArrayList<_category>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAllCategory = "select * from "+dbstring.TABLE_CATEGORY;
        Cursor c = db.rawQuery(selectAllCategory,null);

        if(c.moveToFirst()){
            do{
                _category category= new _category();
                category.setCategory_id(c.getInt(c.getColumnIndex(dbstring.KEY_CATEGORY_ID)));
                category.setCategory_name(c.getString(c.getColumnIndex(dbstring.KEY_CATEGORY_NAME)));
                category.setParent(c.getInt(c.getColumnIndex(dbstring.KEY_PARENT)));
                lstCategory.add(category);
            }while(c.moveToNext());
        }
        return lstCategory;
    }

    //chinh sua category
    public boolean chinhsuaCategory(_category category){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(dbstring.KEY_CATEGORY_NAME,category.getCategory_name());
        if(category.getParent()>0)
            value.put(dbstring.KEY_PARENT,category.getParent());
        else
            value.put(dbstring.KEY_PARENT,"");

        try{
            db.update(dbstring.TABLE_CATEGORY,value,dbstring.KEY_CATEGORY_ID+"=?",
                    new String[]{Integer.toString(category.getCategory_id())});
            return true;
        }catch (Exception e){
            Log.e(null,e.toString());
        }

        return false;
    }

    //xoa category
    public boolean xoaCategory(_category category){
        SQLiteDatabase db = this.getWritableDatabase();

        try{
            db.delete(dbstring.TABLE_CATEGORY,dbstring.KEY_CATEGORY_ID+"=?",
                    new String[]{Integer.toString(category.getCategory_id())});
            return true;
        }catch (Exception e){
            Log.e(null,e.toString());
        }

        return false;
    }

    //============================================================================================================
                                        //**********END*********


    //==============================================TABLE GIAODICH================================================

    //============================================================================================================
                                        //**********END*********


    //===============================================TABLE SUKIENCHITIEU==========================================

    //============================================================================================================
                                        //**********END*********


    //=============================================TABLE SOTIETKIEM===============================================

    //============================================================================================================
                                        //**********END*********


    //=============================================TABLE SONO=====================================================
    //============================================================================================================
                                        //**********END*********


    //=============================================TABLE LOAINO===================================================

    //============================================================================================================
                                        //**********END*********

}
