package com.example.nhatlam.nhomnm_quanlychitieu.Fragments.DoiTienTe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nhatlam.nhomnm_quanlychitieu.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by PhongPham on 2017-02-08.
 */

public class DoiTienTeFragment extends Fragment implements View.OnClickListener{

    View v;
    Button doi;
    EditText vn;
    TextView usa;
    TextView aud;
    TextView cad;
    TextView eur;
    TextView hkd;
    TextView sgd;
    TextView jpy;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_doi_tien_te, container, false);
        //db=new databasehelper(getActivity().getApplicationContext());

        doi = (Button)v.findViewById(R.id.btndoitien);
        vn = (EditText)v.findViewById(R.id.vn);
        usa = (TextView)v.findViewById(R.id.usa);
        aud = (TextView)v.findViewById(R.id.aud);
        cad = (TextView)v.findViewById(R.id.cad);
        eur = (TextView)v.findViewById(R.id.ero);
        hkd = (TextView)v.findViewById(R.id.hkd);
        sgd = (TextView)v.findViewById(R.id.sgd);
        jpy = (TextView)v.findViewById(R.id.jpy);

        vn.setInputType(InputType.TYPE_CLASS_NUMBER);

        doi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                int i = Integer.parseInt(vn.getText().toString());
                float us = (float)i/21000;
                float au = (float)i/19520;
                float ca = (float)i/19150;
                float eu = (float)i/28204;
                float hk = (float)i/2702;
                float sg = (float)i/16857;
                float jp = (float)i/203;

                NumberFormat formatter = new DecimalFormat("###,###,###,###,###.##");
                usa.setText(" " + formatter.format(us).toString() + " Dollar");
                aud.setText(" " + formatter.format(au).toString() + " Dollar");
                cad.setText(" " + formatter.format(ca).toString() + " Dollar");
                eur.setText(" " + formatter.format(eu).toString() + " EUR");
                hkd.setText(" " + formatter.format(hk).toString() + " Dollar");
                sgd.setText(" " + formatter.format(sg).toString() + " Dollar");
                jpy.setText(" " + formatter.format(jp).toString() + " Yen");

            }

        });
        return v;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.btndoitien:{
                int i = Integer.parseInt(vn.getText().toString());
                float us = (float)i/21000;
                float au = (float)i/19520;
                float ca = (float)i/19150;
                float eu = (float)i/28204;
                float hk = (float)i/2702;
                float sg = (float)i/16857;
                float jp = (float)i/203;

                NumberFormat formatter = new DecimalFormat("###,###,###,###,###.##");
                usa.setText(" " + formatter.format(us).toString() + " $ ");
                aud.setText(" " + formatter.format(au).toString() + " $ ");
                cad.setText(" " + formatter.format(ca).toString() + " $ ");
                eur.setText(" " + formatter.format(eu).toString() + " £ ");
                hkd.setText(" " + formatter.format(hk).toString() + " $ ");
                sgd.setText(" " + formatter.format(sg).toString() + " $ ");
                jpy.setText(" " + formatter.format(jp).toString() + " ¥ ");



                break;
            }
        }
    }
}