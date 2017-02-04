package com.example.nhatlam.nhomnm_quanlychitieu.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.nhatlam.nhomnm_quanlychitieu.Database.databasehelper;
import com.example.nhatlam.nhomnm_quanlychitieu.Database.dbstring;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._loaivi;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._vi;
/**
 * Created by MinhLuan on 2/5/2017.
 */

public class AddViTask extends AsyncTask<Void, Void, Boolean> {

    private Context context;
    private String tablename;
    private int userid;
    private String viname;
    private int loaiviid;
    private String sotien;
    private int donvitien;

    public AddViTask(Context context, String tablename, int userid, String viname, int loaiviid, String sotien, int donvitien) {
        this.context = context;
        this.tablename = tablename;
        this.userid = userid;
        this.viname = viname;
        this.loaiviid = loaiviid;
        this.sotien = sotien;
        this.donvitien = donvitien;
    }

    @Override
    protected Boolean doInBackground(Void... params) {

        databasehelper db= new databasehelper(context);

        _vi vi= new _vi();
        vi.setUser_id(userid);
        vi.setVi_name(viname);
        vi.setLoaivi_id(loaiviid);
        vi.setSotien(sotien);
        vi.setDonvitien(donvitien);

        return db.themVi(vi);

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