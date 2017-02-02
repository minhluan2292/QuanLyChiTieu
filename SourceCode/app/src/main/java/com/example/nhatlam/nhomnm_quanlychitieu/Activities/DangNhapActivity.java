package com.example.nhatlam.nhomnm_quanlychitieu.Activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;

import com.example.nhatlam.nhomnm_quanlychitieu.Fragments.User.DangkyFragment;
import com.example.nhatlam.nhomnm_quanlychitieu.Fragments.User.DangnhapFragment;
import com.example.nhatlam.nhomnm_quanlychitieu.Fragments.fragmentAdapter;
import com.example.nhatlam.nhomnm_quanlychitieu.R;

public class DangNhapActivity extends AppCompatActivity {

    ViewPager vPaper;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        /*
        TextView txtDN = (TextView)findViewById(R.id.txtdangnhap);
        databasehelper db = new databasehelper(this.getApplicationContext());

        List<_user> lstuser = db.laydanhsachUser();

        for(int i = 0;i<lstuser.size();i++){
            txtDN.setText(txtDN.getText()+Integer.toString(lstuser.get(i).getUser_id())+" - "+lstuser.get(i).getRemember());
        }
        //db.RemoveAllUser();
        */

        tabLayout = (TabLayout) findViewById(R.id.tabLoginout);
        vPaper = (ViewPager)findViewById(R.id.viewPaperDNDK);

        fragmentAdapter adapter = new fragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(new DangnhapFragment(),"Đăng nhập");
        adapter.addFragment(new DangkyFragment(),"Đăng ký");
        vPaper.setAdapter(adapter);
        tabLayout.setupWithViewPager(vPaper);

    }
}
