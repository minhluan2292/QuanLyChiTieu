package com.example.nhatlam.nhomnm_quanlychitieu.Fragments.Catagory;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nhatlam.nhomnm_quanlychitieu.AsyncTask.AddCatagoryTask;
import com.example.nhatlam.nhomnm_quanlychitieu.Database.databasehelper;
import com.example.nhatlam.nhomnm_quanlychitieu.Database.dbstring;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._loaino;
import com.example.nhatlam.nhomnm_quanlychitieu.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoFragment extends Fragment {
    RecyclerView rView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    databasehelper db;
    List<_loaino> lst;
    List<CategoryProvider> lstCate;

    public NoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_no, container, false);
        final EditText txtNoName = (EditText)v.findViewById(R.id.txtNoName);

        final View vContain = v.findViewById(R.id.layoutAddLoaiNo);
        final View vButtonShow = v.findViewById(R.id.layoutbtnAddLoaiNo);

        Button btnAdd = (Button)v.findViewById(R.id.btnAddNewLoaiNo);
        Button btnShowAdd = (Button)v.findViewById(R.id.btnShowContainLoaiNo);



        rView = (RecyclerView)v.findViewById(R.id.lstLoaiNo);
        db = new databasehelper(getActivity().getApplicationContext());
        lst= db.laydanhsachLoaino();
        lstCate = new ArrayList<CategoryProvider>();
        for(int i=0;i<lst.size();i++){
            CategoryProvider cateprovider = new CategoryProvider(R.drawable.loaino,lst.get(i).getLoaino_name());
            lstCate.add(cateprovider);
        }

        adapter = new CategoryRecylerAdapter(getActivity().getApplicationContext(),lstCate);
        rView.setHasFixedSize(true);

        layoutManager= new LinearLayoutManager(getActivity());
        rView.setLayoutManager(layoutManager);
        rView.setAdapter(adapter);

        Button btnCancel = (Button) v.findViewById(R.id.btnCancelNo);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vContain.setVisibility(View.GONE);
                vButtonShow.setVisibility(View.VISIBLE);
            }
        });




        btnShowAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vContain.setVisibility(View.VISIBLE);
                vButtonShow.setVisibility(View.GONE);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = txtNoName.getText().toString();
                String subval = value.replaceAll("\\s","");
                AddCatagoryTask task;
                if(subval.equals("")==false) {
                    task = new AddCatagoryTask(getActivity().getApplicationContext(), dbstring.TABLE_LOAINO, value, 0);
                    task.execute();
                    vContain.setVisibility(View.GONE);
                    vButtonShow.setVisibility(View.VISIBLE);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    refreshData();
                }else{
                    Toast.makeText(getActivity().getApplicationContext(),"Tên danh mục không bỏ trống!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }


    public void refreshData(){
        lst= db.laydanhsachLoaino();
        lstCate = new ArrayList<CategoryProvider>();
        for(int i=0;i<lst.size();i++){
            CategoryProvider cateprovider = new CategoryProvider(R.drawable.loaino,lst.get(i).getLoaino_name());
            lstCate.add(cateprovider);
        }

        adapter = new CategoryRecylerAdapter(getActivity().getApplicationContext(),lstCate);
        rView.setAdapter(adapter);

    }

}
