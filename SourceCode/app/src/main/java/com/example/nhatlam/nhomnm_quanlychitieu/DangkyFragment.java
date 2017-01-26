package com.example.nhatlam.nhomnm_quanlychitieu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nhatlam.nhomnm_quanlychitieu.Models._user;


/**
 * A simple {@link Fragment} subclass.
 */
public class DangkyFragment extends Fragment {


    public DangkyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dangky, container, false);

        final EditText txtSDT = (EditText) view.findViewById(R.id.txtSDT);
        final EditText txtUsername = (EditText)view.findViewById(R.id.txtUsernameEDK);
        final EditText txtPassword = (EditText) view.findViewById(R.id.txtPasswordEDK);
        final EditText txtRepass = (EditText) view.findViewById(R.id.txtRePasswordEDK);

        Button btnDangKy = (Button) view.findViewById(R.id.btnDangKy);

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtUsername.getText().toString();
                String pass = txtPassword.getText().toString();
                String repass = txtRepass.getText().toString();
                String sdt = txtSDT.getText().toString();
                if(pass.equals(repass)==true){
                    _user user= new _user();
                    user.setUsername(username);
                    user.setPassword(pass);
                    user.setSdt(sdt);

                    DangKyTask task = new DangKyTask(getActivity().getApplicationContext(),user);
                    task.execute();

                }else {
                    Toast.makeText(getActivity().getApplicationContext(),pass+" - "+repass,Toast.LENGTH_SHORT).show();
                }

            }
        });


        return view;
    }

}
