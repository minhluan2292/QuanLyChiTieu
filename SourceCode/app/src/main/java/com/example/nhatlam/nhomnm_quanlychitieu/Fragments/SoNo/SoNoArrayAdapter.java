package com.example.nhatlam.nhomnm_quanlychitieu.Fragments.SoNo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nhatlam.nhomnm_quanlychitieu.Models._sono;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._sotietkiem;
import com.example.nhatlam.nhomnm_quanlychitieu.R;

import java.util.List;

/**
 * Created by Nghia Tran on 2017-02-08.
 */

public class SoNoArrayAdapter extends ArrayAdapter<_sono> {
    private final Context context;
    private final List<_sono> values;


    public SoNoArrayAdapter(Context context, List<_sono> values) {
        super(context, R.layout.sono_item, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.sono_item, parent, false);
        TextView txtDoiTuongItem = (TextView) rowView.findViewById(R.id.txtDoiTuongItem);
        TextView txtTrangThaiItem = (TextView) rowView.findViewById(R.id.txtTrangThaiItem);

        txtDoiTuongItem.setText(values.get(position).getDoituong());
        int soTienHienTai = Integer.parseInt(values.get(position).getSotien());
        if(soTienHienTai < 0){
            txtTrangThaiItem.setText("Nợ");
        }else{
            txtTrangThaiItem.setText("Cho vay");
        }
        return rowView;
    }
}
