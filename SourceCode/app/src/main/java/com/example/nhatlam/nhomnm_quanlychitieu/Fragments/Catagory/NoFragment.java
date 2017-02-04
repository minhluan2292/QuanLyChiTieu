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
    static databasehelper db;
    List<_loaino> lst;
    List<CategoryProvider> lstCate;
    static View v;
    static View vContain;
    static View vButtonShow;
    static View vbox;
    static EditText txtEdit;
    static EditText txtAdd;
    static int editPosition;
    public NoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_no, container, false);

        txtAdd = (EditText)v.findViewById(R.id.txtNoName);
        txtEdit = (EditText)v.findViewById(R.id.editnameNo);
        vContain = v.findViewById(R.id.layoutAddLoaiNo);
        vButtonShow = v.findViewById(R.id.layoutbtnAddLoaiNo);
        vbox = v.findViewById(R.id.NoEditBox);

        Button btnAcceptEdit=(Button) v.findViewById(R.id.btnEditNo);
        Button btnCancelEdit = (Button)v.findViewById(R.id.btnCancelEditNo);
        Button btnDelete = (Button)v.findViewById(R.id.btnDeleteNo);


        Button btnAdd = (Button)v.findViewById(R.id.btnAddNewLoaiNo);
        Button btnShowAdd = (Button)v.findViewById(R.id.btnShowContainLoaiNo);



        rView = (RecyclerView)v.findViewById(R.id.lstLoaiNo);
        db = new databasehelper(getActivity().getApplicationContext());
        lst= db.laydanhsachLoaino();
        lstCate = new ArrayList<CategoryProvider>();
        for(int i=0;i<lst.size();i++){
            CategoryProvider cateprovider = new CategoryProvider(lst.get(i).getLoaino_id(),R.drawable.loaino,lst.get(i).getLoaino_name());
            lstCate.add(cateprovider);
        }

        adapter = new CategoryRecylerAdapter(getActivity().getApplicationContext(),lstCate);
        rView.setHasFixedSize(true);

        layoutManager= new LinearLayoutManager(getActivity());
        rView.setLayoutManager(layoutManager);
        rView.setAdapter(adapter);

        Button btnCancel = (Button) v.findViewById(R.id.btnCancelNo);


        btnAcceptEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _loaino loaino = new _loaino();
                loaino.setLoaino_id(editPosition);
                loaino.setLoaino_name(txtEdit.getText().toString());
                if(db.chinhsuaLoaino(loaino)==true){
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
                _loaino loaino = new _loaino();
                loaino.setLoaino_id(editPosition);
                if(db.xoaLoaino(loaino)==true){
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
                    task = new AddCatagoryTask(getActivity().getApplicationContext(), dbstring.TABLE_LOAINO, value, 0);
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

                EditText edit = (EditText)v.findViewById(R.id.editnameNo);
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
        lst= db.laydanhsachLoaino();
        lstCate = new ArrayList<CategoryProvider>();
        for(int i=0;i<lst.size();i++){
            CategoryProvider cateprovider = new CategoryProvider(lst.get(i).getLoaino_id(),R.drawable.loaino,lst.get(i).getLoaino_name());
            lstCate.add(cateprovider);
        }

        adapter = new CategoryRecylerAdapter(getActivity().getApplicationContext(),lstCate);
        rView.setAdapter(adapter);

    }

}
