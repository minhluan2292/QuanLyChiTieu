package com.example.nhatlam.nhomnm_quanlychitieu.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.nhatlam.nhomnm_quanlychitieu.Database.databasehelper;
import com.example.nhatlam.nhomnm_quanlychitieu.Database.dbstring;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._category;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._loaino;

/**
 * Created by NHATLAM on 2/3/2017.
 */

public class AddCatagoryTask extends AsyncTask<Void, Void, Boolean> {

    private Context context;
    private String tablename;
    private String value;
    private int index;

    public AddCatagoryTask(Context context, String tablename, String value, int index) {
        this.context=context;
        this.tablename = tablename;
        this.value=value;
        this.index=index;
    }

    @Override
    protected Boolean doInBackground(Void... params) {

        databasehelper db= new databasehelper(context);

        if(tablename.equals(dbstring.TABLE_CATEGORY)==true){
            //Them chi or thu
            _category category= new _category();
            category.setCategory_name(value);
            category.setParent(index);

            return db.themCategory(category);

        }else if(tablename.equals(dbstring.TABLE_LOAINO)){
            _loaino loaino = new _loaino();
            loaino.setLoaino_name(value);
            return db.themLoaino(loaino);
        }

        return false;
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
