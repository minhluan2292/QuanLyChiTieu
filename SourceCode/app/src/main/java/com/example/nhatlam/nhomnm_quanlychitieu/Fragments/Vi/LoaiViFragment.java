package com.example.nhatlam.nhomnm_quanlychitieu.Fragments.Vi;


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

import com.example.nhatlam.nhomnm_quanlychitieu.AsyncTask.AddLoaiViTask;
import com.example.nhatlam.nhomnm_quanlychitieu.Database.databasehelper;
import com.example.nhatlam.nhomnm_quanlychitieu.Database.dbstring;
import com.example.nhatlam.nhomnm_quanlychitieu.Fragments.Catagory.RecyclerItemClickListener;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._loaivi;
import com.example.nhatlam.nhomnm_quanlychitieu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MinhLuan on 2/4/2017.
 */

public class LoaiViFragment extends Fragment {
    RecyclerView rView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    static databasehelper db;
    List<_loaivi> lst;
    static List<LoaiViProvider> lstLoaiVi;
    static View v;
    static View vContain;
    static View vButtonShow;
    static View vbox;
    static EditText txtEdit;
    static EditText txtAdd;

    static int editPosition;

    public LoaiViFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_loaivi, container, false);

        txtAdd = (EditText)v.findViewById(R.id.txtLoaiViName);
        txtEdit = (EditText)v.findViewById(R.id.editnameLoaiVi);

        vContain = v.findViewById(R.id.layoutAddLoaiVi);
        vButtonShow = v.findViewById(R.id.layoutbtnAddLoaiVi);
        vbox = v.findViewById(R.id.LoaiViEditBox);


        Button btnAcceptEdit=(Button) v.findViewById(R.id.btnEditLoaiVi);
        Button btnCancelEdit = (Button)v.findViewById(R.id.btnCancelEditLoaiVi);
        Button btnDelete = (Button)v.findViewById(R.id.btnDeleteLoaiVi);

        Button btnCancel = (Button) v.findViewById(R.id.btnCancelAddLoaiVi);

        rView = (RecyclerView)v.findViewById(R.id.lstLoaiVi);
        Button btnAdd = (Button)v.findViewById(R.id.btnAddNewLoaiVi);
        Button btnShowAdd = (Button)v.findViewById(R.id.btnShowContainLoaiVi);



        ///////////////////////////////////set adapter first/////////////////////////////////////////////
        db = new databasehelper(getActivity().getApplicationContext());
        lst= db.laydanhsachLoaiVi();
        lstLoaiVi = new ArrayList<LoaiViProvider>();
        for(int i=0;i<lst.size();i++){
            LoaiViProvider loaiprovider = new LoaiViProvider(lst.get(i).getLoaivi_id(),R.drawable.chi,lst.get(i).getLoaivi_name());
            lstLoaiVi.add(loaiprovider);
        }
        adapter = new LoaiViRecylerAdapter(getActivity().getApplicationContext(),lstLoaiVi);
        rView.setHasFixedSize(true);

        layoutManager= new LinearLayoutManager(getActivity());
        rView.setLayoutManager(layoutManager);
        rView.setAdapter(adapter);
        //////////////////////////////////set adapter first/////////////////////////////////////////////

        btnAcceptEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _loaivi loaivi = new _loaivi();
                loaivi.setLoaivi_id(editPosition);
                loaivi.setLoaivi_name(txtEdit.getText().toString());
                if(db.chinhsuaLoaiVi(loaivi)==true){
                    Toast.makeText(getActivity().getApplicationContext(),"Chỉnh sửa thành công!",Toast.LENGTH_SHORT);
                    txtEdit.setText("");
                    vContain.setVisibility(View.GONE);
                    vButtonShow.setVisibility(View.VISIBLE);
                    vbox.setVisibility(View.GONE);
                    refreshData();
                }else{
                    Toast.makeText(getActivity().getApplicationContext(),"Chỉnh sửa thất bại!",Toast.LENGTH_SHORT);
                }
            }
        });

        btnCancelEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtEdit.setText("");
                vContain.setVisibility(View.GONE);
                vButtonShow.setVisibility(View.VISIBLE);
                vbox.setVisibility(View.GONE);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _loaivi loaivi = new _loaivi();
                loaivi.setLoaivi_id(editPosition);
                if(db.xoaLoaiVi(loaivi)==true){
                    Toast.makeText(getActivity().getApplicationContext(),"Xóa thành công!",Toast.LENGTH_SHORT);
                    txtEdit.setText("");
                    vContain.setVisibility(View.GONE);
                    vButtonShow.setVisibility(View.VISIBLE);
                    vbox.setVisibility(View.GONE);
                    refreshData();
                }else{
                    Toast.makeText(getActivity().getApplicationContext(),"Xóa thất bại!",Toast.LENGTH_SHORT);
                }
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtAdd.setText("");
                vContain.setVisibility(View.GONE);
                vButtonShow.setVisibility(View.VISIBLE);
            }
        });


        btnShowAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vContain.setVisibility(View.VISIBLE);
                vButtonShow.setVisibility(View.GONE);
                vbox.setVisibility(View.GONE);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = txtAdd.getText().toString();
                String subval = value.replaceAll("\\s","");
                AddLoaiViTask task;
                if(subval.equals("")==false) {
                    task = new AddLoaiViTask(getActivity().getApplicationContext(), dbstring.TABLE_LOAIVI, value);
                    task.execute();
                    vContain.setVisibility(View.GONE);
                    vbox.setVisibility(View.GONE);
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

        rView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity().getApplicationContext(),
                rView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                vbox.setVisibility(View.VISIBLE);
                vContain.setVisibility(View.GONE);
                vButtonShow.setVisibility(View.GONE);

                editPosition=lstLoaiVi.get(position).getId();

                EditText edit = (EditText)v.findViewById(R.id.editnameLoaiVi);
                LoaiViProvider provider = lstLoaiVi.get(position);
                edit.setText(provider.getName());
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));


        return v;
    }


    public void refreshData(){
        lst= db.laydanhsachLoaiVi();
        lstLoaiVi = new ArrayList<LoaiViProvider>();
        for(int i=0;i<lst.size();i++){
            LoaiViProvider loaiviprovider = new LoaiViProvider(lst.get(i).getLoaivi_id(),R.drawable.chi,lst.get(i).getLoaivi_name());
            lstLoaiVi.add(loaiviprovider);
        }

        adapter = new LoaiViRecylerAdapter(getActivity().getApplicationContext(),lstLoaiVi);
        rView.setAdapter(adapter);

    }


}