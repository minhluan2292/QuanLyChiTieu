package com.example.nhatlam.nhomnm_quanlychitieu.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.nhatlam.nhomnm_quanlychitieu.Database.databasehelper;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._user;

/**
 * Created by NHATLAM on 1/26/2017.
 */

public class DangKyTask extends AsyncTask<Void,Void,Boolean> {

    private final Context context;
    private _user user;

    public DangKyTask(Context context,_user user) {
        this.context = context;
        this.user = user;
    }

    @Override
    protected Boolean doInBackground(Void... params) {

        databasehelper db = new databasehelper(context);
        //_user u = new _user();


        return db.dangkyUser(this.user);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Boolean aVoid) {
        super.onPostExecute(aVoid);
        if(aVoid==true){
            Toast.makeText(context,"Đăng ký thành công!",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Đăng ký thất bại!",Toast.LENGTH_SHORT).show();
        }
    }
}
