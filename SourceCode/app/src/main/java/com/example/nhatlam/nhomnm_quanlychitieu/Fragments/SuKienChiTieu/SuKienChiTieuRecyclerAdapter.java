package com.example.nhatlam.nhomnm_quanlychitieu.Fragments.SuKienChiTieu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhatlam.nhomnm_quanlychitieu.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by MinhLuan on 2/4/2017.
 */

public class SuKienChiTieuRecyclerAdapter extends RecyclerView.Adapter<SuKienChiTieuRecyclerAdapter.RecyclerViewHolder> {
    private List<SuKienChiTieuProvider> arrayList = new ArrayList<SuKienChiTieuProvider>();
    static Context context;
    static LayoutInflater inflater;

    public SuKienChiTieuRecyclerAdapter(Context context,List<SuKienChiTieuProvider> arrayList) {
        this.context=context;
        inflater = LayoutInflater.from(context);
        this.arrayList = arrayList;

    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.thuchi_item_layout,parent,false);
        RecyclerViewHolder holder = new RecyclerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        SuKienChiTieuProvider dataProvider = arrayList.get(position);
        holder.imgThuChi.setImageResource(dataProvider.getImg_res());
        holder.txtTenCata.setText(dataProvider.getTengiaodich());
        if(dataProvider.getParent()==1)
        {
            holder.txtSoTienThu.setText(dataProvider.getSotien());
            holder.txtSoTienChi.setVisibility(View.GONE);
        }
        else
        {
            holder.txtSoTienChi.setText(dataProvider.getSotien());
            holder.txtSoTienThu.setVisibility(View.GONE);
        }
        holder.txtNgayGiaoDich.setText(dataProvider.getNgaygiaodich());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        ImageView imgThuChi;
        TextView txtTenCata;
        TextView txtSoTienThu;
        TextView txtSoTienChi;
        TextView txtNgayGiaoDich;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            imgThuChi=(ImageView)itemView.findViewById(R.id.imgThuChi);
            txtTenCata=(TextView) itemView.findViewById(R.id.txtCataGiaoDich);
            txtSoTienThu=(TextView) itemView.findViewById(R.id.txtSoTienThu);
            txtSoTienChi=(TextView) itemView.findViewById(R.id.txtSoTienChi);
            txtNgayGiaoDich=(TextView) itemView.findViewById(R.id.txtNgayGiaoDich);

        }

    }
}
