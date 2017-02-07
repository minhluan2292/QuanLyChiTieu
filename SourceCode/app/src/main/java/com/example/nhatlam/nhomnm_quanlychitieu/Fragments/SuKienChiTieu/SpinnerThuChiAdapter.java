package com.example.nhatlam.nhomnm_quanlychitieu.Fragments.SuKienChiTieu;

/**
 * Created by MinhLuan on 2/7/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhatlam.nhomnm_quanlychitieu.R;

import java.util.ArrayList;

public class SpinnerThuChiAdapter extends ArrayAdapter<ThuChiData> {
    int groupid;
    Activity context;
    ArrayList<ThuChiData> list;
    LayoutInflater inflater;
    public SpinnerThuChiAdapter(Activity context, int groupid, int id, ArrayList<ThuChiData>
            list){
        super(context,id,list);
        this.list=list;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.groupid=groupid;
    }

    public View getView(int position, View convertView, ViewGroup parent ){
        View itemView=inflater.inflate(groupid,parent,false);
        ImageView imageView=(ImageView)itemView.findViewById(R.id.imageSpiner);
        imageView.setImageResource(list.get(position).getImageId());
        /*TextView textViewID=(TextView)itemView.findViewById(R.id.idSpinner);
        textViewID.setText(list.get(position).getId());*/
        TextView textView=(TextView)itemView.findViewById(R.id.txtSpiner);
        textView.setText(list.get(position).getText());
        return itemView;
    }

    public View getDropDownView(int position, View convertView, ViewGroup
            parent){
        return getView(position,convertView,parent);

    }
}