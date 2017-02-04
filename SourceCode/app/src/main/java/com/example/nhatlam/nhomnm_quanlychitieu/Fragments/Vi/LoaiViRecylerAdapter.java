package com.example.nhatlam.nhomnm_quanlychitieu.Fragments.Vi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhatlam.nhomnm_quanlychitieu.R;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by MinhLuan on 2/4/2017.
 */

public class LoaiViRecylerAdapter extends RecyclerView.Adapter<LoaiViRecylerAdapter.RecyclerViewHolder> {
    private List<LoaiViProvider> arrayList = new ArrayList<LoaiViProvider>();
    static Context context;
    static LayoutInflater inflater;

    public LoaiViRecylerAdapter(Context context,List<LoaiViProvider> arrayList) {
        this.context=context;
        inflater = LayoutInflater.from(context);
        this.arrayList = arrayList;

    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.loaivi_item_layout,parent,false);
        RecyclerViewHolder holder = new RecyclerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        LoaiViProvider dataProvider = arrayList.get(position);
        holder.img.setImageResource(dataProvider.getImg_res());
        holder.txt.setText(dataProvider.getName());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView txt;


        public RecyclerViewHolder(View itemView) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.imgLoaiVi);
            txt=(TextView) itemView.findViewById(R.id.txtLoaiViName);
        }

    }
}
