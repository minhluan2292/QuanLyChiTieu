package com.example.nhatlam.nhomnm_quanlychitieu.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.nhatlam.nhomnm_quanlychitieu.Database.databasehelper;
import com.example.nhatlam.nhomnm_quanlychitieu.Database.dbstring;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._sukienchitieu;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._category;
/**
 * Created by MinhLuan on 2/5/2017.
 */

public class AddSuKienChiTieuDuKienTask extends AsyncTask<Void, Void, Boolean> {

    private Context context;
    private String tablename;
    private int vi_id;
    private int category_id;
    private String sotien;
    private String ngaysukienchitieu;
    private String ghichu;
    private int trangthai;

   /* public AddSuKienChiTieuDuKienTask(Context context, String tablename, int vi_id, int category_id, String sotien, String ngaysukienchitieu, String ghichu) {
        this.context = context;
        this.tablename = tablename;
        this.vi_id = vi_id;
        this.category_id = category_id;
        this.sotien = sotien;
        this.ngaysukienchitieu = ngaysukienchitieu;
        this.ghichu = ghichu;
    }*/

    public AddSuKienChiTieuDuKienTask(Context context, String tablename, int vi_id, int category_id, String sotien, String ngaysukienchitieu, String ghichu, int trangthai) {
        this.context = context;
        this.tablename = tablename;
        this.vi_id = vi_id;
        this.category_id = category_id;
        this.sotien = sotien;
        this.ngaysukienchitieu = ngaysukienchitieu;
        this.ghichu = ghichu;
        this.trangthai = trangthai;
    }

    @Override
    protected Boolean doInBackground(Void... params) {

        databasehelper db= new databasehelper(context);

        _sukienchitieu sukienchitieu = new _sukienchitieu();
        sukienchitieu.setVi_id(vi_id);
        sukienchitieu.setCategory_id(category_id);
        sukienchitieu.setSotien(sotien);
        sukienchitieu.setNgaythuchien(ngaysukienchitieu);
        sukienchitieu.setGhichu(ghichu);
        sukienchitieu.setTrangthai(trangthai);

        return db.themSukienchitieu(sukienchitieu);

    }

    @Override
    protected void onPostExecute(Boolean b) {
        super.onPostExecute(b);
        if(b==true)
            Toast.makeText(context,"Thêm thành công!",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context,"Thêm thất bại!",Toast.LENGTH_SHORT).show();
    }
}