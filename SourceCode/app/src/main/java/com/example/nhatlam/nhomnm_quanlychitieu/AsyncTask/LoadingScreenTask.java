package com.example.nhatlam.nhomnm_quanlychitieu.AsyncTask;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.nhatlam.nhomnm_quanlychitieu.Activities.DangNhapActivity;
import com.example.nhatlam.nhomnm_quanlychitieu.Database.databasehelper;
import com.example.nhatlam.nhomnm_quanlychitieu.Activities.MainActivity;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._user;

/**
 * Created by NHATLAM on 1/25/2017.
 */

public class LoadingScreenTask extends AsyncTask <Void, Void,_user> {

    private final Context context;


    public LoadingScreenTask(Context context) {
        this.context = context;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected _user doInBackground(Void... params) {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        databasehelper db = new databasehelper(context);
        _user user = new _user();
        user = db.autodangnhapUser();
        return user;
    }

    @Override
    protected void onPostExecute(_user aVoid) {
        super.onPostExecute(aVoid);
        if(aVoid !=null){
            Intent i = new Intent(context,MainActivity.class);
            i.putExtra("user",aVoid);
            context.startActivity(i);

        }else{
            Intent i = new Intent(context,DangNhapActivity.class);
            context.startActivity(i);
        }
    }
}
