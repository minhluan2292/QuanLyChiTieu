package com.example.nhatlam.nhomnm_quanlychitieu.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.nhatlam.nhomnm_quanlychitieu.Database.databasehelper;
import com.example.nhatlam.nhomnm_quanlychitieu.Database.dbstring;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._loaivi;

/**
 * Created by MinhLuan on 2/4/2017.
 */

public class AddLoaiViTask extends AsyncTask<Void, Void, Boolean> {

    private Context context;
    private String tablename;
    private String value;
    private int index;

    public AddLoaiViTask(Context context, String tablename, String value) {
        this.context=context;
        this.tablename = tablename;
        this.value=value;
    }

    @Override
    protected Boolean doInBackground(Void... params) {

        databasehelper db= new databasehelper(context);

        _loaivi loaivi= new _loaivi();
        loaivi.setLoaivi_name(value);
        return db.themLoaiVi(loaivi);

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