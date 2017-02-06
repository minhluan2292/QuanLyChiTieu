package com.example.nhatlam.nhomnm_quanlychitieu.Fragments.SoTietKiem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nhatlam.nhomnm_quanlychitieu.Models._sotietkiem;
import com.example.nhatlam.nhomnm_quanlychitieu.R;

import java.util.List;

/**
 * Created by Nghia Tran on 2017-02-06.
 */

public class SoTietKiemArrayAdapter extends ArrayAdapter<_sotietkiem> {
    private final Context context;
    private final List<_sotietkiem> values;


    public SoTietKiemArrayAdapter(Context context, List<_sotietkiem> values) {
        super(context, R.layout.sotietkiem_item, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.sotietkiem_item, parent, false);
        TextView txtTenSo = (TextView) rowView.findViewById(R.id.txtTenSo);
        TextView txtMucTieu = (TextView) rowView.findViewById(R.id.txtMucTieu);
        TextView txtSoTienCo = (TextView) rowView.findViewById(R.id.txtSoTienCo);
        txtTenSo.setText(values.get(position).getSotietkiem_name());
        txtMucTieu.setText(values.get(position).getMuctieu());
        txtSoTienCo.setText(values.get(position).getSotienbandau());

        return rowView;
    }
}
