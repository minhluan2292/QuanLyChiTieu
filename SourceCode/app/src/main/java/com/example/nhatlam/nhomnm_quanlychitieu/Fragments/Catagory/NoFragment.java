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
public class NoFragment extends Fragment {


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
                }else{
                    Toast.makeText(getActivity().getApplicationContext(),"Tên danh mục không bỏ trống!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }

}
