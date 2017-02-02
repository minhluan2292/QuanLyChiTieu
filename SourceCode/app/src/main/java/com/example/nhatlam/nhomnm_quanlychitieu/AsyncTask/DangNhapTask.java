package com.example.nhatlam.nhomnm_quanlychitieu.AsyncTask;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.nhatlam.nhomnm_quanlychitieu.Database.databasehelper;
import com.example.nhatlam.nhomnm_quanlychitieu.Activities.MainActivity;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._user;

/**
 * Created by NHATLAM on 1/26/2017.
 */

public class DangNhapTask extends AsyncTask<Void,Void,_user> {

    private final Context context;
    private _user user;

    public DangNhapTask(Context context,_user user) {
        this.context = context;
        this.user=user;
    }

    @Override
    protected _user doInBackground(Void... params) {
        databasehelper db = new databasehelper(context);
        _user u = new _user();
        u = db.dangnhapUser(this.user);
        return u;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(_user aVoid) {
        super.onPostExecute(aVoid);
        if(aVoid !=null){
            Intent i = new Intent(context,MainActivity.class);
            i.putExtra("user",aVoid);
            context.startActivity(i);

        }else{
            Toast.makeText(context,"Đăng nhập thất bại!",Toast.LENGTH_SHORT).show();
        }
    }
}
