package com.example.nhatlam.nhomnm_quanlychitieu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.nhatlam.nhomnm_quanlychitieu.Models._user;


/**
 * A simple {@link Fragment} subclass.
 */
public class DangnhapFragment extends Fragment {


    public DangnhapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_dangnhap, container, false);

        final EditText txtUsername = (EditText) view.findViewById(R.id.txtUsernameEDN);
        final EditText txtPassword = (EditText)view.findViewById(R.id.txtPasswordEDN);

        Button btnDangNhap = (Button)view.findViewById(R.id.btnDangNhap);
        final CheckBox chkRemember = (CheckBox)view.findViewById(R.id.checkboxRemember);

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtUsername.getText().toString()!=""&&txtPassword.getText().toString()!="") {
                    _user user = new _user();
                    user.setUsername(txtUsername.getText().toString());
                    user.setPassword(txtPassword.getText().toString());
                    //databasehelper db = new databasehelper(getActivity().getApplicationContext());

                    if(chkRemember.isChecked()==true){
                        user.setRemember(1);
                    }else{
                        user.setRemember(0);
                    }

                    DangNhapTask task= new DangNhapTask(getActivity().getApplicationContext(),user);
                    task.execute();

                    //user = db.dangnhapUser(user);
                    /*
                    if(user!=null){
                        Intent i = new Intent(getActivity().getApplicationContext(),MainActivity.class);
                        i.putExtra("user",user);
                        startActivity(i);
                    }else{
                        Toast.makeText(getActivity().getApplicationContext(),"Đăng nhập thất bại!",Toast.LENGTH_SHORT).show();
                    }
                    */
                }
            }
        });


        return view;
    }

}
