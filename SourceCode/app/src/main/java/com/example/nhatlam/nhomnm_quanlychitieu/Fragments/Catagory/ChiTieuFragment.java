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
import com.example.nhatlam.nhomnm_quanlychitieu.Models._category;
import com.example.nhatlam.nhomnm_quanlychitieu.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChiTieuFragment extends Fragment {
    RecyclerView rView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    databasehelper db;
    List<_category> lst;
    List<CategoryProvider> lstCate;

    public ChiTieuFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_chi_tieu, container, false);


        final EditText txtChiTieuName = (EditText)v.findViewById(R.id.txtChitieuName);

        final View vContain = v.findViewById(R.id.layoutAddChiTieu);
        final View vButtonShow = v.findViewById(R.id.layoutbtnAddChiTieu);
        rView = (RecyclerView)v.findViewById(R.id.lstChitieu);
        Button btnAdd = (Button)v.findViewById(R.id.btnAddNewChiTieu);
        Button btnShowAdd = (Button)v.findViewById(R.id.btnShowContainChiTieu);

        db = new databasehelper(getActivity().getApplicationContext());
        lst= db.laydanhsachCategory(0);
        lstCate = new ArrayList<CategoryProvider>();
        for(int i=0;i<lst.size();i++){
            CategoryProvider cateprovider = new CategoryProvider(R.drawable.chi,lst.get(i).getCategory_name());
            lstCate.add(cateprovider);
        }
        adapter = new CategoryRecylerAdapter(getActivity().getApplicationContext(),lstCate);
        rView.setHasFixedSize(true);

        layoutManager= new LinearLayoutManager(getActivity());
        rView.setLayoutManager(layoutManager);
        rView.setAdapter(adapter);


        Button btnCancel = (Button) v.findViewById(R.id.btnCancelChiTieu);
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
                String value = txtChiTieuName.getText().toString();
                String subval = value.replaceAll("\\s","");
                AddCatagoryTask task;
                if(subval.equals("")==false) {
                    task = new AddCatagoryTask(getActivity().getApplicationContext(), dbstring.TABLE_CATEGORY, value, 0);
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
        lst= db.laydanhsachCategory(0);
        lstCate = new ArrayList<CategoryProvider>();
        for(int i=0;i<lst.size();i++){
            CategoryProvider cateprovider = new CategoryProvider(R.drawable.chi,lst.get(i).getCategory_name());
            lstCate.add(cateprovider);
        }

        adapter = new CategoryRecylerAdapter(getActivity().getApplicationContext(),lstCate);
        rView.setAdapter(adapter);

    }

}
