package com.example.nhatlam.nhomnm_quanlychitieu.Fragments.Vi;

/**
 * Created by MinhLuan on 2/4/2017.
 */

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

import com.example.nhatlam.nhomnm_quanlychitieu.AsyncTask.AddViTask;
import com.example.nhatlam.nhomnm_quanlychitieu.Database.databasehelper;
import com.example.nhatlam.nhomnm_quanlychitieu.Database.dbstring;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._user;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._vi;
import com.example.nhatlam.nhomnm_quanlychitieu.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViFragment extends Fragment {
    RecyclerView rView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    static databasehelper db;
    List<_vi> lst;
    static List<ViProvider> lstVi;
    static View v;
    static View vContain;
    static View vButtonShow;
    static View vbox;
    static EditText txtEditViName;
    static EditText txtEditViSoTien;
    static EditText txtAddViName;
    static EditText txtAddViSoTien;
    _user user;

    static int editPosition;

    public ViFragment() {
        // Required empty public constructor

    }

    public ViFragment(_user user) {
        // Required empty public constructor
        this.user = user;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_vi, container, false);

        txtAddViName = (EditText)v.findViewById(R.id.txtViName);
        txtAddViSoTien = (EditText)v.findViewById(R.id.txtViSoTien);

        txtEditViName = (EditText)v.findViewById(R.id.editnameVi);
        txtEditViSoTien = (EditText)v.findViewById(R.id.editsotienVi);

        vContain = v.findViewById(R.id.layoutAddVi);
        vButtonShow = v.findViewById(R.id.layoutbtnAddVi);
        vbox = v.findViewById(R.id.ViEditBox);


        Button btnAcceptEdit=(Button) v.findViewById(R.id.btnEditVi);
        Button btnCancelEdit = (Button)v.findViewById(R.id.btnCancelEditVi);
        Button btnDelete = (Button)v.findViewById(R.id.btnDeleteVi);

        Button btnCancel = (Button) v.findViewById(R.id.btnCancelAddVi);

        rView = (RecyclerView)v.findViewById(R.id.lstVi);
        Button btnAdd = (Button)v.findViewById(R.id.btnAddNewVi);
        Button btnShowAdd = (Button)v.findViewById(R.id.btnShowContainVi);



        ///////////////////////////////////set adapter first/////////////////////////////////////////////
        db = new databasehelper(getActivity().getApplicationContext());
        
        lst= db.laydanhsachVi(user);
        lstVi = new ArrayList<ViProvider>();
        for(int i=0;i<lst.size();i++){
            ViProvider catevi = new ViProvider(lst.get(i).getVi_id(),R.drawable.chi,lst.get(i).getVi_name(),lst.get(i).getSotien());
            lstVi.add(catevi);
        }
        adapter = new ViRecylerAdapter(getActivity().getApplicationContext(),lstVi);
        rView.setHasFixedSize(true);

        layoutManager= new LinearLayoutManager(getActivity());
        rView.setLayoutManager(layoutManager);
        rView.setAdapter(adapter);
        //////////////////////////////////set adapter first/////////////////////////////////////////////

        btnAcceptEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _vi vi = new _vi();
                vi.setVi_id(editPosition);
                vi.setVi_name(txtEditViName.getText().toString());
                vi.setSotien(txtEditViSoTien.getText().toString());
                if(db.chinhsuaVi(vi)==true){
                    Toast.makeText(getActivity().getApplicationContext(),"Chỉnh sửa thành công!",Toast.LENGTH_SHORT);
                    txtEditViName.setText("");
                    txtEditViSoTien.setText("");
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
                txtEditViName.setText("");
                txtEditViSoTien.setText("");
                vContain.setVisibility(View.GONE);
                vButtonShow.setVisibility(View.VISIBLE);
                vbox.setVisibility(View.GONE);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _vi vi = new _vi();
                vi.setVi_id(editPosition);
                if(db.xoaVi(vi)==true){
                    Toast.makeText(getActivity().getApplicationContext(),"Xóa thành công!",Toast.LENGTH_SHORT);
                    txtEditViName.setText("");
                    txtEditViSoTien.setText("");
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
                txtAddViName.setText("");
                txtAddViSoTien.setText("");
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
                String viname = txtAddViName.getText().toString();
                String sotien = txtAddViSoTien.getText().toString();
                String subval = viname.replaceAll("\\s","");
                AddViTask task;
                if(subval.equals("")==false) {
                    task = new AddViTask(getActivity().getApplicationContext(), dbstring.TABLE_VI, user.getUser_id(), viname, 0,sotien,0);
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
                    Toast.makeText(getActivity().getApplicationContext(),"Tên ví không bỏ trống!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        rView.addOnItemTouchListener(new com.example.nhatlam.nhomnm_quanlychitieu.Fragments.Catagory.RecyclerItemClickListener(getActivity().getApplicationContext(),
                rView, new com.example.nhatlam.nhomnm_quanlychitieu.Fragments.Catagory.RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                vbox.setVisibility(View.VISIBLE);
                vContain.setVisibility(View.GONE);
                vButtonShow.setVisibility(View.GONE);

                editPosition=lstVi.get(position).getId();

                EditText edit = (EditText)v.findViewById(R.id.editnameVi);
                EditText edit2 = (EditText)v.findViewById(R.id.editsotienVi);
                ViProvider provider = lstVi.get(position);
                edit.setText(provider.getName());
                edit2.setText(provider.getSotien());
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));


        return v;
    }


    public void refreshData(){
        lst= db.laydanhsachVi(user);
        lstVi = new ArrayList<ViProvider>();
        for(int i=0;i<lst.size();i++){
            ViProvider catevi = new ViProvider(lst.get(i).getVi_id(),R.drawable.chi,lst.get(i).getVi_name(),lst.get(i).getSotien());
            lstVi.add(catevi);
        }

        adapter = new ViRecylerAdapter(getActivity().getApplicationContext(),lstVi);
        rView.setAdapter(adapter);

    }


}