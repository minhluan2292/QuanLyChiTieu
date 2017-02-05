package com.example.nhatlam.nhomnm_quanlychitieu.Fragments.DoiTienTe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nhatlam.nhomnm_quanlychitieu.R;

/**
 * Created by Nghia Tran on 2017-02-06.
 */

public class DoiTienTeFragment extends Fragment implements View.OnClickListener{

    View v;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_doi_tien_te, container, false);
        //db=new databasehelper(getActivity().getApplicationContext());


        return v;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.btnEditUser:{
                break;
            }
        }
    }
}