package com.example.nhatlam.nhomnm_quanlychitieu.Fragments.Catagory;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nhatlam.nhomnm_quanlychitieu.AsyncTask.AddCatagoryTask;
import com.example.nhatlam.nhomnm_quanlychitieu.Database.dbstring;
import com.example.nhatlam.nhomnm_quanlychitieu.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChiTieuFragment extends Fragment {


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

        Button btnAdd = (Button)v.findViewById(R.id.btnAddNewChiTieu);
        Button btnShowAdd = (Button)v.findViewById(R.id.btnShowContainChiTieu);

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
                }else{
                    Toast.makeText(getActivity().getApplicationContext(),"Tên danh mục không bỏ trống!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }

}
