package com.example.nhatlam.nhomnm_quanlychitieu.Fragments.User;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhatlam.nhomnm_quanlychitieu.Database.databasehelper;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._user;
import com.example.nhatlam.nhomnm_quanlychitieu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThongtinuserFragment extends Fragment implements View.OnClickListener{

    _user user;
    databasehelper db;

    View v;
    View vboxThongTinUser;
    View vboxEditUser;
    View vboxEditPasswordUser;


    TextView txtUsername;
    TextView txtSDT;

    TextView txtUsernameEdit;
    EditText txtSDTEdit;

    EditText txtNewPassword;
    EditText txtOldPassword;

    Button btnEditThongTin;
    Button btnEditPassword;
    Button btnEditThongTinAccept;
    Button btnEditThongTinCancel;
    Button btnEditPasswordAccept;
    Button btnEditPasswordCancel;

    public ThongtinuserFragment() {
        // Required empty public constructor
    }
    public ThongtinuserFragment(_user user) {
        // Required empty public constructor
        this.user=user;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_thongtinuser, container, false);
        db=new databasehelper(getActivity().getApplicationContext());
        vboxThongTinUser = v.findViewById(R.id.vboxThongTinUser);
        vboxEditUser = v.findViewById(R.id.vboxEditUser);
        vboxEditPasswordUser=v.findViewById(R.id.vboxEditUserPassword);

        txtUsername = (TextView) v.findViewById(R.id.txtThongtinUsername);
        txtSDT = (TextView)v.findViewById(R.id.txtThongtinSDT);

        txtUsernameEdit = (TextView) v.findViewById(R.id.txtEditUsername);
        txtSDTEdit = (EditText)v.findViewById(R.id.txtEditSDT);

        txtNewPassword = (EditText)v.findViewById(R.id.txtNewUserPassword);
        txtOldPassword = (EditText)v.findViewById(R.id.txtOldUserPassword);

        btnEditThongTin = (Button)v.findViewById(R.id.btnEditUser);
        btnEditPassword = (Button)v.findViewById(R.id.btnEditPassUser);
        btnEditThongTinAccept = (Button)v.findViewById(R.id.btnEditUserAccept);
        btnEditThongTinCancel = (Button)v.findViewById(R.id.btnEditUserCancel);
        btnEditPasswordAccept = (Button)v.findViewById(R.id.btnEditUserPasswordAccept);
        btnEditPasswordCancel = (Button)v.findViewById(R.id.btnEditUserPasswordCancel);

        btnEditThongTin.setOnClickListener(this);
        btnEditPassword.setOnClickListener(this);
        btnEditThongTinAccept.setOnClickListener(this);
        btnEditThongTinCancel.setOnClickListener(this);
        btnEditPasswordAccept.setOnClickListener(this);
        btnEditPasswordCancel.setOnClickListener(this);

        LoadThongTin();

        return v;
    }

    private void LoadThongTin(){
        setVisibleView(View.VISIBLE,View.GONE,View.GONE);
        txtUsername.setText(user.getUsername());
        txtSDT.setText(user.getSdt());
    }



    private void setVisibleView(int vboxThongTin, int vboxEditThongTin, int vboxEditPass){
        vboxThongTinUser.setVisibility(vboxThongTin);
        vboxEditUser.setVisibility(vboxEditThongTin);
        vboxEditPasswordUser.setVisibility(vboxEditPass);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.btnEditUser:{
                setVisibleView(View.GONE,View.VISIBLE,View.GONE);
                txtUsernameEdit.setText(user.getUsername());
                txtSDTEdit.setText(user.getSdt());
                break;
            }
            case R.id.btnEditPassUser:{
                setVisibleView(View.GONE,View.GONE,View.VISIBLE);
                txtNewPassword.setText("");
                txtOldPassword.setText("");
                break;
            }
            case R.id.btnEditUserAccept:{
                if(AcceptEditThongTin()) {
                    Toast.makeText(getActivity().getApplicationContext(),"Thay đổi SDT thành công!", Toast.LENGTH_SHORT).show();
                    LoadThongTin();
                }else{
                    Toast.makeText(getActivity().getApplicationContext(),"Thay đổi SDT thất bại!", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.btnEditUserCancel:{
                LoadThongTin();
                break;
            }
            case R.id.btnEditUserPasswordAccept:{
                if(AcceptEditPassword()==true){
                    Toast.makeText(getActivity().getApplicationContext(),"Thay đổi Mật khẩu thành công!",Toast.LENGTH_SHORT).show();
                    LoadThongTin();
                }else{
                    Toast.makeText(getActivity().getApplicationContext(),"Thay đổi Mật khẩu thất bại!",Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.btnEditUserPasswordCancel:{
                LoadThongTin();
                break;
            }
        }
    }


    private Boolean AcceptEditThongTin(){
        _user u = db.getUser(user.getUser_id());
        u.setSdt(txtSDTEdit.getText().toString());
        if(db.chinhsuaThongTinUser(u)==true){
            //
            user=db.getUser(user.getUser_id());
            return true;
        }
        return false;
    }

    private Boolean AcceptEditPassword(){
        _user u = db.getUser(user.getUser_id());
        String newpass = txtNewPassword.getText().toString();
        String oldpass = txtOldPassword.getText().toString();

        if(db.thaydoiPassword(u,newpass,oldpass)==true){
            //
            user=db.getUser(user.getUser_id());
            return true;
        }
        return false;
    }
}
