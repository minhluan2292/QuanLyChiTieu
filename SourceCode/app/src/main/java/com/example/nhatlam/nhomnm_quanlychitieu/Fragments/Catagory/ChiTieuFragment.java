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
    static databasehelper db;
    List<_category> lst;
    static List<CategoryProvider> lstCate;
    static View v;
    static View vContain;
    static View vButtonShow;
    static View vbox;
    static EditText txtEdit;
    static EditText txtAdd;

    static int editPosition;

    public ChiTieuFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_chi_tieu, container, false);

        txtAdd = (EditText)v.findViewById(R.id.txtChitieuName);
        txtEdit = (EditText)v.findViewById(R.id.editnameChiTieu);

        vContain = v.findViewById(R.id.layoutAddChiTieu);
        vButtonShow = v.findViewById(R.id.layoutbtnAddChiTieu);
        vbox = v.findViewById(R.id.ChitieuEditBox);


        Button btnAcceptEdit=(Button) v.findViewById(R.id.btnEditChiTieu);
        Button btnCancelEdit = (Button)v.findViewById(R.id.btnCancelEditChitieu);
        Button btnDelete = (Button)v.findViewById(R.id.btnDeleteChiTieu);

        Button btnCancel = (Button) v.findViewById(R.id.btnCancelChiTieu);

        rView = (RecyclerView)v.findViewById(R.id.lstChitieu);
        Button btnAdd = (Button)v.findViewById(R.id.btnAddNewChiTieu);
        Button btnShowAdd = (Button)v.findViewById(R.id.btnShowContainChiTieu);



        ///////////////////////////////////set adapter first/////////////////////////////////////////////
        db = new databasehelper(getActivity().getApplicationContext());
        lst= db.laydanhsachCategory(0);
        lstCate = new ArrayList<CategoryProvider>();
        for(int i=0;i<lst.size();i++){
            CategoryProvider cateprovider = new CategoryProvider(lst.get(i).getCategory_id(),R.drawable.chi,lst.get(i).getCategory_name());
            lstCate.add(cateprovider);
        }
        adapter = new CategoryRecylerAdapter(getActivity().getApplicationContext(),lstCate);
        rView.setHasFixedSize(true);

        layoutManager= new LinearLayoutManager(getActivity());
        rView.setLayoutManager(layoutManager);
        rView.setAdapter(adapter);
        //////////////////////////////////set adapter first/////////////////////////////////////////////

        btnAcceptEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _category category = new _category();
                category.setCategory_id(editPosition);
                category.setCategory_name(txtEdit.getText().toString());
                if(db.chinhsuaCategory(category)==true){
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
                _category category = new _category();
                category.setCategory_id(editPosition);
                if(db.xoaCategory(category)==true){
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
                AddCatagoryTask task;
                if(subval.equals("")==false) {
                    task = new AddCatagoryTask(getActivity().getApplicationContext(), dbstring.TABLE_CATEGORY, value, 0);
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

                editPosition=lstCate.get(position).getId();

                EditText edit = (EditText)v.findViewById(R.id.editnameChiTieu);
                CategoryProvider provider = lstCate.get(position);
                edit.setText(provider.getName());
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));


        return v;
    }


    public void refreshData(){
        lst= db.laydanhsachCategory(0);
        lstCate = new ArrayList<CategoryProvider>();
        for(int i=0;i<lst.size();i++){
            CategoryProvider cateprovider = new CategoryProvider(lst.get(i).getCategory_id(),R.drawable.chi,lst.get(i).getCategory_name());
            lstCate.add(cateprovider);
        }

        adapter = new CategoryRecylerAdapter(getActivity().getApplicationContext(),lstCate);
        rView.setAdapter(adapter);

    }


}
