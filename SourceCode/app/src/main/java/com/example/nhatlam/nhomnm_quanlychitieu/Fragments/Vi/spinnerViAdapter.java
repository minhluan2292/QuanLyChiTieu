package com.example.nhatlam.nhomnm_quanlychitieu.Fragments.Vi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nhatlam.nhomnm_quanlychitieu.R;

import java.util.ArrayList;

/**
 * Created by Phong Pham on 2/8/2017.
 */

public class spinnerViAdapter extends ArrayAdapter<viData>{
    private Context context1;
    private ArrayList<viData> datas;
    LayoutInflater inflater;
    public spinnerViAdapter(Context context, int resource) {
        super(context, resource);
    }

    public spinnerViAdapter(Context context, ArrayList<viData> objects) {
        super(context, R.layout.spinner_row, objects);
        this.context1= context;
        datas=objects;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return getCustomView(position, convertView, parent);
        View row = inflater.inflate(R.layout.spinner_row, parent, false);

        TextView tvCategory = (TextView) row.findViewById(R.id.tvCategory);

        tvCategory.setText(datas.get(position).getViname());

        return row;
    }

}
