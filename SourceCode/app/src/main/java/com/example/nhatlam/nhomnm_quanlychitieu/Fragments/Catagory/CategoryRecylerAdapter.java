package com.example.nhatlam.nhomnm_quanlychitieu.Fragments.Catagory;

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
 * Created by NHATLAM on 2/3/2017.
 */

public class CategoryRecylerAdapter extends RecyclerView.Adapter<CategoryRecylerAdapter.RecyclerViewHolder> {
    private List<CategoryProvider> arrayList = new ArrayList<CategoryProvider>();
    static Context con;
    LayoutInflater inflater;
    public CategoryRecylerAdapter(Context context,List<CategoryProvider> arrayList) {
        this.con=context;
        inflater = LayoutInflater.from(context);
        this.arrayList = arrayList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.category_item_layout,parent,false);
        RecyclerViewHolder holder = new RecyclerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        CategoryProvider dataProvider = arrayList.get(position);
        holder.img.setImageResource(dataProvider.getImg_res());
        holder.txt.setText(dataProvider.getName());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView txt;


        public RecyclerViewHolder(View itemView) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.imgCategory);
            txt=(TextView) itemView.findViewById(R.id.txtCategoryName);
        }
    }
}
