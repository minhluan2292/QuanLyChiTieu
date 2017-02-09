package com.example.nhatlam.nhomnm_quanlychitieu.Fragments.ChuyenTien;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nhatlam.nhomnm_quanlychitieu.Database.databasehelper;
import com.example.nhatlam.nhomnm_quanlychitieu.Fragments.Vi.spinnerViAdapter;
import com.example.nhatlam.nhomnm_quanlychitieu.Fragments.Vi.viData;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._user;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._vi;
import com.example.nhatlam.nhomnm_quanlychitieu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nghia Tran on 2017-02-06.
 */

public class ChuyenTienFragment extends Fragment implements View.OnClickListener{

    _user user;
    databasehelper db;
    List<_vi> lstVi;
    View v;
    Spinner SpinnerViDi;
    Spinner SpinnerViDen;
    Button btnChuyenTien;
    EditText edtSoTienChuyen;
    int ViIDDi;
    int ViIDDen;


    public ChuyenTienFragment(_user user) {
        // Required empty public constructor
        this.user = user;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_chuyen_tien, container, false);
        db=new databasehelper(getActivity().getApplicationContext());
        SpinnerViDi = (Spinner)v.findViewById(R.id.spinnerViDi);
        SpinnerViDen = (Spinner)v.findViewById(R.id.spinnerViDen);
        btnChuyenTien = (Button)v.findViewById(R.id.btnChuyenTien);
        edtSoTienChuyen = (EditText)v.findViewById(R.id.edtSoTienChuyen);
        edtSoTienChuyen.setInputType(InputType.TYPE_CLASS_NUMBER);
        //Toast.makeText(getActivity().getApplicationContext(),user.getUsername(), Toast.LENGTH_LONG).show();

        // Show Spinner
        lstVi = db.laydanhsachVi(user);
        ArrayList<viData> ArraySpinner = new ArrayList<viData>();

        //String array_spinner[];
        //array_spinner = new String[lstVi.size()];
        //for(int i=0;i<lstVi.size();i++){
        //    array_spinner[i] = lstVi.get(i).getVi_name();
        //}
        for(int i=0;i<lstVi.size();i++){
            ArraySpinner.add(new viData(lstVi.get(i).getVi_id(),lstVi.get(i).getVi_name()));
        }
        //final ArrayAdapter<String> adapterDi = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item, ArraySpinner);
        //final ArrayAdapter<String> adapterDen = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item, ArraySpinner);


        final spinnerViAdapter adapterDi = new spinnerViAdapter(getActivity().getApplicationContext(),ArraySpinner);
        final spinnerViAdapter adapterDen = new spinnerViAdapter(getActivity().getApplicationContext(),ArraySpinner);

        SpinnerViDi.setAdapter(adapterDi);
        SpinnerViDen.setAdapter(adapterDen);
        ViIDDi = ArraySpinner.get(0).getId();
        ViIDDen = ArraySpinner.get(0).getId();

        SpinnerViDi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {

                viData ViDatadi = adapterDi.getItem(position);
                ViIDDi = ViDatadi.getId();
                //catagoryadd = db.getCategory(thuChiData.getId());
                //System.out.println(thuChiData.getId());
                //System.out.println(db.getCategory(thuChiData.getId()).getParent());
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapter) {  }
        });
        SpinnerViDen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {

                viData ViDataden = adapterDen.getItem(position);
                ViIDDen = ViDataden.getId();
                //catagoryadd = db.getCategory(thuChiData.getId());
                //System.out.println(thuChiData.getId());
                //System.out.println(db.getCategory(thuChiData.getId()).getParent());
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapter) {  }
        });

        btnChuyenTien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int SoTienCanChuyen = Integer.parseInt(edtSoTienChuyen.getText().toString());
                boolean x = db.chuyentienVi(ViIDDi,ViIDDen,edtSoTienChuyen.getText().toString());
                if (x)
                {
                    Toast.makeText(getActivity().getApplicationContext(),"Chuyển tiền thành công", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getActivity().getApplicationContext(),"Chuyển tiền thất bại", Toast.LENGTH_LONG).show();
                }
            }
        });

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
