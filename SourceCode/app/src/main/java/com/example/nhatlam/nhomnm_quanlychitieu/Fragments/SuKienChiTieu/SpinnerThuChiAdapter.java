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
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhatlam.nhomnm_quanlychitieu.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SpinnerThuChiAdapter extends BaseAdapter {
    int groupid;
    Context context;
    ArrayList<ThuChiData> list;
    LayoutInflater inflater;

    public SpinnerThuChiAdapter(Context context, ArrayList<ThuChiData> list){
        this.list=list;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent ){
        View itemView=inflater.inflate(R.layout.spinner_thuchi_layout,null);
        ImageView imageView=(ImageView)itemView.findViewById(R.id.imageSpiner);
        imageView.setImageResource(list.get(position).getImageId());
        TextView textViewID=(TextView)itemView.findViewById(R.id.idSpinner);
        textViewID.setText(list.get(position).getId());
        TextView textView=(TextView)itemView.findViewById(R.id.txtSpiner);
        textView.setText(list.get(position).getText());
        return itemView;
    }

    public View getDropDownView(int position, View convertView, ViewGroup
            parent){
        return getView(position,convertView,parent);

    }
}