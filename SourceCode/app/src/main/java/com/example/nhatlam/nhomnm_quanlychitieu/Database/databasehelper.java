package com.example.nhatlam.nhomnm_quanlychitieu.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.nhatlam.nhomnm_quanlychitieu.Models._category;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._donvitien;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._giaodich;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._loaino;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._loaivi;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._sono;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._sotietkiem;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._sukienchitieu;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._user;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._vi;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    public _user getUser(int id){
        _user user = new _user();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectUser = "select * from "+dbstring.TABLE_USER+" where "+dbstring.KEY_USER_ID+" = "+id;
        Cursor c =db.rawQuery(selectUser,null);
        if (c.moveToFirst()) {
            user.setUser_id(id);
            user.setUsername(c.getString(c.getColumnIndex(dbstring.KEY_USERNAME)));
            user.setSdt(c.getString(c.getColumnIndex(dbstring.KEY_SDT)));
            user.setPassword(c.getString(c.getColumnIndex(dbstring.KEY_PASSWORD)));
            user.setRemember(c.getInt(c.getColumnIndex(dbstring.KEY_REMEMBER)));
        }
        return user;
    }

    //===============================================================================================================



    //===============================USER TABLE==========================================================================
    //đăng ký
    public boolean dangkyUser(_user user){
        //mã hóa password
        user.setPassword(encryptMD5(user.getPassword()));
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(dbstring.KEY_USERNAME,user.getUsername());
        value.put(dbstring.KEY_PASSWORD,user.getPassword());
        value.put(dbstring.KEY_SDT,user.getSdt());
        value.put(dbstring.KEY_REMEMBER,user.getRemember());
        try {
            if(checkTrung(dbstring.TABLE_USER,dbstring.KEY_USERNAME,user.getUsername())==false) {
                db.insert(dbstring.TABLE_USER, null, value);
                return true;
            }
        }
        catch (Exception e){

        }
        return false;
    }


    //đăng nhập
    public _user dangnhapUser(_user user){
        SQLiteDatabase db = this.getReadableDatabase();

        //mã hóa md5
        user.setPassword(encryptMD5(user.getPassword()));


        String selectUser = "select * from "+dbstring.TABLE_USER+" where "+
                dbstring.KEY_USERNAME+"='"+user.getUsername()+"' and "+
                dbstring.KEY_PASSWORD+"='"+user.getPassword()+"'";

        Cursor c =db.rawQuery(selectUser,null);
        if (c.moveToFirst()){
            //set user if != null
            user.setUser_id(c.getInt(c.getColumnIndex(dbstring.KEY_USER_ID)));
            user.setSdt(c.getString(c.getColumnIndex(dbstring.KEY_SDT)));
            if(user.getRemember()==1)
                setRemember(user,1);
            user.setRemember(c.getInt(c.getColumnIndex(dbstring.KEY_REMEMBER)));
        } else
            return null;


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
            Log.e(null,e.toString());
        }

    }

    //auto đăng nhập
    public _user autodangnhapUser(){
        _user user = new _user();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectUserRemember="select * from "+dbstring.TABLE_USER+" where "+dbstring.KEY_REMEMBER+"=1";

        Cursor c =db.rawQuery(selectUserRemember,null);
        if(c.moveToFirst()){
            user.setUser_id(c.getInt(c.getColumnIndex(dbstring.KEY_USER_ID)));
            user.setUsername(c.getString(c.getColumnIndex(dbstring.KEY_USERNAME)));
            user.setPassword(c.getString(c.getColumnIndex(dbstring.KEY_PASSWORD)));
            user.setSdt(c.getString(c.getColumnIndex(dbstring.KEY_SDT)));
            user.setRemember(c.getInt(c.getColumnIndex(dbstring.KEY_REMEMBER)));
        } else
            return null;

        return user;
    }

    public _user dangxuatUser(_user user){
        setRemember(user,0);
        return null;
    }

    public List<_user> laydanhsachUser(){
        List<_user> lstuser = new ArrayList<_user>();
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "select * from "+dbstring.TABLE_USER;
        Cursor c = db.rawQuery(select,null);
        if(c.moveToFirst()){
            do{
                _user user = new _user();
                user.setUser_id(c.getInt(c.getColumnIndex(dbstring.KEY_USER_ID)));
                user.setRemember(c.getInt(c.getColumnIndex(dbstring.KEY_REMEMBER)));
                lstuser.add(user);
            }while(c.moveToNext());
        }
        return lstuser;
    }

    public Boolean chinhsuaThongTinUser(_user user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(dbstring.KEY_SDT,user.getSdt());
        value.put(dbstring.KEY_PASSWORD,user.getPassword());

        try{
            db.update(dbstring.TABLE_USER,value,dbstring.KEY_USER_ID+"="+user.getUser_id(),null);
            return true;
        } catch (Exception e){

        }
        return false;
    }

    public Boolean thaydoiPassword(_user user, String newpassword,String oldpassword){
        oldpassword=encryptMD5(oldpassword);
        if(user.getPassword().equals(oldpassword)==true){
            newpassword = encryptMD5(newpassword);
            user.setPassword(newpassword);
            return chinhsuaThongTinUser(user);
        }


        return false;
    }


    public void RemoveAllUser(){
        List<_user> lstuser=laydanhsachUser();
        SQLiteDatabase db =this.getWritableDatabase();

        for(int i=0;i<lstuser.size();i++) {
            try {
                db.delete(dbstring.TABLE_USER, dbstring.KEY_USER_ID + "=?",
                        new String[]{Integer.toString(lstuser.get(i).getUser_id())});
            } catch (Exception e) {

            }
        }
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
            if(checkTrung(dbstring.TABLE_LOAIVI,dbstring.KEY_LOAIVI_NAME,loaivi.getLoaivi_name())==false) {
                db.insert(dbstring.TABLE_LOAIVI, null, value);
                return true;
            }
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
            if(checkTrung(dbstring.TABLE_DONVITIEN,dbstring.KEY_DONVITIEN_NAME,donvitien.getDonvitien_name())==false) {
                db.insert(dbstring.TABLE_DONVITIEN, null, value);
                return true;
            }
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
            if(checkTrung(dbstring.TABLE_CATEGORY,dbstring.KEY_CATEGORY_NAME,category.getCategory_name())==false) {
                db.insert(dbstring.TABLE_CATEGORY, null, value);
                return true;
            }
        }catch (Exception e){
            Log.e(null,e.toString());
        }
        return false;
    }

    //lay danh sach category
    public List<_category> laydanhsachCategory(int parent){
        List<_category> lstCategory = new ArrayList<_category>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAllCategory = "select * from "+dbstring.TABLE_CATEGORY+" where "+dbstring.KEY_PARENT+"="+parent;
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
        try{
            db.update(dbstring.TABLE_CATEGORY,value,dbstring.KEY_CATEGORY_ID+"=?",
                    new String[]{Integer.toString(category.getCategory_id())});
            Log.d(null,"Edited");
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
    //them giao dich
    public boolean themGiaodich(_giaodich giaodich){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put(dbstring.KEY_VI_ID,giaodich.getVi_id());
        value.put(dbstring.KEY_CATEGORY_ID,giaodich.getCategory_id());
        value.put(dbstring.KEY_SOTIEN,giaodich.getSotien());
        value.put(dbstring.KEY_NGAYGIAODICH,giaodich.getNgaygiaodich());
        value.put(dbstring.KEY_GHICHU,giaodich.getGhichu());

        try{
            db.insert(dbstring.TABLE_GIAODICH,null,value);
            return true;
        }catch (Exception e){
            Log.e(null,e.toString());
        }

        return false;
    }


    //lay danh sach giaodich
    public List<_giaodich> laydanhsachGiaodich(_vi vi){
        List<_giaodich> lstGiaodich = new ArrayList<_giaodich>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAllGiaodich="select * from "+dbstring.TABLE_GIAODICH+" where "+dbstring.KEY_VI_ID+"="+vi.getVi_id();

        Cursor c = db.rawQuery(selectAllGiaodich,null);
        if(c.moveToFirst()){
            do{
                _giaodich giaodich = new _giaodich();
                giaodich.setGiaodich_id(c.getInt(c.getColumnIndex(dbstring.KEY_GIAODICH_ID)));
                giaodich.setVi_id(vi.getVi_id());
                giaodich.setCategory_id(c.getInt(c.getColumnIndex(dbstring.KEY_CATEGORY_ID)));
                giaodich.setSotien(c.getString(c.getColumnIndex(dbstring.KEY_SOTIEN)));
                giaodich.setNgaygiaodich(c.getString(c.getColumnIndex(dbstring.KEY_NGAYGIAODICH)));
                giaodich.setGhichu(c.getString(c.getColumnIndex(dbstring.KEY_GHICHU)));

                lstGiaodich.add(giaodich);
            }while(c.moveToNext());
        }

        return lstGiaodich;
    }


    //chinh sua giaodich
    public boolean chinhsuaGiaodich(_giaodich giaodich){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put(dbstring.KEY_VI_ID,giaodich.getVi_id());
        value.put(dbstring.KEY_CATEGORY_ID,giaodich.getCategory_id());
        value.put(dbstring.KEY_SOTIEN,giaodich.getSotien());
        value.put(dbstring.KEY_NGAYGIAODICH,giaodich.getNgaygiaodich());
        value.put(dbstring.KEY_GHICHU,giaodich.getGhichu());

        try{
            db.update(dbstring.TABLE_GIAODICH,value,dbstring.KEY_GIAODICH_ID+"=?",
                    new String[]{Integer.toString(giaodich.getGiaodich_id())});
            return true;
        }catch (Exception e){
            Log.e(null,e.toString());
        }

        return false;
    }


    //xoa giaodich
    public  boolean xoaGiaodich(_giaodich giaodich){
        SQLiteDatabase db= this.getWritableDatabase();

        try{
            db.delete(dbstring.TABLE_GIAODICH,dbstring.KEY_GIAODICH_ID+"=?",
                    new String[]{Integer.toString(giaodich.getGiaodich_id())});
            return true;
        }catch (Exception e){
            Log.e(null,e.toString());
        }

        return false;
    }

    //============================================================================================================
                                        //**********END*********


    //===============================================TABLE SUKIENCHITIEU==========================================

    //Them sukienchitieu
    public boolean themSukienchitieu(_sukienchitieu sukienchitieu){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put(dbstring.KEY_VI_ID,sukienchitieu.getVi_id());
        value.put(dbstring.KEY_CATEGORY_ID,sukienchitieu.getCategory_id());
        value.put(dbstring.KEY_SOTIEN,sukienchitieu.getSotien());
        value.put(dbstring.KEY_NGAYTHUCHIEN,sukienchitieu.getNgaythuchien());
        value.put(dbstring.KEY_TRANGTHAI,sukienchitieu.getTrangthai());
        value.put(dbstring.KEY_GHICHU,sukienchitieu.getGhichu());

        try {
            db.insert(dbstring.TABLE_SUKIENCHITIEU,null,value);
            return true;
        }catch (Exception e){
            Log.e(null,e.toString());
        }

        return false;
    }

    //lay danh sach sukienchitieu
    public List<_sukienchitieu> laydanhsachSukienchitieu(_vi vi){
        List<_sukienchitieu> lstSukienchitieu = new ArrayList<_sukienchitieu>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selectAllSukienchitieu = "select * from "+dbstring.TABLE_SUKIENCHITIEU+
                " where "+dbstring.KEY_VI_ID+"="+vi.getVi_id();

        Cursor c = db.rawQuery(selectAllSukienchitieu,null);

        if(c.moveToFirst()){
            do{
                _sukienchitieu sukienchitieu = new _sukienchitieu();
                sukienchitieu.setSukienchitieu_id(c.getInt(c.getColumnIndex(dbstring.KEY_SUKIENCHITIEU_ID)));
                sukienchitieu.setVi_id(vi.getVi_id());
                sukienchitieu.setCategory_id(c.getInt(c.getColumnIndex(dbstring.KEY_CATEGORY_ID)));
                sukienchitieu.setSotien(c.getString(c.getColumnIndex(dbstring.KEY_SOTIEN)));
                sukienchitieu.setNgaythuchien(c.getString(c.getColumnIndex(dbstring.KEY_NGAYTHUCHIEN)));
                sukienchitieu.setGhichu(c.getString(c.getColumnIndex(dbstring.KEY_GHICHU)));
                sukienchitieu.setTrangthai(c.getInt(c.getColumnIndex(dbstring.KEY_TRANGTHAI)));

                lstSukienchitieu.add(sukienchitieu);
            }while(c.moveToNext());
        }

        return lstSukienchitieu;
    }

    //chinh sua sukienchitieu
    public boolean chinhsuaSukienchitieu(_sukienchitieu sukienchitieu){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put(dbstring.KEY_VI_ID,sukienchitieu.getVi_id());
        value.put(dbstring.KEY_CATEGORY_ID,sukienchitieu.getCategory_id());
        value.put(dbstring.KEY_SOTIEN,sukienchitieu.getSotien());
        value.put(dbstring.KEY_NGAYTHUCHIEN,sukienchitieu.getNgaythuchien());
        value.put(dbstring.KEY_TRANGTHAI,sukienchitieu.getTrangthai());
        value.put(dbstring.KEY_GHICHU,sukienchitieu.getGhichu());

        try {
            db.update(dbstring.TABLE_SUKIENCHITIEU,value,dbstring.KEY_SUKIENCHITIEU_ID+"=?",
                    new String[]{Integer.toString(sukienchitieu.getSukienchitieu_id())});
            return true;
        }catch (Exception e){
            Log.e(null,e.toString());
        }

        return false;
    }


    //xoa sukienchitieu
    public boolean xoaSukienchitieu(_sukienchitieu sukienchitieu){
        SQLiteDatabase db= this.getWritableDatabase();

        try{
            db.delete(dbstring.TABLE_SUKIENCHITIEU,dbstring.KEY_SUKIENCHITIEU_ID+"=?",
                    new String[]{Integer.toString(sukienchitieu.getSukienchitieu_id())});
            return true;
        }catch (Exception e){
            Log.e(null,e.toString());
        }
        return false;
    }

    //============================================================================================================
                                        //**********END*********


    //=============================================TABLE SOTIETKIEM===============================================
    //them sotietkiem
    public boolean themSotietkiem(_sotietkiem sotietkiem){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put(dbstring.KEY_VI_ID,sotietkiem.getVi_id());
        value.put(dbstring.KEY_SOTIETKIEM_NAME,sotietkiem.getSotietkiem_name());
        value.put(dbstring.KEY_MUCTIEU,sotietkiem.getMuctieu());
        value.put(dbstring.KEY_SOTIENBANDAU,sotietkiem.getSotienbandau());
        value.put(dbstring.KEY_NGAYTAO,sotietkiem.getNgaytao());
        value.put(dbstring.KEY_GHICHU,sotietkiem.getGhichu());

        try{
            db.insert(dbstring.TABLE_SOTIETKIEM,null,value);
            return true;
        }catch (Exception e){
            Log.e(null,e.toString());
        }

        return false;
    }

    //lay danh sach sotietkiem
    public List<_sotietkiem> laydanhsachSotietkiem(_vi vi){
        List<_sotietkiem> lstSotietkiem = new ArrayList<_sotietkiem>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selectAllSotietkiem = "select * from "+dbstring.TABLE_SOTIETKIEM+" where "+dbstring.KEY_VI_ID+"="+vi.getVi_id();

        Cursor c = db.rawQuery(selectAllSotietkiem,null);

        if(c.moveToFirst()){
            do{
                _sotietkiem sotietkiem = new _sotietkiem();
                sotietkiem.setSotietkiem_id(c.getInt(c.getColumnIndex(dbstring.KEY_SOTIETKIEM_ID)));
                sotietkiem.setVi_id(vi.getVi_id());
                sotietkiem.setSotietkiem_name(c.getString(c.getColumnIndex(dbstring.KEY_SOTIETKIEM_NAME)));
                sotietkiem.setMuctieu(c.getString(c.getColumnIndex(dbstring.KEY_MUCTIEU)));
                sotietkiem.setSotienbandau(c.getString(c.getColumnIndex(dbstring.KEY_SOTIENBANDAU)));
                sotietkiem.setNgaytao(c.getString(c.getColumnIndex(dbstring.KEY_NGAYTAO)));
                sotietkiem.setGhichu(c.getString(c.getColumnIndex(dbstring.KEY_GHICHU)));

                lstSotietkiem.add(sotietkiem);
            }while(c.moveToNext());
        }

        return lstSotietkiem;
    }


    //chinh sua sotietkiem
    public boolean chinhsuaSotietkiem(_sotietkiem sotietkiem){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put(dbstring.KEY_VI_ID,sotietkiem.getVi_id());
        value.put(dbstring.KEY_SOTIETKIEM_NAME,sotietkiem.getSotietkiem_name());
        value.put(dbstring.KEY_MUCTIEU,sotietkiem.getMuctieu());
        value.put(dbstring.KEY_SOTIENBANDAU,sotietkiem.getSotienbandau());
        value.put(dbstring.KEY_NGAYTAO,sotietkiem.getNgaytao());
        value.put(dbstring.KEY_GHICHU,sotietkiem.getGhichu());

        try{
            db.update(dbstring.TABLE_SOTIETKIEM,value,dbstring.KEY_SOTIETKIEM_ID+"=?",
                    new String[]{Integer.toString(sotietkiem.getSotietkiem_id())});
            return true;
        }catch (Exception e){
            Log.e(null,e.toString());
        }

        return false;
    }

    //xoa sotietkiem
    public boolean xoaSotietkiem(_sotietkiem sotietkiem){
        SQLiteDatabase db = this.getWritableDatabase();

        try{
            db.delete(dbstring.TABLE_SOTIETKIEM,dbstring.KEY_SOTIETKIEM_ID+"=?",
                    new String[]{Integer.toString(sotietkiem.getSotietkiem_id())});
            return true;
        }catch (Exception e){
            Log.e(null,e.toString());
        }

        return false;
    }

    //============================================================================================================
                                        //**********END*********


    //=============================================TABLE SONO=====================================================

    //them sono
    public boolean themSono(_sono sono){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put(dbstring.KEY_VI_ID,sono.getVi_id());
        value.put(dbstring.KEY_LOAINO_ID,sono.getLoaino_id());
        value.put(dbstring.KEY_SOTIEN,sono.getSotien());
        value.put(dbstring.KEY_GHICHU,sono.getGhichu());
        value.put(dbstring.KEY_NGAYGIAODICH,sono.getNgaygiaodich());
        value.put(dbstring.KEY_DOITUONG,sono.getDoituong());
        value.put(dbstring.KEY_DIADIEM,sono.getDiadiem());
        value.put(dbstring.KEY_THOIHAN,sono.getThoihan());
        value.put(dbstring.KEY_TRANGTHAI,sono.getTrangthai());

        try{
            db.insert(dbstring.TABLE_SONO,null,value);
            return true;
        }catch (Exception e){
            Log.e(null,e.toString());
        }

        return false;
    }

    //lay danhsach sono
    public List<_sono> laydanhsachSono (_vi vi){
        List<_sono> lstSono= new ArrayList<_sono>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selectAllSono = "select * from "+dbstring.TABLE_SONO+" where "+dbstring.KEY_VI_ID+"="+vi.getVi_id();

        Cursor c = db.rawQuery(selectAllSono,null);

        if(c.moveToFirst()){
            do{
                _sono sono = new _sono();
                sono.setSono_id(c.getInt(c.getColumnIndex(dbstring.KEY_SONO_ID)));
                sono.setVi_id(vi.getVi_id());
                sono.setLoaino_id(c.getInt(c.getColumnIndex(dbstring.KEY_LOAINO_ID)));
                sono.setSotien(c.getString(c.getColumnIndex(dbstring.KEY_SOTIEN)));
                sono.setGhichu(c.getString(c.getColumnIndex(dbstring.KEY_GHICHU)));
                sono.setDoituong(c.getString(c.getColumnIndex(dbstring.KEY_DOITUONG)));
                sono.setDiadiem(c.getString(c.getColumnIndex(dbstring.KEY_DIADIEM)));
                sono.setNgaygiaodich(c.getString(c.getColumnIndex(dbstring.KEY_NGAYGIAODICH)));
                sono.setThoihan(c.getString(c.getColumnIndex(dbstring.KEY_THOIHAN)));
                sono.setTrangthai(c.getInt(c.getColumnIndex(dbstring.KEY_TRANGTHAI)));

                lstSono.add(sono);


            }while(c.moveToNext());
        }

        return  lstSono;
    }

    //chinh sua sono
    public boolean chinhsuaSono(_sono sono){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put(dbstring.KEY_VI_ID,sono.getVi_id());
        value.put(dbstring.KEY_LOAINO_ID,sono.getLoaino_id());
        value.put(dbstring.KEY_SOTIEN,sono.getSotien());
        value.put(dbstring.KEY_GHICHU,sono.getGhichu());
        value.put(dbstring.KEY_NGAYGIAODICH,sono.getNgaygiaodich());
        value.put(dbstring.KEY_DOITUONG,sono.getDoituong());
        value.put(dbstring.KEY_DIADIEM,sono.getDiadiem());
        value.put(dbstring.KEY_THOIHAN,sono.getThoihan());
        value.put(dbstring.KEY_TRANGTHAI,sono.getTrangthai());

        try{
            db.update(dbstring.TABLE_SONO,value,dbstring.KEY_SONO_ID+"=?",
                    new String[]{Integer.toString(sono.getSono_id())});
            return true;
        }catch (Exception e){
            Log.e(null,e.toString());
        }

        return  false;
    }

    //xoa sono
    public  boolean xoaSono(_sono sono){
        SQLiteDatabase db = this.getWritableDatabase();

        try{
            db.delete(dbstring.TABLE_SONO,dbstring.KEY_SONO_ID+"=?",
                    new String[]{Integer.toString(sono.getSono_id())});
            return true;
        }catch (Exception e){
            Log.e(null,e.toString());
        }

        return  false;
    }

    //============================================================================================================
                                        //**********END*********


    //=============================================TABLE LOAINO===================================================

    //Them loaino
    public boolean themLoaino (_loaino loaino){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put(dbstring.KEY_LOAINO_NAME,loaino.getLoaino_name());

        try{
            if(checkTrung(dbstring.TABLE_LOAINO,dbstring.KEY_LOAINO_NAME,loaino.getLoaino_name())==false) {
                db.insert(dbstring.TABLE_LOAINO, null, value);
                return true;
            }
        }catch (Exception e){
            Log.e(null,e.toString());
        }


        return false;
    }

    //lay danh sach loaino
    public List<_loaino> laydanhsachLoaino (){
        List<_loaino> lstLoaino = new ArrayList<_loaino>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selectAllLoaino = "select * from "+dbstring.TABLE_LOAINO;

        Cursor c = db.rawQuery(selectAllLoaino,null);

        if(c.moveToFirst()){
            do{
                _loaino loaino = new _loaino();

                loaino.setLoaino_id(c.getInt(c.getColumnIndex(dbstring.KEY_LOAINO_ID)));
                loaino.setLoaino_name(c.getString(c.getColumnIndex(dbstring.KEY_LOAINO_NAME)));

                lstLoaino.add(loaino);

            }while(c.moveToNext());
        }

        return lstLoaino;
    }

    //chinh sua loaino
    public boolean chinhsuaLoaino(_loaino loaino){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put(dbstring.KEY_LOAINO_NAME,loaino.getLoaino_name());

        try{
            db.update(dbstring.TABLE_LOAINO,value,dbstring.KEY_LOAINO_ID+"=?",
                    new String[]{Integer.toString(loaino.getLoaino_id())});
            return true;
        }catch (Exception e){
            Log.e(null,e.toString());
        }


        return false;
    }

    //Xoa loaino
    public boolean xoaLoaino (_loaino loaino){
        SQLiteDatabase db = this.getWritableDatabase();

        try{
            db.delete(dbstring.TABLE_LOAINO,dbstring.KEY_LOAINO_ID+"=?",
                    new String[]{Integer.toString(loaino.getLoaino_id())});
            return true;
        }catch (Exception e){
            Log.e(null,e.toString());
        }
        return false;
    }

    //============================================================================================================
                                        //**********END*********


    public boolean checkTrung(String table, String keyid, String value){
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "select * from "+table+" where "+keyid+"='"+value+"'";
        Cursor c = db.rawQuery(select,null);
        if(c.getCount()>0){
            return true;
        }

        return false;
    }


    public static String encryptMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
