package com.example.nhatlam.nhomnm_quanlychitieu.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.nhatlam.nhomnm_quanlychitieu.Database.databasehelper;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._giaodich;
/**
 * Created by MinhLuan on 2/5/2017.
 */

public class AddSuKienChiTieuTask extends AsyncTask<Void, Void, Boolean> {

    private Context context;
    private String tablename;
    private int vi_id;
    private int category_id;
    private String sotien;
    private String ngaygiaodich;
    private String ghichu;

    public AddSuKienChiTieuTask(Context context, String tablename, int vi_id, int category_id, String sotien, String ngaygiaodich, String ghichu) {
        this.context = context;
        this.tablename = tablename;
        this.vi_id = vi_id;
        this.category_id = category_id;
        this.sotien = sotien;
        this.ngaygiaodich = ngaygiaodich;
        this.ghichu = ghichu;
    }


    @Override
    protected Boolean doInBackground(Void... params) {

        databasehelper db= new databasehelper(context);

        _giaodich giaodich = new _giaodich();
        giaodich.setVi_id(vi_id);
        giaodich.setCategory_id(category_id);
        giaodich.setSotien(sotien);
        giaodich.setNgaygiaodich(ngaygiaodich);
        giaodich.setGhichu(ghichu);
        Log.d(null,giaodich.getNgaygiaodich());

        return db.themGiaodich(giaodich);

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